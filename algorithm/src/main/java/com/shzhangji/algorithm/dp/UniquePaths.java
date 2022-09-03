package com.shzhangji.algorithm.dp;

// https://leetcode.com/problems/unique-paths/
public class UniquePaths {
  public static void main(String[] args) {
    var obj = new UniquePaths();
    System.out.println(obj.uniquePaths(3, 7)); // 28
    System.out.println(obj.uniquePaths(3, 2)); // 3
  }

  public int uniquePaths(int m, int n) {
    return bottomUp(m, n);
  }

  int bottomUp(int m, int n) {
    var dp = new int[m][n];
    for (int i = 0; i < m; ++i) {
      dp[i][0] = 1;
    }
    for (int j = 0; j < n; ++j) {
      dp[0][j] = 1;
    }

    for (int i = 1; i < m; ++i) {
      for (int j = 1; j < n; ++j) {
        dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
      }
    }

    return dp[m - 1][n - 1];
  }
}
