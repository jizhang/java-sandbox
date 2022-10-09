package com.shzhangji.algorithm.sort;

import java.util.Arrays;

public class InsertionSort {
  public static void main(String[] args) {
    var arr = new int[] { 4, 5, 6, 1, 3, 2 };
    sort(arr);
    System.out.println(Arrays.toString(arr));
  }

  static void sort(int[] arr) {
    for (int i = 1; i < arr.length; ++i) {
      int value = arr[i];
      int j;
      for (j = i - 1; j >= 0; --j) {
        if (arr[j] > value) {
          arr[j + 1] = arr[j];
        } else {
          break;
        }
      }
      arr[j + 1] = value;
    }
  }
}
