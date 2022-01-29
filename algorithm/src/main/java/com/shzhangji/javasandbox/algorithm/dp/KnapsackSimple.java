package com.shzhangji.javasandbox.algorithm.dp;

public class KnapsackSimple {
  public static void main(String[] args) {
    var items = new int[] { 2, 2, 4, 6, 3 };
    System.out.println("Max weight: " + knapsack1(items, 9));
    System.out.println("Max weight: " + knapsack2(items, 9));
  }

  static int knapsack1(int[] items, int weight) {
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

  static int knapsack2(int[] items, int weight) {
    var states = new boolean[weight + 1];

    states[0] = true;
    if (items[0] <= weight) {
      states[items[0]] = true;
    }

    for (int i = 1; i < items.length; ++i) {
      for (int j = weight - items[i]; j >= 0; --j) {
        if (states[j]) {
          states[j + items[i]] = true;
        }
      }
    }

    for (int j = weight; j >= 0; --j) {
      if (states[j]) {
        return j;
      }
    }

    return 0;
  }
}
