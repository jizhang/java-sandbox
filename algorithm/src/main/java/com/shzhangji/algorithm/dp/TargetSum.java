package com.shzhangji.algorithm.dp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/target-sum/
public class TargetSum {
  public static void main(String[] args) {
    var obj = new TargetSum();
    System.out.println(obj.findTargetSumWays(new int[] { 1, 1, 1, 1, 1 }, 3)); // 5
    System.out.println(obj.findTargetSumWays(new int[] { 1 }, 1)); // 1
    System.out.println(obj.findTargetSumWays(new int[] { 1 }, 2)); // 0
    System.out.println(obj.findTargetSumWays(new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 1 }, 1)); // 256
  }

  // 1 <= nums.length <= 20
  // 0 <= nums[i] <= 1000
  // 0 <= sum(nums[i]) <= 1000
  // -1000 <= target <= 1000
  public int findTargetSumWays(int[] nums, int target) {
    return topDown(nums, target);
  }

  int topDownNaive(int[] nums, int target) {
    var memo = new HashMap<String, Integer>(); // Use map instead of 2D array.
    return topDownNaive(nums, nums.length - 1, target, memo);
  }

  int topDownNaive(int[] nums, int i, int target, Map<String, Integer> memo) {
    if (i == -1) return target == 0 ? 1 : 0;
    var key = i + "," + target;
    if (memo.containsKey(key)) return memo.get(key);
    int result = topDownNaive(nums, i - 1, target - nums[i], memo) + topDownNaive(nums, i - 1, target + nums[i], memo);
    memo.put(key, result);
    return result;
  }

  int topDown(int[] nums, int target) {
    int sum = Arrays.stream(nums).sum();
    if (target > sum || target < -sum || (target + sum) % 2 != 0) return 0;
    return topDown(nums, nums.length - 1, (target + sum) / 2);
  }

  int topDown(int[] nums, int i, int target) { // No memo
    if (i == -1) return target == 0 ? 1 : 0;

    int result = topDown(nums, i - 1, target);
    if (nums[i] <= target) {
      result += topDown(nums, i - 1, target - nums[i]);
    }

    return result;
  }

  // https://zhuanlan.zhihu.com/p/265891102
  int bottomUp(int[] nums, int target) {
    int sum = Arrays.stream(nums).sum();
    if (target > sum || target < -sum || (target + sum) % 2 != 0) return 0;
    int t = (target + sum) / 2;
    var dp = new int[nums.length + 1][t + 1];
    dp[0][0] = 1; // 0 items, 0 target, 1 way

    for (int i = 1; i <= nums.length; ++i) {
      for (int j = 0; j <= t; ++j) {
        dp[i][j] = dp[i - 1][j];
        if (nums[i - 1] <= j) {
          dp[i][j] += dp[i - 1][j - nums[i - 1]];
        }
      }
    }

    return dp[nums.length][t];
  }

  int bottomUp1D(int[] nums, int target) {
    int sum = Arrays.stream(nums).sum();
    if (target > sum || target < -sum || (target + sum) % 2 != 0) return 0;
    int t = (target + sum) / 2;
    var dp = new int[t + 1];
    dp[0] = 1;
    for (int num : nums) {
      for (int j = t; j >= num; --j) {
        dp[j] += dp[j - num];
      }
    }
    return dp[t];
  }
}
