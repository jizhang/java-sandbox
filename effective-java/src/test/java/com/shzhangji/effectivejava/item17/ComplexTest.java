package com.shzhangji.effectivejava.item17;

import org.junit.Test;
import static org.junit.Assert.*;

public class ComplexTest {
  @Test
  public void testValueOf() {
    var c = Complex.valueOf(1, 2);
    assertEquals(1, c.realPart(), 0.001);
    assertEquals(2, c.imaginaryPart(), 0.001);
    assertEquals("(1.0 + 2.0i)", c.toString());
  }

  @Test
  public void testCalculation() {
    var a = Complex.valueOf(1, 2);
    var b = Complex.valueOf(3, 4);

    var c = a.plus(b);
    assertEquals(4, c.realPart(), 0.001);
    assertEquals(6, c.imaginaryPart(), 0.001);

    c = a.minus(b);
    assertEquals(-2, c.realPart(), 0.001);
    assertEquals(-2, c.imaginaryPart(), 0.001);

    a = Complex.valueOf(3, 4);
    b = Complex.valueOf(3, 4);
    c = a.times(b);
    assertEquals(-7, c.realPart(), 0.001);
    assertEquals(24, c.imaginaryPart(), 0.001);

    a = Complex.valueOf(3, 4);
    b = Complex.valueOf(1, 1);
    c = a.dividedBy(b);
    assertEquals(3.5, c.realPart(), 0.001);
    assertEquals(0.5, c.imaginaryPart(), 0.001);
  }

  @Test
  public void testEquals() {
    var a = Complex.valueOf(1, 2);
    var b = Complex.valueOf(1, 2);
    var c = Complex.valueOf(3, 4);
    assertFalse(a.equals(null));
    assertFalse(a.equals(""));
    assertTrue(a.equals(a));
    assertTrue(a.equals(b) && b.equals(a));
    assertFalse(a.equals(c) || c.equals(a));
  }

  @Test
  public void testHashCode() {
    var a = Complex.valueOf(1, 2);
    var b = Complex.valueOf(1, 2);
    var c = Complex.valueOf(3, 4);
    assertEquals(a.hashCode(), a.hashCode());
    assertEquals(a.hashCode(), b.hashCode());
    assertNotEquals(a.hashCode(), c.hashCode());
  }
}
