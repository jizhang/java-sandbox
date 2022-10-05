package com.shzhangji.algorithm.dp;

import java.util.Arrays;

//https://leetcode.com/problems/knight-probability-in-chessboard/
public class KnightProbability {
  public static void main(String[] args) {
    var obj = new KnightProbability();
    System.out.println(obj.knightProbability(3, 2, 0, 0)); // 0.0625
    System.out.println(obj.knightProbability(1, 0, 0, 0)); // 1.0
    System.out.println(obj.knightProbability(8, 30, 6, 4)); // 0.00019
  }

  public double knightProbability(int n, int k, int row, int column) {
    return bottomUp2(n, k, row, column);
  }

  double topDown(int n, int k, int row, int column) {
    var memo = new Double[k + 1][n][n];
    return topDown(n, k, row, column, memo);
  }

  double topDown(int n, int k, int row, int column, Double[][][] memo) {
    if (row < 0 || row >= n || column < 0 || column >= n) return 0;
    if (k == 0) return 1;
    if (memo[k][row][column] != null) return memo[k][row][column];
    double numerator = topDown(n, k - 1, row + 1, column + 2, memo)
      + topDown(n, k - 1, row + 1, column - 2, memo)
      + topDown(n, k - 1, row - 1, column + 2, memo)
      + topDown(n, k - 1, row - 1, column - 2, memo)
      + topDown(n, k - 1, row + 2, column + 1, memo)
      + topDown(n, k - 1, row + 2, column - 1, memo)
      + topDown(n, k - 1, row - 2, column + 1, memo)
      + topDown(n, k - 1, row - 2, column - 1, memo);
    memo[k][row][column] = numerator / 8;
    return memo[k][row][column];
  }

  double bottomUp(int n, int k, int row, int column) {
    var dp = new double[k + 1][n][n];
    Arrays.stream(dp[0]).forEach(arr -> Arrays.fill(arr, 1.0));
    var dirs = new int[][] {{1, 2}, {1, -2}, {-1, 2}, {-1, -2}, {2, 1}, {2, -1}, {-2, 1}, {-2, -1}};

    for (int i = 1; i <= k; ++i) {
      for (int x = 0; x < n; ++x) {
        for (int y = 0; y < n; ++y) {
          for (var dir : dirs) {
            if (x + dir[0] < 0 || x + dir[0] >= n || y + dir[1] < 0 || y + dir[1] >= n) continue;
            dp[i][x][y] += dp[i - 1][x + dir[0]][y + dir[1]];
          }
          dp[i][x][y] /= 8;
        }
      }
    }

    return dp[k][row][column];
  }

  double bottomUp2(int n, int k, int row, int column) {
    var curr = new double[n][n];
    var dirs = new int[][] {{1, 2}, {1, -2}, {-1, 2}, {-1, -2}, {2, 1}, {2, -1}, {-2, 1}, {-2, -1}};

    curr[row][column] = 1;
    for (int i = 1; i <= k; ++i) {
      var next = new double[n][n];
      for (int x = 0; x < n; ++x) {
        for (int y = 0; y < n; ++y) {
          for (var dir : dirs) {
            if (x + dir[0] < 0 || x + dir[0] >= n || y + dir[1] < 0 || y + dir[1] >= n) continue;
            next[x + dir[0]][y + dir[1]] += curr[x][y] / 8;
          }
        }
      }
      curr = next;
    }

    return Arrays.stream(curr).flatMapToDouble(Arrays::stream).sum();
  }
}
