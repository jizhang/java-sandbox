package com.shzhangji.effectivejava;

import lombok.Getter;

@Getter
public class IndexOutOfBoundsException extends RuntimeException {
  private final int lowerBound;
  private final int upperBound;
  private final int index;

  public IndexOutOfBoundsException(int lowerBound, int upperBound, int index) {
    super(String.format("Lower bound: %d, Upper bound: %d, Index: %d",
        lowerBound, upperBound, index));
    this.lowerBound = lowerBound;
    this.upperBound = upperBound;
    this.index = index;
  }
}
