package com.shzhangji.algorithm.graph;

// https://leetcode.com/problems/number-of-islands/
public class NumberOfIslands {
  public static void main(String[] args) {
    var obj = new NumberOfIslands();

    var grid = new char[][] {
      new char[] {'1', '1', '1', '1', '0'},
      new char[] {'1', '1', '0', '1', '0'},
      new char[] {'1', '1', '0', '0', '0'},
      new char[] {'0', '0', '0', '0', '0'},
    };
    System.out.println(obj.numIslands(grid)); // 1

    grid = new char[][] {
      new char[] {'1', '1', '0', '0', '0'},
      new char[] {'1', '1', '0', '0', '0'},
      new char[] {'0', '0', '1', '0', '0'},
      new char[] {'0', '0', '0', '1', '1'},
    };
    System.out.println(obj.numIslands(grid)); // 3
  }

  public int numIslands(char[][] grid) {
    int count = 0;
    for (int i = 0; i < grid.length; ++i) {
      for (int j = 0; j < grid[i].length; ++j) {
        if (grid[i][j] == '1') {
          dfs(grid, i, j);
          ++count;
        }
      }
    }
    return count;
  }

  void dfs(char[][] grid, int i, int j) {
    if (i < 0 || i >= grid.length || j < 0 || j >= grid[i].length || grid[i][j] == '0') {
      return;
    }
    grid[i][j] = '0';
    dfs(grid, i + 1, j);
    dfs(grid, i - 1, j);
    dfs(grid, i, j + 1);
    dfs(grid, i, j - 1);
  }
}
