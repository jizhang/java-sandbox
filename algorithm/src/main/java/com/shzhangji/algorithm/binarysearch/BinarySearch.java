package com.shzhangji.algorithm.binarysearch;

// https://leetcode.com/problems/binary-search/
public class BinarySearch {
  public static void main(String[] args) {
    var obj = new BinarySearch();
    System.out.println(obj.search(new int[] { -1, 0, 3, 5, 9, 12 }, 9)); // 4
  }

  public int search(int[] nums, int target) {
    int low = 0;
    int high = nums.length - 1;

    while (low <= high) { // 1. <=
      int mid = low + (high - low) / 2; // 2. Integer overflow
      if (nums[mid] == target) return mid;
      if (nums[mid] < target) low = mid + 1; // 3. Not low = mid
      else high = mid - 1;
    }

    return -1;
  }
}
