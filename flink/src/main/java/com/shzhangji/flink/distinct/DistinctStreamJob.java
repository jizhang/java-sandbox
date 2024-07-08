package com.shzhangji.flink.distinct;

import com.google.common.hash.Hashing;
import java.time.Duration;
import java.util.HashSet;
import java.util.Set;
import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.api.common.functions.AggregateFunction;
import org.apache.flink.api.common.functions.OpenContext;
import org.apache.flink.api.common.state.MapState;
import org.apache.flink.api.common.state.MapStateDescriptor;
import org.apache.flink.api.common.state.ValueState;
import org.apache.flink.api.common.state.ValueStateDescriptor;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.api.connector.source.util.ratelimit.RateLimiterStrategy;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.configuration.CheckpointingOptions;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.connector.datagen.source.DataGeneratorSource;
import org.apache.flink.connector.datagen.source.GeneratorFunction;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.KeyedStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.KeyedProcessFunction;
import org.apache.flink.streaming.api.functions.windowing.ProcessWindowFunction;
import org.apache.flink.streaming.api.windowing.assigners.GlobalWindows;
import org.apache.flink.streaming.api.windowing.triggers.ContinuousProcessingTimeTrigger;
import org.apache.flink.streaming.api.windowing.windows.GlobalWindow;
import org.apache.flink.util.Collector;

public class DistinctStreamJob {
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

    KeyedStream<String, String> stream = env
        .fromSource(source, WatermarkStrategy.noWatermarks(), "datagen")
        .keyBy(value -> value.substring(0, 1));

    processByWindow(stream);
    processByFunction(stream);

    env.execute();
  }

  static void processByWindow(KeyedStream<String, String> stream) {
    stream
        .window(GlobalWindows.create())
        .trigger(ContinuousProcessingTimeTrigger.of(Duration.ofSeconds(5)))
        .aggregate(new DistinctAggregate(), new DistinctProcessWindow())
        .print();
  }

  public static class DistinctAggregate implements AggregateFunction<String, Set<String>, Long> {
    @Override
    public Set<String> createAccumulator() {
      return new HashSet<>();
    }

    @Override
    public Set<String> add(String value, Set<String> accumulator) {
      accumulator.add(value);
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

  public static class DistinctProcessWindow extends ProcessWindowFunction<Long, Tuple2<String, Long>, String, GlobalWindow> {
    @Override
    public void process(String key, Context context, Iterable<Long> elements, Collector<Tuple2<String, Long>> out) throws Exception {
      var count = elements.iterator().next();
      out.collect(Tuple2.of(key, count));
    }
  }

  static void processByFunction(KeyedStream<String, String> stream) {
    stream
        .process(new DistinctProcess())
        .print();
  }

  public static class DistinctProcess extends KeyedProcessFunction<String, String, Tuple2<String, Long>> {
    private static final long INTERVAL = 5_000;

    private transient MapState<String, Long> values;
    private transient ValueState<Long> count;

    @Override
    public void open(OpenContext openContext) throws Exception {
      values = getRuntimeContext().getMapState(new MapStateDescriptor<>("values", String.class, Long.class));
      count = getRuntimeContext().getState(new ValueStateDescriptor<>("count", Long.class));
    }

    @Override
    public void processElement(String value, Context ctx, Collector<Tuple2<String, Long>> out) throws Exception {
      if (!values.contains(value)) {
        values.put(value, 1L);
        Long currentCount = count.value() == null ? 1L : count.value() + 1;
        count.update(currentCount);
      }

      var timer = ctx.timerService();
      var processingTime = timer.currentProcessingTime();
      timer.registerProcessingTimeTimer((processingTime / INTERVAL + 1) * INTERVAL);
    }

    @Override
    public void onTimer(long timestamp, OnTimerContext ctx, Collector<Tuple2<String, Long>> out) throws Exception {
      // System.out.println(Utils.formatTimestampMillis(ctx.timerService().currentProcessingTime(), "yyyy-MM-dd HH:mm:ss"));
      out.collect(Tuple2.of(ctx.getCurrentKey(), count.value()));
    }
  }
}
