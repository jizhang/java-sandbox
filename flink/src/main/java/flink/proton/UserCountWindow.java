package flink.proton;

import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.functions.windowing.ProcessAllWindowFunction;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.util.Collector;

public class UserCountWindow extends ProcessAllWindowFunction<Long, Tuple2<TimeWindow, Long>, TimeWindow> {
  @Override
  public void process(Context context, Iterable<Long> elements, Collector<Tuple2<TimeWindow, Long>> out) throws Exception {
    out.collect(Tuple2.of(context.window(), elements.iterator().next()));
  }
}
