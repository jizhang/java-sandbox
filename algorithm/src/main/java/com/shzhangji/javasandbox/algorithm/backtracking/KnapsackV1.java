package com.shzhangji.javasandbox.algorithm.backtracking;

public class KnapsackV1 {
  static int maxWeight = Integer.MIN_VALUE;

  public static void main(String[] args) {
    var items = new int[] { 20, 5, 10, 40, 15, 25 };
    knapsack(items, 100, 0, 0);
    System.out.println("Max weight: " + maxWeight);
  }

  static void knapsack(int[] items, int weight, int i, int currentWeight) {
    if (currentWeight == weight || i == items.length) {
      if (currentWeight > maxWeight) {
        maxWeight = currentWeight;
      }
      return;
    }

    knapsack(items, weight, i + 1, currentWeight);
    if (currentWeight + items[i] <= weight) {
      knapsack(items, weight, i + 1, currentWeight + items[i]);
    }
  }
}
