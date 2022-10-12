package com.shzhangji.algorithm.array;

// https://leetcode.com/problems/first-missing-positive/
// Use constant extra space.
public class MissingPositive {
  public static void main(String[] args) {
    var obj = new MissingPositive();
    System.out.println(obj.firstMissingPositive(new int[] { 1, 2, 0 })); // 3
    System.out.println(obj.firstMissingPositive(new int[] { 3, 4, -1, 1 })); // 2
    System.out.println(obj.firstMissingPositive(new int[] { 7, 8, 9, 11, 12 })); // 1
  }

  // https://leetcode.com/problems/first-missing-positive/discuss/858526/Cyclic-Sort-Explained
  public int firstMissingPositive(int[] nums) {
    int i = 0;
    while (i < nums.length) {
      if (nums[i] > 0 && nums[i] <= nums.length && nums[i] != nums[nums[i] - 1]) {
        swap(nums, i, nums[i] - 1);
      } else {
        ++i;
      }
    }

    for (i = 0; i < nums.length; ++i) {
      if (nums[i] != i + 1) {
        return i + 1;
      }
    }

    return nums.length + 1;
  }

  void swap(int[] nums, int a, int b) {
    int t = nums[a];
    nums[a] = nums[b];
    nums[b] = t;
  }
}
