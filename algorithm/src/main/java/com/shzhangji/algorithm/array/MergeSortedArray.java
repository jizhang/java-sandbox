package com.shzhangji.algorithm.array;

import java.util.Arrays;

// https://leetcode.com/problems/merge-sorted-array/
public class MergeSortedArray {
  public static void main(String[] args) {
    var obj = new MergeSortedArray();
    var nums1 = new int[] { 1, 2, 3, 0, 0, 0 };
    var nums2 = new int[] { 2, 5, 6 };
    obj.merge(nums1, 3, nums2, 3);
    System.out.println(Arrays.toString(nums1));
  }

  public void merge(int[] nums1, int m, int[] nums2, int n) {
    int i = m - 1, j = n - 1, k = m + n - 1;
    while (i >= 0 && j >= 0) {
      if (nums1[i] > nums2[j]) {
        nums1[k--] = nums1[i];
        --i;
      } else {
        nums1[k--] = nums2[j];
        --j;
      }
    }

    while (j >= 0) {
      nums1[k--] = nums2[j--];
    }
  }
}
