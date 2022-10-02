package com.shzhangji.algorithm.dp;

// https://leetcode.com/problems/min-cost-climbing-stairs/
public class MinCostClimbingStairs {
  public static void main(String[] args) {
    var obj = new MinCostClimbingStairs();
    System.out.println(obj.minCostClimbingStairs(new int[] { 10, 15, 20 })); // 15
    System.out.println(obj.minCostClimbingStairs(new int[] { 1, 100, 1, 1, 1, 100, 1, 1, 100, 1 })); // 6
  }

  public int minCostClimbingStairs(int[] cost) {
    return bottomUp(cost);
  }

  int bottomUp(int[] cost) {
    var dp = new int[cost.length];
    dp[0] = cost[0];
    dp[1] = cost[1];

    for (int i = 2; i < cost.length; ++i) {
      dp[i] = Math.min(dp[i - 1], dp[i - 2]) + cost[i];
    }

    return Math.min(dp[cost.length - 2], dp[cost.length - 1]);
  }
}
