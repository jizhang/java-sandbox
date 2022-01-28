package com.shzhangji.javasandbox.algorithm.backtracking;

public class Knapsack2 {
  static int maxValue = Integer.MIN_VALUE;

  public static void main(String[] args) {
    var values = new int[] { 20, 5, 10, 40, 15, 25 };
    var weights = new int [] { 1, 2, 3, 8, 7, 4 };
    fill(values, weights, 10, 0, 0, 0);
    System.out.println("Max value: " + maxValue);
  }

  static void fill(int[] values, int[] weights, int weight, int i, int currentValue, int currentWeight) {
    if (currentWeight == weight || i == weights.length) {
      if (currentValue > maxValue) {
        maxValue = currentValue;
      }
      return;
    }

    fill(values, weights, weight, i + 1, currentValue, currentWeight);
    if (currentWeight + weights[i] <= weight) {
      fill(values, weights, weight, i + 1, currentValue + values[i], currentWeight + weights[i]);
    }
  }
}
