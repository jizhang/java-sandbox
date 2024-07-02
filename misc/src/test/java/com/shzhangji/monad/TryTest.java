package com.shzhangji.monad;

import java.util.NoSuchElementException;
import org.junit.Test;
import static org.junit.Assert.*;

public class TryTest {
  @Test
  public void testGet() {
    Try<Integer> success = Try.ofThrowable(() -> Integer.valueOf("1"));
    int successResult = 0;
    try {
      successResult = success.get();
    } catch (Throwable e) {
      fail();
    }
    assertEquals(1, successResult);
    assertEquals(1, (int) success.orElse(2));

    Try<Integer> failure = Try.ofThrowable(() -> Integer.valueOf("a"));
    assertThrows(NumberFormatException.class, failure::get);
    assertEquals(2, (int) failure.orElse(2));
  }

  @Test
  public void testFlatMap() {
    Try<Double> success = Try.ofThrowable(() -> Integer.valueOf("1"))
        .flatMap(n -> Try.ofThrowable(() -> n / 2.0));
    assertEquals(0.5, success.orElse(0.0), 0.01);

    Try<Double> failure1 = Try.ofThrowable(() -> Integer.valueOf("a"))
        .flatMap(n -> Try.ofThrowable(() -> n / 2.0));
    assertTrue(failure1 instanceof Failure);

    Try<Double> failure2 = Try.ofThrowable((() -> Integer.valueOf("1")))
        .flatMap(n -> Try.ofThrowable(() -> Double.valueOf("a")));
    assertTrue(failure2 instanceof Failure);
  }

  @Test
  public void testMap() {
    Try<Double> success = Try.ofThrowable(() -> Integer.valueOf("1"))
        .map(n -> n / 2.0);
    assertEquals(0.5, success.orElse(0.0), 0.01);

    Try<Double> failure1 = Try.ofThrowable(() -> Integer.valueOf("a"))
        .map(n -> n / 2.0);
    assertTrue(failure1 instanceof Failure);

    Try<Double> failure2 = Try.ofThrowable(() -> Integer.valueOf("1"))
        .map(n -> Double.valueOf("a"));
    assertTrue(failure2 instanceof Failure);
  }

  @Test
  public void testFilter() {
    Try<Integer> success = Try.ofThrowable(() -> Integer.valueOf("1"))
        .filter(n -> n > 0);
    assertEquals(1, (int) success.orElse(2));

    Try<Integer> failure1 = Try.ofThrowable(() -> Integer.valueOf("a"))
        .filter(n -> n > 0);
    assertThrows(NumberFormatException.class, failure1::get);

    Try<Integer> failure2 = Try.ofThrowable(() -> Integer.valueOf("1"))
        .filter(n -> n > 1);
    assertThrows(NoSuchElementException.class, failure2::get);
  }
}
