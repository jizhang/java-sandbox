package com.shzhangji.javasandbox.algorithm.dp;

import lombok.AllArgsConstructor;

public class Knapsack {
  @AllArgsConstructor
  static class TestCase {
    int[] values;
    int[] weights;
    int weight;
    int maxValue;
  }

  static TestCase[] testCases = new TestCase[] {
    new TestCase(
      new int[] { 20, 5, 10, 40, 15, 25 },
      new int[] { 1, 2, 3, 8, 7, 4 },
      10, 60),
    new TestCase(
      new int[] { 55, 10, 47, 5, 4, 50, 8, 61, 85, 87 },
      new int[] { 95, 4, 60, 32, 23, 72, 80, 62, 65, 46 },
      269, 295),
    new TestCase(
      new int[] { 44, 46, 90, 72, 91, 40, 75, 35, 8, 54, 78, 40, 77, 15, 61, 17, 75, 29, 75, 63 },
      new int[] { 92, 4, 43, 83, 84, 68, 92, 82, 6, 44, 32, 18, 56, 83, 25, 96, 70, 48, 14, 58 },
      878, 1024),
  };

  public static void main(String[] args) {
    for (int i = 0; i < testCases.length; ++i){
      System.out.println("Case No." + (i + 1));
      System.out.println("Max value: " + knapsack1(testCases[i].values, testCases[i].weights, testCases[i].weight) + ", expected: " + testCases[i].maxValue);
      System.out.println("Max value: " + knapsack2(testCases[i].values, testCases[i].weights, testCases[i].weight) + ", expected: " + testCases[i].maxValue);
    }
  }

  static int knapsack1(int[] values, int[] weights, int weight) {
    var states = new int[values.length][weight + 1];
    for (int i = 0; i < values.length; ++i) {
      for (int j = 0; j <= weight; ++j) {
        states[i][j] = -1;
      }
    }

    states[0][0] = 0;
    if (weights[0] <= weight) {
      states[0][weights[0]] = values[0];
    }

    for (int i = 1; i < values.length; ++i) {
      for (int j = 0; j <= weight; ++j) {
        if (states[i - 1][j] >= 0) {
          states[i][j] = states[i - 1][j];
        }
      }

      for (int j = 0; j <= weight - weights[i]; ++j) {
        if (states[i - 1][j] >= 0) {
          int value = states[i - 1][j] + values[i];
          if (value > states[i][j + weights[i]]) {
            states[i][j + weights[i]] = value;
          }
        }
      }
    }

    int maxValue = -1;
    for (int j = weight; j >= 0; --j) {
      if (states[values.length - 1][j] > maxValue) {
        maxValue = states[values.length - 1][j];
      }
    }

    return maxValue;
  }

  static int knapsack2(int[] values, int[] weights, int weight) {
    var states = new int[weight + 1];
    for (int j = 0; j <= weight; ++j) {
      states[j] = -1;
    }

    states[0] = 0;
    if (weights[0] <= weight) {
      states[weights[0]] = values[0];
    }

    for (int i = 1; i < values.length; ++i) {
      for (int j = weight - weights[i]; j >= 0; --j) {
        if (states[j] >= 0) {
          int value = states[j] + values[i];
          if (value > states[j + weights[i]]) {
            states[j + weights[i]] = value;
          }
        }
      }
    }

    int maxValue = -1;
    for (int j = weight; j >= 0; --j) {
      if (states[j] > maxValue) {
        maxValue = states[j];
      }
    }

    return maxValue;
  }
}
