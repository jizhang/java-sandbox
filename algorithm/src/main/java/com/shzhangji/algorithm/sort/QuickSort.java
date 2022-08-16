package com.shzhangji.algorithm.sort;

import java.util.Arrays;

public class QuickSort {
  public static void main(String[] args) {
    var nums = new int[] { 11, 8, 3, 9, 7, 1, 2, 5 };
    quickSort(nums);
    System.out.println(Arrays.toString(nums));
  }

  static void quickSort(int[] nums) {
    quickSort0(nums, 0, nums.length - 1);
  }

  static void quickSort0(int[] nums, int p, int r) {
    if (p >= r) return;
    int q = partition(nums, p, r);
    quickSort0(nums, p, q - 1);
    quickSort0(nums, q + 1, r);
  }

  static int partition(int[] nums, int p, int r) {
    int i = p, j = p;
    while (j < r) {
      if (nums[j] < nums[r]) {
        swap(nums, i, j);
        ++i;
        ++j;
      } else {
        ++j;
      }
    }
    swap(nums, i, r);
    return i;
  }

  static void swap(int[] nums, int a, int b) {
    int t = nums[a];
    nums[a] = nums[b];
    nums[b] = t;
  }
}
