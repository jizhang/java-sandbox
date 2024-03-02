package com.shzhangji.flink.proton;

import java.util.ArrayList;
import java.util.HashSet;
import org.apache.flink.api.common.state.ListState;
import org.apache.flink.api.common.state.ListStateDescriptor;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.KeyedProcessFunction;
import org.apache.flink.util.Collector;

public class UserCountProcess extends KeyedProcessFunction<Integer, UserAction, UserCount> {
  private static final long INTERVAL = 5_000;

  private transient ListState<UserAction> actions;

  @Override
  public void open(Configuration parameters) throws Exception {
    var descriptor = new ListStateDescriptor<>("actions", UserAction.class);
    actions = getRuntimeContext().getListState(descriptor);
  }

  @Override
  public void processElement(UserAction value, Context ctx, Collector<UserCount> out) throws Exception {
    actions.add(value);
    ctx.timerService().registerEventTimeTimer((ctx.timestamp() / INTERVAL + 1) * INTERVAL);
  }

  @Override
  public void onTimer(long timestamp, OnTimerContext ctx, Collector<UserCount> out) throws Exception {
    long maxTimestamp = timestamp - 1;
    long ts1min = timestamp - 60_000;
    long ts5min = timestamp - 300_000;
    long ts15min = timestamp - 900_000;

    var newActions = new ArrayList<UserAction>();
    var set1min = new HashSet<Long>();
    var set5min = new HashSet<Long>();
    var set15min = new HashSet<Long>();

    for (var action : actions.get()) {
      long ts = (long) (action.getTimestamp() * 1000);
      if (ts < ts15min) continue;
      newActions.add(action);
      if (ts > maxTimestamp) continue;
      if (ts >= ts1min) set1min.add(action.getUserId());
      if (ts >= ts5min) set5min.add(action.getUserId());
      set15min.add(action.getUserId());
    }

    out.collect(new UserCount(maxTimestamp, set1min.size(), set5min.size(), set15min.size()));
    actions.update(newActions);
  }
}
