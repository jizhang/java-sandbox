package com.shzhangji.flink.distinct;

import org.apache.flink.table.functions.AggregateFunction;

public class CountDistinct extends AggregateFunction<Long, CountDistinctAccumulator> {
  @Override
  public CountDistinctAccumulator createAccumulator() {
    return new CountDistinctAccumulator();
  }

  @Override
  public Long getValue(CountDistinctAccumulator accumulator) {
    return (long) accumulator.values.size();
  }

  public void accumulate(CountDistinctAccumulator accumulator, String value) {
    accumulator.values.putIfAbsent(value, 1L);
  }
}
