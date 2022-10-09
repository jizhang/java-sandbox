package com.shzhangji.algorithm.sort;

import java.util.Arrays;

public class SelectionSort {
  public static void main(String[] args) {
    var arr = new int[] { 4, 5, 6, 3, 2, 1 };
    sort(arr);
    System.out.println(Arrays.toString(arr));
  }

  static void sort(int[] arr) {
    for (int i = 0; i < arr.length; ++i) {
      int minIndex = i;
      for (int j = i + 1; j < arr.length; ++j) {
        if (arr[j] < arr[minIndex]) {
          minIndex = j;
        }
      }
      if (minIndex != i) {
        swap(arr, minIndex, i);
      }
    }
  }

  static void swap(int[] arr, int a, int b) {
    int t = arr[a];
    arr[a] = arr[b];
    arr[b] = t;
  }
}
