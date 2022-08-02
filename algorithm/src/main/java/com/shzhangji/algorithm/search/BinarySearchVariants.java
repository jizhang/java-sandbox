package com.shzhangji.algorithm.search;

public class BinarySearchVariants {
  public static void main(String[] args) {
    var nums = new int[] { 1, 3, 4, 5, 6, 8, 8, 8, 11, 18 };
    System.out.println(findFirst(nums, 8)); // 5
    System.out.println(findLast(nums, 8)); // 7

    System.out.println(findFirstGte(nums, 7)); // 5
    System.out.println(findFirstGte(nums, 5)); // 3

    System.out.println(findLastLte(nums, 9)); // 7
    System.out.println(findLastLte(nums, 11)); // 8
  }

  /**
   * Find the first element that equals to target.
   */
  static int findFirst(int[] nums, int target) {
    int low = 0, high = nums.length - 1;

    while (low <= high) {
      int mid = low + (high - low) / 2;
      if (nums[mid] > target) {
        high = mid - 1;
      } else if (nums[mid] < target) {
        low = mid + 1;
      } else {
        if (mid == 0 || (nums[mid - 1] != target)) return mid;
        high = mid - 1;
      }
    }

    return -1;
  }

  /**
   * Find the last element that equals to target.
   */
  static int findLast(int[] nums, int target) {
    int low = 0, high = nums.length - 1;

    while (low <= high) {
      int mid = low + (high - low) / 2;
      if (nums[mid] > target) {
        high = mid - 1;
      } else if (nums[mid] < target) {
        low = mid + 1;
      } else {
        if (mid == nums.length - 1 || (nums[mid + 1] != target)) return mid;
        low = mid + 1;
      }
    }

    return -1;
  }

  /**
   * Find the first element that is greater than or equals to target.
   */
  static int findFirstGte(int[] nums, int target) {
    int low = 0, high = nums.length - 1;

    while (low <= high) {
      int mid = low + (high - low) / 2;
      if (nums[mid] >= target) {
        if (mid == 0 || nums[mid - 1] < target) return mid;
        high = mid - 1;
      } else {
        low = mid + 1;
      }
    }

    return -1;
  }

  /**
   * Find the last element that is lesser than or equals to target.
   */
  static int findLastLte(int[] nums, int target) {
    int low = 0, high = nums.length - 1;

    while (low <= high) {
      int mid = low + (high - low) / 2;
      if (nums[mid] <= target) {
        if (mid == nums.length - 1 || nums[mid + 1] > target) return mid;
        low = mid + 1;
      } else {
        high = mid - 1;
      }
    }

    return -1;
  }
}
