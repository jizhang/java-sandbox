package com.shzhangji.algorithm.dp;

import java.util.Arrays;

// https://leetcode.com/problems/combination-sum-iv/
public class CombinationSumIV {
  public static void main(String[] args) {
    var obj = new CombinationSumIV();
    System.out.println(obj.combinationSum4(new int[] {1, 2, 3}, 4)); // 7
    System.out.println(obj.combinationSum4(new int[] {9}, 3)); // 0
    System.out.println(obj.combinationSum4(new int[] {2,1,3}, 35)); // 0
  }

  public int combinationSum4(int[] nums, int target) {
    return bottomUp(nums, target);
  }

  int topDown(int[] nums, int target) {
    var memo = new int[target + 1];
    Arrays.fill(memo, -1);
    return topDownMemo(nums, target, memo);
  }

  int topDownMemo(int[] nums, int target, int[] memo) {
    if (target < 0) return 0;
    if (target == 0) return 1;
    if (memo[target] != -1) return memo[target];

    int count = 0;
    for (int num : nums) {
      count += topDownMemo(nums, target - num, memo);
    }
    memo[target] = count;
    return count;
  }

  int bottomUp(int[] nums, int target) {
    var dp = new int[target + 1];
    dp[0] = 1;

    for (int i = 1; i <= target; ++i) {
      for (int num : nums) {
        dp[i] += i >= num ? dp[i - num] : 0;
      }
    }

    return dp[target];
  }
}
