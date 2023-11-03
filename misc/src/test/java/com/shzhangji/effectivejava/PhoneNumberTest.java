package com.shzhangji.effectivejava;

import org.junit.Test;

import java.util.List;
import java.util.TreeSet;

import static org.junit.Assert.*;

public class PhoneNumberTest {
  @Test
  public void testToString() {
    var pn = new PhoneNumber(707, 867, 5309);
    assertEquals("707-867-5309", pn.toString());

    pn = new PhoneNumber(1, 2, 3);
    assertEquals("001-002-0003", pn.toString());
  }

  @Test
  public void testCompareTo() {
    var set = new TreeSet<PhoneNumber>();
    set.add(new PhoneNumber(1, 2, 3));
    set.add(new PhoneNumber(2, 1, 3));
    set.add(new PhoneNumber(1, 3, 2));

    var expected = List.of(
        new PhoneNumber(1, 2, 3),
        new PhoneNumber(1, 3, 2),
        new PhoneNumber(2, 1, 3));
    assertEquals(expected, List.copyOf(set));
  }
}
