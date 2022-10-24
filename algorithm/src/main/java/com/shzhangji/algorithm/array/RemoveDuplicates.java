package com.shzhangji.algorithm.array;

import java.util.Arrays;

// https://leetcode.com/problems/remove-duplicates-from-sorted-array/
public class RemoveDuplicates {
  public static void main(String[] args) {
    var obj = new RemoveDuplicates();
    var nums = new int[] { 1, 1, 2 };
    var k = obj.removeDuplicates(nums);
    print(nums, k); // [1, 2]

    nums = new int[] { 0, 0, 1, 1, 1, 2, 2, 3, 3, 4 };
    k = obj.removeDuplicates(nums);
    print(nums, k); // [0, 1, 2, 3, 4]
  }

  public int removeDuplicates(int[] nums) {
    int i = 0, j = 1;
    while (j < nums.length) {
      if (nums[j] != nums[i]) {
        nums[++i] = nums[j++];
      } else {
        ++j;
      }
    }
    return i + 1;
  }

  static void print(int[] nums, int k) {
    System.out.println(Arrays.toString(Arrays.copyOf(nums, k)));
  }
}
