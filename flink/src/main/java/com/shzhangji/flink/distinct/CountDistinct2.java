package com.shzhangji.flink.distinct;

import org.apache.flink.table.api.dataview.MapView;
import org.apache.flink.table.functions.AggregateFunction;

public class CountDistinct2 extends AggregateFunction<Long, CountDistinct2.Accumulator> {
  public static class Accumulator {
    public MapView<String, Integer> values = new MapView<>();
    public long count = 0;
  }

  @Override
  public Accumulator createAccumulator() {
    return new Accumulator();
  }

  @Override
  public Long getValue(Accumulator accumulator) {
    return accumulator.count;
  }

  public void accumulate(Accumulator accumulator, String value) throws Exception {
    if (!accumulator.values.contains(value)) {
      accumulator.values.put(value, 1);
      accumulator.count++;
    }
  }
}
