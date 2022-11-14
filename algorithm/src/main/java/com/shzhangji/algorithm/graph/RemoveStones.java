package com.shzhangji.algorithm.graph;

// https://leetcode.com/problems/most-stones-removed-with-same-row-or-column/
public class RemoveStones {
  public static void main(String[] args) {
    var obj = new RemoveStones();
    System.out.println(obj.removeStones(new int[][] {
      new int[] { 0, 0 },
      new int[] { 0, 1 },
      new int[] { 1, 0 },
      new int[] { 1, 2 },
      new int[] { 2, 1 },
      new int[] { 2, 2 },
    })); // 5
    System.out.println(obj.removeStones(new int[][] {
      new int[] { 0, 0 },
      new int[] { 0, 2 },
      new int[] { 1, 1 },
      new int[] { 2, 0 },
      new int[] { 2, 2 },
    })); // 3
    System.out.println(obj.removeStones(new int[][] {
      new int[] { 0, 0 },
    })); // 0
  }

  public int removeStones(int[][] stones) {
    var matrix = new boolean[stones.length][stones.length];
    for (int i = 0; i < stones.length; ++i) {
      for (int j = 0; j < stones.length; ++j) {
        if (stones[i][0] == stones[j][0] || stones[i][1] == stones[j][1]) {
          matrix[i][j] = true;
        }
      }
    }

    var visited = new boolean[stones.length];
    int count = 0;
    for (int i = 0; i < matrix.length; ++i) {
      if (!visited[i]) {
        dfs(matrix, i, visited);
        ++count;
      }
    }

    return stones.length - count;
  }

  void dfs(boolean[][] matrix, int i, boolean[] visited) {
    if (visited[i]) return;
    visited[i] = true;
    for (int j = 1; j < matrix[i].length; ++j) {
      if (matrix[i][j]) dfs(matrix, j, visited);
    }
  }
}
