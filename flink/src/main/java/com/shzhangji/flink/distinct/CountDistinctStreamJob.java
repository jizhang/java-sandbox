package com.shzhangji.flink.distinct;

import com.google.common.hash.Hashing;
import java.time.Duration;
import java.util.HashSet;
import java.util.Set;
import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.api.common.functions.AggregateFunction;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.api.connector.source.util.ratelimit.RateLimiterStrategy;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.configuration.CheckpointingOptions;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.connector.datagen.source.DataGeneratorSource;
import org.apache.flink.connector.datagen.source.GeneratorFunction;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.windowing.ProcessWindowFunction;
import org.apache.flink.streaming.api.windowing.assigners.GlobalWindows;
import org.apache.flink.streaming.api.windowing.triggers.ContinuousProcessingTimeTrigger;
import org.apache.flink.streaming.api.windowing.windows.GlobalWindow;
import org.apache.flink.util.Collector;

public class CountDistinctStreamJob {
  public static void main(String[] args) throws Exception {
    var conf = new Configuration();
    conf.set(CheckpointingOptions.CHECKPOINT_STORAGE, "filesystem");
    conf.set(CheckpointingOptions.CHECKPOINTS_DIRECTORY, "file:///tmp/count-distinct/");

//    StreamExecutionEnvironment env = StreamExecutionEnvironment.createLocalEnvironmentWithWebUI(conf);
    StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment(conf);
    env.enableCheckpointing(5000);
    env.setParallelism(1);

    GeneratorFunction<Long, String> generator = index -> Hashing.sha256().hashLong(index).toString();
    DataGeneratorSource<String> source = new DataGeneratorSource<>(
        generator, Long.MAX_VALUE, RateLimiterStrategy.perSecond(100), Types.STRING);

    DataStream<String> stream = env.fromSource(source, WatermarkStrategy.noWatermarks(), "data-gen");
    stream.map(value -> Tuple2.of(value.substring(0, 1), value))
        .returns(Types.TUPLE(Types.STRING, Types.STRING))
        .keyBy(t -> t.f0)
        .window(GlobalWindows.create())
        .trigger(ContinuousProcessingTimeTrigger.of(Duration.ofSeconds(1)))
        .aggregate(new DistinctAggregate(), new DistinctProcess())
        .print();

    env.execute();

    // Use process function
  }

  public static class DistinctAggregate implements AggregateFunction<Tuple2<String, String>, Set<String>, Long> {
    @Override
    public Set<String> createAccumulator() {
      return new HashSet<>();
    }

    @Override
    public Set<String> add(Tuple2<String, String> value, Set<String> accumulator) {
      accumulator.add(value.f1);
      return accumulator;
    }

    @Override
    public Long getResult(Set<String> accumulator) {
      return (long) accumulator.size();
    }

    @Override
    public Set<String> merge(Set<String> a, Set<String> b) {
      a.addAll(b);
      return a;
    }
  }

  public static class DistinctProcess extends ProcessWindowFunction<Long, Tuple2<String, Long>, String, GlobalWindow> {
    @Override
    public void process(String key, Context context, Iterable<Long> elements, Collector<Tuple2<String, Long>> out) throws Exception {
      var count = elements.iterator().next();
      out.collect(Tuple2.of(key, count));
    }
  }
}
