package com.shzhangji.flink.proton.userprimary;

import com.shzhangji.flink.proton.UserAction;
import com.shzhangji.flink.proton.UserActionDeserializer;
import java.time.Duration;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import org.apache.flink.api.common.RuntimeExecutionMode;
import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.connector.jdbc.JdbcConnectionOptions;
import org.apache.flink.connector.jdbc.JdbcSink;
import org.apache.flink.connector.kafka.source.KafkaSource;
import org.apache.flink.connector.kafka.source.enumerator.initializer.OffsetsInitializer;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.assigners.EventTimeSessionWindows;
import org.apache.flink.streaming.api.windowing.assigners.TumblingEventTimeWindows;
import org.apache.flink.streaming.api.windowing.time.Time;

public class UserPrimaryDailyJob {
  public static void main(String[] args) throws Exception {
    var env = StreamExecutionEnvironment.getExecutionEnvironment();
    env.setRuntimeMode(RuntimeExecutionMode.BATCH);
    env.setParallelism(1);

    var dt = LocalDate.parse("2023-03-17").atStartOfDay();
    var dtOffset = OffsetDateTime.now().getOffset();
    long startTs = dt.toInstant(dtOffset).toEpochMilli();
    long endTs = dt.plusDays(1).plusMinutes(10).toInstant(dtOffset).toEpochMilli();
    var bounded = dt.equals(LocalDate.now().atStartOfDay()) ?
        OffsetsInitializer.latest() : OffsetsInitializer.timestamp(endTs);

    var source = KafkaSource.<UserAction>builder()
        .setBootstrapServers("localhost:9092")
        .setTopics("flink_test")
        .setGroupId("flink_group")
        .setDeserializer(new UserActionDeserializer())
        .setStartingOffsets(OffsetsInitializer.timestamp(startTs))
        .setBounded(bounded)
        .build();

    var watermarkStrategy = WatermarkStrategy
        .<UserAction>forBoundedOutOfOrderness(Duration.ofDays(1))
        .withTimestampAssigner((action, timestamp) -> (long) (action.getTimestamp() * 1000));

    DataStream<UserAction> actions = env.fromSource(source, watermarkStrategy, "kafka-source");

    long minSec = dt.toEpochSecond(dtOffset);
    long maxSec = dt.plusDays(1).toEpochSecond(dtOffset);
    actions = actions.filter(action -> action.getTimestamp() >= minSec && action.getTimestamp() < maxSec);

    var dailyWindow = TumblingEventTimeWindows.of(Time.days(1), Time.hours(-8));

    var userCount = actions
        .keyBy(UserAction::getUserId)
        .window(dailyWindow)
        .reduce((a, b) -> a)
        .map(action -> 1L)
        .windowAll(dailyWindow)
        .sum(0)
        .map(value -> new TaggedNumber("user_count", value));

    var singlePageUserCount = actions
        .map(UserActionCount::new)
        .keyBy(UserActionCount::getUserId)
        .window(dailyWindow)
        .reduce(UserActionCount::merge)
        .filter(row -> row.getActionCount() == 1)
        .map(row -> 1L)
        .windowAll(dailyWindow)
        .sum(0)
        .map(value -> new TaggedNumber("single_page_user_count", value));

    var sessionCount = actions
        .keyBy(UserAction::getUserId)
        .window(EventTimeSessionWindows.withGap(Time.minutes(10)))
        .reduce((a, b) -> a)
        .map(action -> 1L)
        .windowAll(dailyWindow)
        .sum(0)
        .map(value -> new TaggedNumber("session_count", value));

    var totalSessionSeconds = actions
        .map(UserSessionDuration::new)
        .keyBy(UserSessionDuration::getUserId)
        .window(EventTimeSessionWindows.withGap(Time.minutes(10)))
        .reduce(UserSessionDuration::merge)
        .windowAll(dailyWindow)
        .aggregate(new SessionDurationAggregate())
        .map(ms -> new TaggedNumber("total_session_seconds", ms / 1000));

    var result = userCount
        .union(singlePageUserCount)
        .union(sessionCount)
        .union(totalSessionSeconds)
        .windowAll(dailyWindow)
        .apply(new UserPrimaryDailyWindow());

    var jdbcSink = JdbcSink.<UserPrimaryDaily>sink(
        "REPLACE INTO da_user_primary_daily " +
            "(report_date, user_count, single_page_user_count, session_count, total_session_seconds) " +
            "VALUES (?, ?, ?, ?, ?) ",
        (stmt, row) -> {
          stmt.setInt(1, row.getReportDate());
          stmt.setLong(2, row.getUserCount());
          stmt.setLong(3, row.getSinglePageUserCount());
          stmt.setLong(4, row.getSessionCount());
          stmt.setLong(5, row.getTotalSessionSeconds());
        },
        new JdbcConnectionOptions.JdbcConnectionOptionsBuilder()
            .withUrl("jdbc:mysql://localhost:3306/proton")
            .withUsername("root")
            .withPassword("")
            .build());

    result.addSink(jdbcSink);

    env.execute("User Primary Daily");
  }
}
