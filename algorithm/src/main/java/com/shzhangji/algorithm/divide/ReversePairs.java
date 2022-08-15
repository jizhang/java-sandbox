package com.shzhangji.algorithm.divide;

// https://leetcode.com/problems/reverse-pairs/
public class ReversePairs {
  public static void main(String[] args) {
    var obj = new ReversePairs();
    System.out.println(obj.reversePairs(new int[] { 1, 3, 2, 3, 1 })); // 2
    System.out.println(obj.reversePairs(new int[] { 2, 4, 3, 5, 1 })); // 3
  }

  int num;
  public int reversePairs(int[] nums) {
    num = 0;
    mergeSortCounting(nums, 0, nums.length - 1);
    return num;
  }

  void mergeSortCounting(int[] nums, int p, int r) {
    if (p >= r) return;
    int q = (p + r) / 2;
    mergeSortCounting(nums, p, q);
    mergeSortCounting(nums, q + 1, r);
    merge(nums, p, q, r);
  }

  void merge(int[] nums, int p, int q, int r) {
    int i, j, k;
    j = q + 1;
    for (i = p; i <= q; ++i) {
      while (j <= r && nums[i] > 2L * nums[j]) {
        ++j;
      }
      num += j - (q + 1);
    }

    var tmp = new int[r - p + 1];
    i = p;
    j = q + 1;
    k = 0;

    while (i <= q && j <= r) {
      if (nums[i] <= nums[j]) {
        tmp[k++] = nums[i++];
      } else {
        tmp[k++] = nums[j++];
      }
    }

    while (i <= q) {
      tmp[k++] = nums[i++];
    }

    while (j <= r) {
      tmp[k++] = nums[j++];
    }

    for (k = 0; k < tmp.length; ++k) {
      nums[p + k] = tmp[k];
    }
  }
}
