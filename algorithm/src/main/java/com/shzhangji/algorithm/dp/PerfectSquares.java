package com.shzhangji.algorithm.dp;

// https://leetcode.com/problems/perfect-squares/
public class PerfectSquares {
  public static void main(String[] args) {
    var obj = new PerfectSquares();
    System.out.println(obj.numSquares(12)); // 3
    System.out.println(obj.numSquares(13)); // 2

    System.out.println(obj.numSquares(2)); // 2
  }

  public int numSquares(int n) {
    return bottomUp(n);
  }

  int topDown(int n) {
    return recur(n, new int[n + 1]);
  }

  int recur(int n, int[] memo) {
    if (n == 0) return 0;
    if (memo[n] > 0) return memo[n];

    int min = Integer.MAX_VALUE;
    for (int i = 1; i * i <= n; ++i) {
      min = Math.min(min, recur(n - i * i, memo));
    }
    memo[n] = min + 1;
    return memo[n];
  }

  int bottomUp(int n) {
    var dp = new int[n + 1];
    for (int i = 1; i <= n; ++i) {
      dp[i] = Integer.MAX_VALUE;
      for (int j = 1; j * j <= i; ++j) {
        dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
      }
    }
    return dp[n];
  }
}
