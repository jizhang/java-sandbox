package com.shzhangji.algorithm.sort;

import java.util.Arrays;

public class BubbleSort {
  public static void main(String[] args) {
    var arr = new int[] { 4, 5, 6, 3, 2, 1 };
    sort(arr);
    System.out.println(Arrays.toString(arr));
  }

  static void sort(int[] arr) {
    for (int i = 0; i < arr.length; ++i) {
      boolean swapped = false; // Optional
      for (int j = 0; j < arr.length - i - 1; ++j) {
        if (arr[j] > arr[j + 1]) { // Bubble the largest element toward end.
          swap(arr, j, j + 1);
          swapped = true;
        }
      }
      if (!swapped) break;
    }
  }

  static void swap(int[] arr, int a, int b) {
    int t = arr[a];
    arr[a] = arr[b];
    arr[b] = t;
  }
}
