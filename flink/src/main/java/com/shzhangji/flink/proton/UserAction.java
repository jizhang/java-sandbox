package com.shzhangji.flink.proton;

import lombok.Data;

@Data
public class UserAction {
  private int partition;
  private long offset;
  private long userId;
  private int actionId;
  private double timestamp;
}
