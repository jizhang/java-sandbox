package com.shzhangji.effectivejava.item20;

import org.junit.Test;
import static org.junit.Assert.*;

public class ListHelperTest {
  @Test
  public void testIntArrayAsList() {
    var arr = new int[] { 1, 2, 3 };
    var list = ListHelper.intArrayAsList(arr);
    assertEquals(3, list.size());
    assertEquals(Integer.valueOf(2), list.get(1));

    list.set(1, 4);
    assertEquals(Integer.valueOf(4), list.get(1));
    assertEquals(4, arr[1]);
  }
}
