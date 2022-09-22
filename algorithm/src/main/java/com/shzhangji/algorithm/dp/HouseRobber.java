package com.shzhangji.algorithm.dp;

import java.util.Arrays;

// https://leetcode.com/problems/house-robber/
public class HouseRobber {
  public static void main(String[] args) {
    var obj = new HouseRobber();
    System.out.println(obj.rob(new int[] { 1, 2, 3, 1 })); // 4
    System.out.println(obj.rob(new int[] { 2, 7, 9, 3, 1 })); // 12
  }

  public int rob(int[] nums) {
    return bottomUp(nums);
  }

  int topDown(int[] nums) {
    var memo = new int[nums.length + 1];
    Arrays.fill(memo, -1);
    return topDown(nums, nums.length, memo);
  }

  int topDown(int[] nums, int i, int[] memo) {
    if (i == 1) return nums[i - 1];
    if (i == 2) return Math.max(nums[i - 1], nums[i - 2]);
    if (memo[i] != -1) return memo[i];
    memo[i] = Math.max(topDown(nums, i - 2, memo) + nums[i - 1], topDown(nums, i - 1, memo));
    return memo[i];
  }

  int bottomUp(int[] nums) {
    if (nums.length == 1) return nums[0];
    var dp = new int[nums.length + 1];
    dp[1] = nums[0];
    dp[2] = Math.max(nums[0], nums[1]);
    for (int i = 3; i <= nums.length; ++i) {
      dp[i] = Math.max(dp[i - 2] + nums[i - 1], dp[i - 1]);
    }
    return dp[nums.length];
  }
}
