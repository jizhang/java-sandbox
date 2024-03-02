package com.shzhangji.flink.proton.userprimary;

import lombok.Value;

@Value
public class RunningAverage {
  long sum;
  long count;

  public long getAverage() {
    return sum / count;
  }
}
