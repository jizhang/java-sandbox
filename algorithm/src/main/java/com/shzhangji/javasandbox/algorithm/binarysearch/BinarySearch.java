package com.shzhangji.javasandbox.algorithm.binarysearch;

public class BinarySearch {
  public static void main(String[] args) {
    var obj = new BinarySearch();
    System.out.println(obj.search(new int[] { -1, 0, 3, 5, 9, 12 }, 9)); // 4
  }

  // https://leetcode.com/problems/binary-search/
  public int search(int[] nums, int target) {
    int low = 0;
    int high = nums.length - 1;

    while (low <= high) {
      int mid = low + (high - low) / 2;
      if (nums[mid] == target) return mid;
      if (nums[mid] < target) low = mid + 1;
      else high = mid - 1;
    }

    return -1;
  }
}
