package com.shzhangji.javasandbox.disruptor;

import lombok.ToString;

@ToString
public class LongEvent {
  private long value;

  public void set(long value) {
    this.value = value;
  }
}
