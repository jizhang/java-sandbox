package com.shzhangji.effectivejava;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public enum Operation {
  PLUS("+") {
    @Override
    public double apply(double x, double y) {
      return x + y;
    }
  },

  MINUS("-") {
    @Override
    public double apply(double x, double y) {
      return x - y;
    }
  },

  TIMES("*") {
    @Override
    public double apply(double x, double y) {
      return x * y;
    }
  },

  DIVIDE("/") {
    @Override
    public double apply(double x, double y) {
      return x / y;
    }
  };

  private static final Map<String, Operation> stringToEnum =
      Stream.of(values()).collect(toMap(Object::toString, e -> e));

  private final String symbol;

  Operation(String symbol) {
    this.symbol = symbol;
  }

  public static Optional<Operation> fromString(String symbol) {
    return Optional.ofNullable(stringToEnum.get(symbol));
  }

  @Override
  public String toString() {
    return symbol;
  }

  public abstract double apply(double x, double y);
}
