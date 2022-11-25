package com.shzhangji.algorithm.array;

import java.util.Arrays;

// https://leetcode.com/problems/next-permutation/
// https://www.nayuki.io/page/next-lexicographical-permutation-algorithm
public class NextPermutation {
  public static void main(String[] args) {
    var obj = new NextPermutation();
    var nums = new int[] {1, 2, 3};
    obj.nextPermutation(nums);
    System.out.println(Arrays.toString(nums)); // [1,3,2]

    nums = new int[] {3, 2, 1};
    obj.nextPermutation(nums);
    System.out.println(Arrays.toString(nums)); // [1,2,3]

    nums = new int[] {1,1,5};
    obj.nextPermutation(nums);
    System.out.println(Arrays.toString(nums)); // [1,5,1]

    nums = new int[] {0, 1, 2, 5, 3, 3, 0};
    obj.nextPermutation(nums);
    System.out.println(Arrays.toString(nums)); // [0,1,3,0,2,3,5]
  }

  // The replacement must be in place and use only constant extra memory.
  public void nextPermutation(int[] nums) {
    if (nums.length == 1) return;

    int i = nums.length - 2;
    while (i >= 0 && nums[i] >= nums[i + 1]) --i;

    if (i >= 0) {
      int j = nums.length - 1;
      while (j >= i && nums[j] <= nums[i]) --j;
      swap(nums, i, j);
    }

    reverse(nums, i + 1, nums.length - 1);
  }

  void swap(int[] nums, int i, int j) {
    int t = nums[i];
    nums[i] = nums[j];
    nums[j] = t;
  }

  void reverse(int[] nums, int i, int j) {
    while (i < j) swap(nums, i++, j--);
  }
}
