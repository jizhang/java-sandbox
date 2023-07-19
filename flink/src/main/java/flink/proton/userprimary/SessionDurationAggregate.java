package flink.proton.userprimary;

import org.apache.flink.api.common.functions.AggregateFunction;

public class SessionDurationAggregate implements AggregateFunction<UserSessionDuration, RunningAverage, Long> {
  @Override
  public RunningAverage createAccumulator() {
    return new RunningAverage(0, 0);
  }

  @Override
  public RunningAverage add(UserSessionDuration value, RunningAverage accumulator) {
    long duration = value.getEnd() - value.getStart();
    return new RunningAverage(accumulator.getSum() + duration, accumulator.getCount() + 1);
  }

  @Override
  public Long getResult(RunningAverage accumulator) {
    return accumulator.getAverage();
  }

  @Override
  public RunningAverage merge(RunningAverage a, RunningAverage b) {
    return new RunningAverage(a.getSum() + b.getSum(), a.getCount() + b.getCount());
  }
}
