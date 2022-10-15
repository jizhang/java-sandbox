package com.shzhangji.algorithm.array;

import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FixedSizedSortedArray {
  public static void main(String[] args) {
    var arr = new FixedSizedSortedArray(10);
    arr.add(1);
    arr.add(2);
    arr.add(3);
    arr.add(5);
    arr.add(6);
    arr.add(7);
    arr.add(0);
    System.out.println(arr);
    arr.delete(2);
    System.out.println(arr);
  }

  private final int[] elements;
  private int size;

  public FixedSizedSortedArray(int maxSize) {
    elements = new int[maxSize];
    size = 0;
  }

  public void add(int element) {
    if (size == elements.length) throw new IllegalStateException("Array is full.");
    int index = findLastLte(element) + 1;
    for (int i = size; i > index; --i) {
      elements[i] = elements[i - 1];
    }
    elements[index] = element;
    ++size;
  }

  public void delete(int element) {
    int index = binarySearch(element);
    if (index == -1) throw new NoSuchElementException();
    for (int i = index + 1; i < size; ++i) {
      elements[i - 1] = elements[i];
    }
    --size;
  }

  private int binarySearch(int target) {
    int low = 0, high = size - 1;
    while (low <= high) {
      int mid = low + (high - low) / 2;
      if (elements[mid] == target) return mid;
      if (elements[mid] < target) low = mid + 1;
      else high = mid - 1;
    }
    return -1;
  }

  private int findLastLte(int target) {
    int low = 0, high = size - 1;
    while (low <= high) {
      int mid = low + (high - low) / 2;
      if (elements[mid] <= target) {
        if (mid == size - 1 || elements[mid + 1] > target) return mid;
        low = mid + 1;
      } else {
        high = mid - 1;
      }
    }
    return -1;
  }

  @Override
  public String toString() {
    var content = IntStream.range(0, size)
      .mapToObj(i -> String.valueOf(elements[i]))
      .collect(Collectors.joining(", "));
    return "[" + content + "]";
  }
}
