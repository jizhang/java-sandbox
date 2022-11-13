package com.shzhangji.algorithm.binarysearch;

import java.util.Arrays;

// https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/
public class FirstAndLastPosition {
  public static void main(String[] args) {
    var obj = new FirstAndLastPosition();
    System.out.println(Arrays.toString(obj.searchRange(new int[] { 5, 7, 7, 8, 8, 10 }, 8))); // [3, 4]
    System.out.println(Arrays.toString(obj.searchRange(new int[] { 5, 7, 7, 8, 8, 10 }, 6))); // [-1, -1]
    System.out.println(Arrays.toString(obj.searchRange(new int[] {}, 0))); // [-1, -1]

    System.out.println(Arrays.toString(obj.searchRange(new int[] { 1 }, 1))); // [0, 0]
    System.out.println(Arrays.toString(obj.searchRange(new int[] { 1, 1, 1 }, 1))); // [0, 2]
  }

  public int[] searchRange(int[] nums, int target) {
    return new int[] { findFirst(nums, target), findLast(nums, target) };
  }

  int findFirst(int[] nums, int target) {
    int i = 0, j = nums.length - 1;
    while (i <= j) {
      int mid = i + (j - i) / 2;
      if (nums[mid] == target) {
        if (mid == 0 || nums[mid - 1] != target) return mid;
        j = mid - 1;
      } else if (nums[mid] > target) {
        j = mid - 1;
      } else {
        i = mid + 1;
      }
    }
    return -1;
  }

  int findLast(int[] nums, int target) {
    int i = 0, j = nums.length - 1;
    while (i <= j) {
      int mid = i + (j - i) / 2;
      if (nums[mid] == target) {
        if (mid == nums.length - 1 || nums[mid + 1] != target) return mid;
        i = mid + 1;
      } else if (nums[mid] < target) {
        i = mid + 1;
      } else {
        j = mid - 1;
      }
    }
    return -1;
  }
}
