package com.shzhangji.javasandbox.disruptor;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FizzBuzzEvent {
  private long value;
  private long fizz;
  private long buzz;
}
