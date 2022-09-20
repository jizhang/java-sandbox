package com.shzhangji.algorithm.dp;

import java.util.Arrays;

// https://www.geeksforgeeks.org/0-1-knapsack-problem-dp-10/
public class Knapsack {
  public static void main(String[] args) {
    var obj = new Knapsack();
    System.out.println(obj.knapsack(
      new int[] { 3, 4, 8, 9, 6},
      new int[] { 2, 2, 4, 6, 3 },
      9)); // 60
    System.out.println(obj.knapsack(
      new int[] { 20, 5, 10, 40, 15, 25 },
      new int[] { 1, 2, 3, 8, 7, 4 },
      10)); // 60
    System.out.println(obj.knapsack(
      new int[] { 55, 10, 47, 5, 4, 50, 8, 61, 85, 87 },
      new int[] { 95, 4, 60, 32, 23, 72, 80, 62, 65, 46 },
      269)); // 295
    System.out.println(obj.knapsack(
      new int[] { 44, 46, 90, 72, 91, 40, 75, 35, 8, 54, 78, 40, 77, 15, 61, 17, 75, 29, 75, 63 },
      new int[] { 92, 4, 43, 83, 84, 68, 92, 82, 6, 44, 32, 18, 56, 83, 25, 96, 70, 48, 14, 58 },
      878)); // 1024
  }

  int knapsack(int[] values, int[] weights, int weight) {
    return bottomUp1D(values, weights, weight);
  }

  int topDown(int[] values, int[] weights, int weight) {
    return topDown(values, weights, weight, weights.length - 1);
  }

  int topDown(int[] values, int[] weights, int weight, int i) {
    if (i == 0) return weights[i] > weight ? 0 : values[i]; // if (i == -1) return 0;

    if (weights[i] > weight) {
      return topDown(values, weights, weight, i - 1);
    }

    return Math.max(topDown(values, weights, weight, i - 1), values[i] + topDown(values, weights, weight - weights[i], i - 1));
  }

  int bottomUp(int[] values, int[] weights, int weight) {
    var dp = new int[weights.length + 1][weight + 1];

    for (int i = 1; i <= weights.length; ++i) {
      for (int j = 1; j <= weight; ++j) {
        if (weights[i - 1] > j) {
          dp[i][j] = dp[i - 1][j];
        } else {
          dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weights[i - 1]] + values[i - 1]);
        }
      }
    }

    return Arrays.stream(dp[weights.length]).max().orElse(0);
  }

  int bottomUp1D(int[] values, int[] weights, int weight) {
    var dp = new int[weight + 1];

    for (int i = 1; i <= weights.length; ++i) {
      for (int j = weight; j >= 1; --j) {
        if (weights[i - 1] <= j) {
          dp[j] = Math.max(dp[j], dp[j - weights[i - 1]] + values[i - 1]);
        }
      }
    }

    return Arrays.stream(dp).max().orElse(0);
  }
}
