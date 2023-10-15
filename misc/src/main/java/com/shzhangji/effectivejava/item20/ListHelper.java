package com.shzhangji.effectivejava.item20;

import com.google.common.base.Preconditions;

import java.util.AbstractList;
import java.util.List;

public class ListHelper {
  public static List<Integer> intArrayAsList(int[] a) {
    Preconditions.checkNotNull(a);

    return new AbstractList<>() {
      @Override
      public Integer get(int index) {
        return a[index];
      }

      @Override
      public Integer set(int index, Integer element) {
        int oldValue = a[index];
        a[index] = element;
        return oldValue;
      }

      @Override
      public int size() {
        return a.length;
      }
    };
  }
}
