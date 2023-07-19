package flink.proton;

import java.util.HashSet;
import java.util.Set;
import org.apache.flink.api.common.functions.AggregateFunction;

public class UserCountAggregate implements AggregateFunction<UserAction, Set<Long>, Long> {
  @Override
  public Set<Long> createAccumulator() {
    return new HashSet<>();
  }

  @Override
  public Set<Long> add(UserAction value, Set<Long> accumulator) {
    accumulator.add(value.getUserId());
    return accumulator;
  }

  @Override
  public Long getResult(Set<Long> accumulator) {
    return (long) accumulator.size();
  }

  @Override
  public Set<Long> merge(Set<Long> a, Set<Long> b) {
    a.addAll(b);
    return a;
  }
}
