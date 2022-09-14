package com.shzhangji.algorithm.dp;

// https://leetcode.com/problems/climbing-stairs/
public class ClimbingStairs {
  public static void main(String[] args) {
    var obj = new ClimbingStairs();
    System.out.println(obj.climbStairs(2)); // 2
    System.out.println(obj.climbStairs(3)); // 3
    System.out.println(obj.climbStairs(4)); // 5
    System.out.println(obj.climbStairs(45)); // 1836311903
  }

  public int climbStairs(int n) {
    return bottomUp(n);
  }

  int bottomUp(int n) {
    var dp = new int[n + 1];
    dp[1] = 1;
    dp[2] = 2;
    for (int i = 3; i <= n; ++i) {
      dp[i] = dp[i - 1] + dp[i - 2];
    }
    return dp[n];
  }

  int topDown(int n) {
    return topDown(n, new int[n + 1]);
  }

  int topDown(int n, int[] memo) {
    if (n == 1) return 1;
    if (n == 2) return 2;
    if (memo[n] > 0) return memo[n];
    int result = topDown(n - 1, memo) + topDown(n - 2, memo);
    memo[n] = result;
    return result;
  }
}
