package com.shzhangji.javasandbox.algorithm.dp;

public class KnapsackV1 {
  public static void main(String[] args) {
    var items = new int[] { 2, 2, 4, 6, 3 };
    System.out.println("Max weight: " + knapsack(items, 9));
  }

  static int knapsack(int[] items, int weight) {
    var states = new boolean[items.length][weight + 1];

    states[0][0] = true;
    if (items[0] <= weight) {
      states[0][items[0]] = true;
    }

    for (int i = 1; i < items.length; ++i) {
      for (int j = 0; j <= weight; ++j) {
        if (states[i - 1][j]) {
          states[i][j] = true;
        }
      }

      for (int j = 0; j <= weight - items[i]; ++j) {
        if (states[i - 1][j]) {
          states[i][j + items[i]] = true;
        }
      }
    }

    for (int j = weight; j >= 0; --j) {
      if (states[items.length - 1][j]) {
        return j;
      }
    }

    return 0;
  }
}
