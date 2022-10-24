package com.shzhangji.algorithm.array;

import java.util.Arrays;

// https://leetcode.com/problems/remove-duplicates-from-sorted-array/
public class RemoveElement {
  public static void main(String[] args) {
    var obj = new RemoveElement();
    var nums = new int[] { 3, 2, 2, 3 };
    var k = obj.removeElement(nums, 3);
    print(nums, k); // [2, 2]

    nums = new int[] { 0, 1, 2, 2, 3, 0, 4, 2 };
    k = obj.removeElement(nums, 2);
    print(nums, k); // [0, 1, 3, 0, 4]
  }

  public int removeElement(int[] nums, int val) {
    int i = 0, j = 0;
    while (j < nums.length) {
      if (nums[j] != val) {
        nums[i++] = nums[j];
      }
      ++j;
    }
    return i;
  }

  static void print(int[] nums, int k) {
    System.out.println(Arrays.toString(Arrays.copyOf(nums, k)));
  }
}
