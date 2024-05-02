package com.shzhangji.effectivejava;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;

import static org.junit.Assert.*;

public class PeriodTest {
  @Test
  public void testValidity() {
    var start = new Date();
    var end = new Date(start.getTime() - 60);
    assertThrows(IllegalArgumentException.class, () -> new Period(start, end));
  }

  @Test
  public void testImmutability() {
    var baseStart = new Date();
    var baseEnd = new Date(baseStart.getTime() + 60);

    var start = new Date(baseStart.getTime());
    var end = new Date(baseEnd.getTime());
    var period = new Period(start, end);

    assertEquals(start, period.start());
    assertEquals(end, period.end());
    assertNotSame(start, period.start());
    assertNotSame(end, period.end());

    start.setTime(baseStart.getTime() - 1);
    end.setTime(baseEnd.getTime() + 1);
    assertEquals(baseStart, period.start());
    assertEquals(baseEnd, period.end());

    period.start().setTime(baseStart.getTime() - 1);
    period.end().setTime(baseEnd.getTime() + 1);
    assertEquals(baseStart, period.start());
    assertEquals(baseEnd, period.end());
  }

  @Test
  public void testSerialization() throws Exception {
    var start = new Date();
    var end = new Date(start.getTime() + 60);
    var period = new Period(start, end);

    byte[] arr;
    try (var buffer = new ByteArrayOutputStream();
         var out = new ObjectOutputStream(buffer)) {
      out.writeObject(period);
      arr = buffer.toByteArray();
    }

    Period otherPeriod;
    try (var in = new ObjectInputStream(new ByteArrayInputStream(arr))) {
      otherPeriod = (Period) in.readObject();
    }

    assertEquals(period, otherPeriod);
    assertNotSame(period, otherPeriod);
    assertNotSame(start, otherPeriod.start());
    assertNotSame(end, otherPeriod.end());
  }
}
