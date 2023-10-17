package com.shzhangji.effectivejava.item30;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static com.shzhangji.effectivejava.item30.CollectionHelper.max;

public class CollectionHelperTest {
  @Test
  public void testMax() {
    assertEquals(Optional.empty(), max(List.<Integer>of()));
    assertEquals(Optional.of(3), max(List.of(1, 3, 2)));

    var list = new ArrayList<Integer>();
    list.add(null);
    assertEquals(Optional.empty(), max(list));
    list.add(1);
    assertEquals(Optional.of(1), max(list));
  }
}
