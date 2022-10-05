package com.shzhangji.algorithm.heap;

import java.util.Arrays;

public class HeapSort {
  public static void main(String[] args) {
    var nums = new int[] { 0, 7, 5, 19, 8, 4, 1, 20, 13, 16 };
    heapSort(nums);
    System.out.println(Arrays.toString(nums));
  }

  static void heapSort(int[] nums) {
    buildHeap(nums); // Max heap
    for (int i = nums.length - 1; i > 1; --i) {
      swap(nums, 1, i);
      heapify(nums, i - 1, 1);
    }
  }

  static void buildHeap(int[] nums) {
    for (int i = (nums.length - 1) / 2; i >= 1; --i) {
      heapify(nums, nums.length - 1, i);
    }
  }

  static void heapify(int[] nums, int n, int i) {
    while (true) { // From top
      int maxPos = i;
      int left = i * 2, right = i * 2 + 1;
      if (left <= n && nums[maxPos] < nums[left]) maxPos = left;
      if (right <= n && nums[maxPos] < nums[right]) maxPos = right;
      if (maxPos == i) break;
      swap(nums, i, maxPos);
      i = maxPos;
    }
  }

  static void swap(int[] nums, int i, int j) {
    int t = nums[i];
    nums[i] = nums[j];
    nums[j] = t;
  }
}
