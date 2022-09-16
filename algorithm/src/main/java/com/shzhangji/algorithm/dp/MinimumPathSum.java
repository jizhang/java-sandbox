package com.shzhangji.algorithm.dp;

// https://leetcode.com/problems/minimum-path-sum/
public class MinimumPathSum {
  public static void main(String[] args) {
    var obj = new MinimumPathSum();
    System.out.println(obj.minPathSum(new int[][] {
      new int[] { 1, 3, 1 },
      new int[] { 1, 5, 1 },
      new int[] { 4, 2, 1 },
    })); // 7
    System.out.println(obj.minPathSum(new int[][] {
      new int[] { 1, 2, 3 },
      new int[] { 4, 5, 6 },
    })); // 12
  }

  public int minPathSum(int[][] grid) {
    return topDown(grid);
  }

  int topDown(int[][] grid) {
    var memo = new Integer[grid.length][grid[0].length];
    return topDown(grid, 0, 0, memo);
  }

  int topDown(int[][] grid, int i, int j, Integer[][] memo) {
    if (i == grid.length - 1 && j == grid[0].length - 1) return grid[i][j];
    if (i == grid.length || j == grid[0].length) return Integer.MAX_VALUE;
    if (memo[i][j] != null) return memo[i][j];
    memo[i][j] = grid[i][j] + Math.min(topDown(grid, i + 1, j, memo), topDown(grid, i, j + 1, memo));
    return memo[i][j];
  }

  int bottomUp(int[][] grid) {
    var states = new int[grid.length][grid[0].length];

    states[0][0] = grid[0][0];
    for (int i = 1; i < states.length; ++i) {
      states[i][0] = states[i - 1][0] + grid[i][0];
    }
    for (int j = 1; j < states[0].length; ++j) {
      states[0][j] = states[0][j - 1] + grid[0][j];
    }

    for (int i = 1; i < states.length; ++i) {
      for (int j = 1; j < states[0].length; ++j) {
        states[i][j] = Math.min(states[i - 1][j], states[i][j - 1]) + grid[i][j];
      }
    }

    return states[states.length - 1][states[0].length - 1];
  }
}
