package com.shzhangji.algorithm.dp;

// https://leetcode.com/problems/maximum-product-subarray/
// https://zhuanlan.zhihu.com/p/150516970
public class MaximumProductSubarray {
  public static void main(String[] args) {
    var obj = new MaximumProductSubarray();
    System.out.println(obj.maxProduct(new int[] { 2, 3, -2, 4 })); // 6
    System.out.println(obj.maxProduct(new int[] { -2, 0, -1 })); // 0
    System.out.println(obj.maxProduct(new int[] { 7, -2, -4 })); // 56
  }

  public int maxProduct(int[] nums) {
    return bottomUp(nums);
  }

  int bottomUp(int[] nums) {
    var dpMax = new int[nums.length];
    var dpMin = new int[nums.length];
    int result;
    result = dpMax[0] = dpMin[0] = nums[0];

    for (int i = 1; i < nums.length; ++i) {
      if (nums[i] >= 0) {
        dpMax[i] = Math.max(dpMax[i - 1] * nums[i], nums[i]);
        dpMin[i] = Math.min(dpMin[i - 1] * nums[i], nums[i]);
      } else {
        dpMax[i] = Math.max(dpMin[i - 1] * nums[i], nums[i]);
        dpMin[i] = Math.min(dpMax[i - 1] * nums[i], nums[i]);
      }
      result = Math.max(result, dpMax[i]);
    }

    return result;
  }
}
