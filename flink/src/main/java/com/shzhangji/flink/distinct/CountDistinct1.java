package com.shzhangji.flink.distinct;

import java.util.HashMap;
import java.util.Map;
import org.apache.flink.table.functions.AggregateFunction;

public class CountDistinct1 extends AggregateFunction<Long, CountDistinct1.Accumulator> {
  public static class Accumulator {
    public Map<String, Integer> values = new HashMap<>();
  }

  @Override
  public Accumulator createAccumulator() {
    return new Accumulator();
  }

  @Override
  public Long getValue(Accumulator accumulator) {
    return (long) accumulator.values.size();
  }

  public void accumulate(Accumulator accumulator, String value) {
    accumulator.values.put(value, 1);
  }
}
