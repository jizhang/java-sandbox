package flink.kafka;

import java.time.Duration;
import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.api.common.serialization.SimpleStringEncoder;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.connector.file.sink.FileSink;
import org.apache.flink.connector.kafka.source.KafkaSource;
import org.apache.flink.connector.kafka.source.enumerator.initializer.OffsetsInitializer;
import org.apache.flink.core.fs.Path;
import org.apache.flink.runtime.state.hashmap.HashMapStateBackend;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.sink.filesystem.rollingpolicies.DefaultRollingPolicy;
import org.apache.kafka.clients.consumer.OffsetResetStrategy;

public class KafkaLoader {
  public static void main(String[] args) throws Exception {
    StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
    env.setParallelism(1);

    // checkpoint
    env.enableCheckpointing(10_000);
    env.setStateBackend(new HashMapStateBackend());
    env.getCheckpointConfig().setCheckpointStorage("file:///tmp/flink/checkpoints");

    // source
    var source = KafkaSource.<String>builder()
      .setBootstrapServers("localhost:9092")
      .setTopics("flink_test")
      .setGroupId("flink_group")
      .setStartingOffsets(OffsetsInitializer.committedOffsets(OffsetResetStrategy.LATEST))
      .setValueOnlyDeserializer(new SimpleStringSchema())
      .build();
    var stream = env.fromSource(source, WatermarkStrategy.noWatermarks(), "kafka-source");

    // sink
    var sink = FileSink
        .forRowFormat(new Path("file:///tmp/kafka-loader"), new SimpleStringEncoder<String>())
        .withBucketAssigner(new EventTimeBucketAssigner())
        .withRollingPolicy(DefaultRollingPolicy.builder()
          .withRolloverInterval(Duration.ofSeconds(15))
          .build())
        .withBucketCheckInterval(1000)
        .build();

    stream.sinkTo(sink);

    env.execute();
  }
}
