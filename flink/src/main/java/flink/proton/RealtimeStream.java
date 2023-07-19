package flink.proton;

import java.time.Duration;
import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.connector.jdbc.JdbcConnectionOptions;
import org.apache.flink.connector.jdbc.JdbcExecutionOptions;
import org.apache.flink.connector.jdbc.JdbcSink;
import org.apache.flink.connector.kafka.source.KafkaSource;
import org.apache.flink.connector.kafka.source.enumerator.initializer.OffsetsInitializer;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.assigners.TumblingEventTimeWindows;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.api.windowing.triggers.ContinuousEventTimeTrigger;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;

public class RealtimeStream {
  public static void main(String[] args) throws Exception {
    var env = StreamExecutionEnvironment.getExecutionEnvironment();
    env.setParallelism(1); // Fix idle source.

    var source = KafkaSource.<UserAction>builder()
        .setBootstrapServers("localhost:9092")
        .setTopics("flink_test")
        .setGroupId("flink_group")
        .setStartingOffsets(OffsetsInitializer.latest())
        .setDeserializer(new UserActionDeserializer())
        .build();

    var watermarkStrategy = WatermarkStrategy
        .<UserAction>forBoundedOutOfOrderness(Duration.ofSeconds(5))
        .withTimestampAssigner((action, timestamp) -> (long) (action.getTimestamp() * 1000));

    var actions = env.fromSource(source, watermarkStrategy, "kafka-source");

    var jdbcExec = JdbcExecutionOptions.builder()
        .withBatchSize(100)
        .withBatchIntervalMs(1000)
        .build();
    var jdbcConn = new JdbcConnectionOptions.JdbcConnectionOptionsBuilder()
        .withUrl("jdbc:mysql://localhost:3306/proton")
        .withUsername("root")
        .withPassword("")
        .build();

    var actionSink = JdbcSink.<UserAction>sink(
        "REPLACE INTO ds_user_action " +
            "(`partition`, `offset`, `user_id`, `action_id`, `timestamp`, `created_at`) " +
            "VALUES (?, ?, ?, ?, ?, ?) ",
        (stmt, action) -> {
          stmt.setInt(1, action.getPartition());
          stmt.setLong(2, action.getOffset());
          stmt.setLong(3, action.getUserId());
          stmt.setInt(4, action.getActionId());
          stmt.setDouble(5, action.getTimestamp());
          stmt.setString(6, Utils.formatTimestamp(action.getTimestamp(), "yyyy-MM-dd HH:mm:ss"));
        },
        jdbcExec, jdbcConn);

    actions.addSink(actionSink);
    var loginActions = actions.filter(action -> action.getActionId() == 8 || action.getActionId() == 9);

    var count1min = loginActions
        .windowAll(TumblingEventTimeWindows.of(Time.seconds(60)))
        .trigger(ContinuousEventTimeTrigger.of(Time.seconds(5)))
        .aggregate(new UserCountAggregate(), new UserCountWindow());

    var count1minSink = JdbcSink.<Tuple2<TimeWindow, Long>>sink(
        "REPLACE INTO da_user_count_1min (`report_minute`, `user_count`) VALUES (?, ?) ",
        (stmt, tuple) -> {
          stmt.setString(1, Utils.formatTimestampMillis(tuple.f0.getStart(), "yyyy-MM-dd HH:mm:ss"));
          stmt.setLong(2, tuple.f1);
        },
        jdbcExec, jdbcConn);

    count1min.addSink(count1minSink);

    var rtSink = JdbcSink.<UserCount>sink(
        "REPLACE INTO da_user_count_rt " +
            "(`report_minute`, `user_count_1min`, `user_count_5min`, `user_count_15min`) " +
            "VALUES (?, ?, ?, ?) ",
        (stmt, row) -> {
          stmt.setString(1, Utils.formatTimestampMillis(row.getTimestamp(), "yyyy-MM-dd HH:mm:00"));
          stmt.setLong(2, row.getCount1min());
          stmt.setLong(3, row.getCount5min());
          stmt.setLong(4, row.getCount15min());
        },
        jdbcExec, jdbcConn);

    loginActions
        .keyBy(action -> 0)
        .process(new UserCountProcess())
        .addSink(rtSink);

    env.execute("Realtime Stream");
  }
}
