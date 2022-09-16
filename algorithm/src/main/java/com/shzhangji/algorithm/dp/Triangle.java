package com.shzhangji.algorithm.dp;

import java.util.Arrays;
import java.util.List;

// https://leetcode.com/problems/triangle/
public class Triangle {
  public static void main(String[] args) {
    var obj = new Triangle();
    System.out.println(obj.minimumTotal(List.of(
      List.of(2),
      List.of(3, 4),
      List.of(6, 5, 7),
      List.of(4, 1, 8, 3)
    ))); // 11;
    System.out.println(obj.minimumTotal(List.of(List.of(-10)))); // -10
  }

  public int minimumTotal(List<List<Integer>> triangle) {
    return bottomUp(triangle);
  }

  // Literally from bottom level to top level.
  int bottomUp(List<List<Integer>> triangle) {
    var dp = new int[triangle.size() + 1];

    for (int i = triangle.size() - 1; i >= 0; --i) {
      for (int j = 0; j <= i; ++j) {
        dp[j] = triangle.get(i).get(j) + Math.min(dp[j], dp[j + 1]);
      }
    }

    return dp[0];
  }

  // DFS
  int topDown(List<List<Integer>> triangle) {
    var memo = new int[triangle.size()][triangle.size()];
    Arrays.stream(memo).forEach(arr -> Arrays.fill(arr, -1));
    return topDown(triangle, 0, 0, memo);
  }

  int topDown(List<List<Integer>> triangle, int i, int j, int[][] memo) {
    if (i == triangle.size()) return 0;
    if (memo[i][j] != -1) return memo[i][j];
    int result = triangle.get(i).get(j) + Math.min(topDown(triangle, i + 1, j, memo), topDown(triangle, i + 1, j + 1, memo));
    memo[i][j] = result;
    return result;
  }
}
