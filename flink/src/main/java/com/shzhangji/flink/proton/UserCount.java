package com.shzhangji.flink.proton;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserCount {
  private long timestamp;
  private long count1min;
  private long count5min;
  private long count15min;
}
