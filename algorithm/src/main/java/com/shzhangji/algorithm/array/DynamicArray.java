package com.shzhangji.algorithm.array;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DynamicArray {
  public static void main(String[] args) {
    var arr = new DynamicArray();
    System.out.println(arr);
    arr.add(1);
    arr.add(2);
    arr.add(3);
    arr.add(4);
    arr.add(5);
    System.out.println(arr);
  }

  private int[] elements = new int[0];
  private int size = 0;

  public void add(int element) {
    if (size == elements.length) {
      grow();
    }
    elements[size++] = element;
  }

  private void grow() {
    elements = Arrays.copyOf(elements, Math.max(size + 1, (int) (size * 1.5)));
  }

  @Override
  public String toString() {
    var content = IntStream.range(0, size)
      .mapToObj(i -> String.valueOf(elements[i]))
      .collect(Collectors.joining(", "));
    return String.format("[%s] size=%d capacity=%d", content, size, elements.length);
  }
}
