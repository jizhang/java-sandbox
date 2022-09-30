package com.shzhangji.algorithm.dp;

import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/ones-and-zeroes/
public class OnesAndZeroes {
  public static void main(String[] args) {
    var obj = new OnesAndZeroes();
    System.out.println(obj.findMaxForm(new String[] { "10", "0001", "111001", "1", "0" }, 5, 3)); // 4
    System.out.println(obj.findMaxForm(new String[] { "10", "0", "1" }, 1, 1)); // 2
  }

  public int findMaxForm(String[] strs, int m, int n) {
    return topDown(strs, m, n);
  }

  int topDown(String[] strs, int m, int n) {
    var memo = new Integer[strs.length][m + 1][n + 1];
    var zeroesMemo = new HashMap<String, Integer>();
    return topDown(strs, m, n, strs.length - 1, memo, zeroesMemo);
  }

  int topDown(String[] strs, int m, int n, int i, Integer[][][] memo, Map<String, Integer> zeroesMemo) {
    if (i == -1) return 0;
    if (memo[i][m][n] != null) return memo[i][m][n];

    int zeroes = zeroesMemo.computeIfAbsent(strs[i], str -> (int) str.chars().filter(c -> c == '0').count());
    int ones = strs[i].length() - zeroes;

    int result = topDown(strs, m, n, i - 1, memo, zeroesMemo);
    if (zeroes <= m && ones <= n) {
      result = Math.max(result, topDown(strs, m - zeroes, n - ones, i - 1, memo, zeroesMemo) + 1);
    }

    memo[i][m][n] = result;
    return result;
  }

  int bottomUp3D(String[] strs, int m, int n) {
    var dp = new int[strs.length + 1][m + 1][n + 1];

    for (int i = 1; i <= strs.length; ++i) {
      int zeroes = 0;
      int ones = 0;
      for (char c : strs[i - 1].toCharArray()) {
        if (c == '0') ++zeroes;
        else ++ones;
      }

      for (int j = 0; j <= m; ++j) {
        for (int k = 0; k <= n; ++k) {
          if (zeroes <= j && ones <= k) {
            dp[i][j][k] = Math.max(dp[i - 1][j][k], dp[i - 1][j - zeroes][k - ones] + 1);
          } else {
            dp[i][j][k] = dp[i - 1][j][k];
          }
        }
      }
    }

    return dp[strs.length][m][n];
  }

  int bottomUp2D(String[] strs, int m, int n) {
    var dp = new int[m + 1][n + 1];

    for (int i = 1; i <= strs.length; ++i) {
      int zeroes = 0;
      int ones = 0;
      for (char c : strs[i - 1].toCharArray()) {
        if (c == '0') ++zeroes;
        else ++ones;
      }

      for (int j = m; j >= zeroes; --j) { // Reversed loop
        for (int k = n; k >= ones; --k) {
          dp[j][k] = Math.max(dp[j][k], dp[j - zeroes][k - ones] + 1);
        }
      }
    }

    return dp[m][n];
  }
}
