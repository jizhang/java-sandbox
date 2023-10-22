package com.shzhangji.effectivejava;

import org.junit.Test;
import static org.junit.Assert.*;

public class OperationTest {
  @Test
  public void testOperation() {
    assertEquals(6.0, Operation.PLUS.apply(2.0, 4.0), 0.1);
    assertEquals(-2.0, Operation.MINUS.apply(2.0, 4.0), 0.1);
    assertEquals(8.0, Operation.TIMES.apply(2.0, 4.0), 0.1);
    assertEquals(0.5, Operation.DIVIDE.apply(2.0, 4.0), 0.1);
  }

  @Test
  public void testToString() {
    assertEquals("+", Operation.PLUS.toString());
    assertEquals("-", Operation.MINUS.toString());
    assertEquals("*", Operation.TIMES.toString());
    assertEquals("/", Operation.DIVIDE.toString());
  }

  @Test
  public void testFromString() {
    assertSame(Operation.PLUS, Operation.fromString("+").orElseThrow());
    assertSame(Operation.MINUS, Operation.fromString("-").orElseThrow());
    assertSame(Operation.TIMES, Operation.fromString("*").orElseThrow());
    assertSame(Operation.DIVIDE, Operation.fromString("/").orElseThrow());
    assertTrue(Operation.fromString("").isEmpty());
  }
}
