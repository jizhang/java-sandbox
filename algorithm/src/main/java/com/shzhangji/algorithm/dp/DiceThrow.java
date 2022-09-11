package com.shzhangji.algorithm.dp;

import java.util.Arrays;

// https://leetcode.com/problems/number-of-dice-rolls-with-target-sum/
public class DiceThrow {
  public static void main(String[] args) {
    var obj = new DiceThrow();
    System.out.println(obj.numRollsToTarget(1, 6, 3)); // 1
    System.out.println(obj.numRollsToTarget(2, 6, 7)); // 6
    System.out.println(obj.numRollsToTarget(30, 30, 500)); // 222616187
  }

  static final int MODULO = (int) 1e9 + 7;

  public int numRollsToTarget(int n, int k, int target) {
    return topDown(n, k, target);
  }

  int topDown(int n, int k, int target) {
    var memo = new int[n + 1][target + 1];
    Arrays.stream(memo).forEach(arr -> Arrays.fill(arr, -1));
    return topDown(n, k, target, memo);
  }

  int topDown(int n, int k, int target, int[][] memo) {
    if (n == 1) return k >= target ? 1 : 0;
    if (memo[n][target] != -1) return memo[n][target];

    int result = 0;
    for (int i = 1; i <= k && i < target; ++i) {
      result = (result + topDown(n - 1, k, target - i, memo)) % MODULO;
    }

    memo[n][target] = result;
    return result;
  }

  int bottomUp(int n, int k, int target) {
    var dp = new int[n + 1][target + 1];
    for (int j = 1; j <= target && j <= k; ++j) {
      dp[1][j] = 1;
    }

    for (int i = 2; i <= n; ++i) {
      for (int j = 1; j <= target; ++j) {
        for (int x = 1; x <= k && x < j; ++x) {
          dp[i][j] = (dp[i][j] + dp[i - 1][j - x]) % MODULO;
        }
      }
    }

    return dp[n][target];
  }
}
