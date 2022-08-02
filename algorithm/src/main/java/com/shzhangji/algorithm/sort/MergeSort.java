package com.shzhangji.algorithm.sort;

import java.util.Arrays;

public class MergeSort {
  public static void main(String[] args) {
    var nums = new int[] { 11, 8, 3, 9, 7, 1, 2, 5 };
    mergeSort(nums);
    System.out.println(Arrays.toString(nums));
  }

  static void mergeSort(int[] nums) {
    mergeSort0(nums, 0, nums.length - 1);
  }

  static void mergeSort0(int[] nums, int p, int r) {
    if (p >= r) return;
    int q = p + (r - p) / 2;
    mergeSort0(nums, p, q);
    mergeSort0(nums, q + 1, r);
    merge(nums, p, q, r);
  }

  static void merge(int[] nums, int p, int q, int r) {
    var tmp = new int[r - p + 1];
    int i = p, j = q + 1, k = 0;

    while (i <= q && j <= r) {
      if (nums[i] <= nums[j]) {
        tmp[k++] = nums[i++];
      } else {
        tmp[k++] = nums[j++];
      }
    }

    while (i <= q) tmp[k++] = nums[i++];
    while (j <= r) tmp[k++] = nums[j++];

    for (k = 0; k < tmp.length; ++k) {
      nums[p + k] = tmp[k];
    }
  }
}
