package com.shzhangji.algorithm.search;

// https://leetcode.com/problems/search-in-rotated-sorted-array/
public class SearchRotated {
  public static void main(String[] args) {
    var obj = new SearchRotated();
    System.out.println(obj.search(new int[] { 4, 5, 6, 7, 0, 1, 2 }, 0)); // 4
    System.out.println(obj.search(new int[] { 4, 5, 6, 7, 0, 1, 2 }, 3)); // -1
    System.out.println(obj.search(new int[] { 1 }, 0)); // -1
  }

  public int search(int[] nums, int target) {
    int low = 0, high = nums.length - 1;
    while (low <= high) {
      int mid = low + (high - low) / 2;
      if (nums[mid] == target) return mid;

      if (nums[low] <= nums[mid]) {
        if (target >= nums[low] && target < nums[mid]) {
          high = mid - 1;
        } else {
          low = mid + 1;
        }
      } else {
        if (target > nums[mid] && target <= nums[high]) {
          low = mid + 1;
        } else {
          high = mid - 1;
        }
      }
    }

    return -1;
  }
}
