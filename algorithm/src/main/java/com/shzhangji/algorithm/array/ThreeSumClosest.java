package com.shzhangji.algorithm.array;

import java.util.Arrays;

// https://leetcode.com/problems/3sum-closest/
public class ThreeSumClosest {
  public static void main(String[] args) {
    var obj = new ThreeSumClosest();
    System.out.println(obj.threeSumClosest(new int[] { -1, 2, 1, -4 }, 1)); // 2
    System.out.println(obj.threeSumClosest(new int[] { 0, 0, 0 }, 1)); // 0
  }

  // You may assume that each input would have exactly one solution.
  public int threeSumClosest(int[] nums, int target) {
    Arrays.sort(nums);

    int result = Integer.MAX_VALUE;
    for (int i = 0; i < nums.length - 2; ++i) {
      int j = i + 1, k = nums.length - 1;
      while (j < k) {
        int sum = nums[i] + nums[j] + nums[k];
        if (sum == target) return sum;

        if (Math.abs(sum - target) < Math.abs(result - target)) {
          result = sum;
        }

        if (sum > target) {
          --k;
        } else {
          ++j;
        }
      }
    }

    return result;
  }
}
