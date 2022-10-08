package com.shzhangji.algorithm.dp;

import java.util.Arrays;

// https://leetcode.com/problems/minimum-cost-tree-from-leaf-values/
public class MinimumCostTree {
  public static void main(String[] args) {
    var obj = new MinimumCostTree();
    System.out.println(obj.mctFromLeafValues(new int[] { 6, 2, 4 })); // 32
    System.out.println(obj.mctFromLeafValues(new int[] { 4, 11 })); // 44
  }

  public int mctFromLeafValues(int[] arr) {
    return topDown(arr);
  }

  int topDown(int[] arr) {
    var memo = new int[arr.length][arr.length];
    return topDown(arr, 0, arr.length - 1, memo);
  }

  int topDown(int[] arr, int start, int end, int[][] memo) {
    if (start >= end) return 0;
    if (memo[start][end] > 0) return memo[start][end];

    int result = Integer.MAX_VALUE;
    for (int i = start; i < end; ++i) {
      int left = Arrays.stream(arr, start, i + 1).max().orElseThrow();
      int right = Arrays.stream(arr, i + 1, end + 1).max().orElseThrow();
      result = Math.min(result, left * right + topDown(arr, start, i, memo) + topDown(arr, i + 1, end, memo));
    }

    memo[start][end] = result;
    return result;
  }
}
