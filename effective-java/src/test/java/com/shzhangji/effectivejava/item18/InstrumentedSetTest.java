package com.shzhangji.effectivejava.item18;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.List;
import java.util.TreeSet;

public class InstrumentedSetTest {
  @Test
  public void testAdd() {
    var times = new InstrumentedSet<>(new TreeSet<Integer>());
    times.add(2);
    times.add(3);
    times.add(1);
    assertEquals(3, times.getAddCount());
    assertEquals(List.of(1, 2, 3), List.copyOf(times));

    times.addAll(List.of(4, 5));
    assertEquals(5, times.getAddCount());
  }
}
