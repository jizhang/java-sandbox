package com.shzhangji.algorithm.dp;

// https://leetcode.com/problems/super-egg-drop/
// https://www.geeksforgeeks.org/egg-dropping-puzzle-dp-11/
public class SuperEggDrop {
  public static void main(String[] args) {
    var obj = new SuperEggDrop();
    System.out.println(obj.superEggDrop(1, 2)); // 2
    System.out.println(obj.superEggDrop(2, 6)); // 3
    System.out.println(obj.superEggDrop(3, 14)); // 4
  }

  public int superEggDrop(int k, int n) {
    return bottomUpQuick(k, n);
  }

  int bottomUpQuick(int k, int n) {
    // dp[m][k] means with m moves and k eggs, the maximum number of floors that can be checked.
    var dp = new int[n + 1][k + 1];

    for (int i = 1; i <= n; ++i) {
      for (int j = 1; j <= k; ++j) {
        dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j] + 1;
      }

      if (dp[i][k] >= n) {
        return i;
      }
    }

    return n;
  }

  int bottomUp(int k, int n) {
    var dp = new int[k + 1][n + 1];
    for (int j = 1; j <= n; ++j) {
      dp[1][j] = j;
    }

    for (int i = 2; i <= k; ++i) {
      for (int j = 1; j <= n; ++j) {
        dp[i][j] = j;
        for (int x = 1; x <= j; ++x) {
          int tmp = Math.max(dp[i - 1][x - 1], dp[i][j - x]) + 1;
          if (tmp < dp[i][j]) dp[i][j] = tmp;
        }
      }
    }

    return dp[k][n];
  }

  int topDown(int k, int n) {
    return topDown(k, n, new int[k + 1][n + 1]);
  }

  int topDown(int k, int n, int[][] memo) {
    if (k == 1 || n <= 1) return n;
    if (memo[k][n] > 0) return memo[k][n];

    int result = n;
    for (int i = 1; i <= n; ++i) {
      int tmp = Math.max(topDown(k - 1, i - 1, memo), topDown(k, n - i, memo)) + 1;
      if (tmp < result) result = tmp;
    }

    memo[k][n] = result;
    return result;
  }
}
