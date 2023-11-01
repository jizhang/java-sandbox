package com.shzhangji.effectivejava;

import org.junit.Test;
import static org.junit.Assert.*;

public class IndexOutOfBoundsExceptionTest {
  @Test
  public void testException() {
    var e = new IndexOutOfBoundsException(0, 9, 10);
    assertEquals("Lower bound: 0, Upper bound: 9, Index: 10", e.getMessage());
    assertEquals(10, e.getIndex());
  }
}
