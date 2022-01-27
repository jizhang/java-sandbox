package com.shzhangji.javasandbox.algorithm.backtracking;

public class Knapsack {
  static int maxWeight = Integer.MIN_VALUE;

  public static void main(String[] args) {
    var items = new int[] { 20, 5, 10, 40, 15, 25};
    fill(items, 100, 0, 0);
    System.out.println("Max weight: " + maxWeight);
  }

  static void fill(int[] items, int weight, int i, int currentWeight) {
    if (currentWeight == weight || i == items.length) {
      if (currentWeight > maxWeight) {
        maxWeight = currentWeight;
      }
      return;
    }

    fill(items, weight, i + 1, currentWeight);
    if (currentWeight + items[i] <= weight) {
      fill(items, weight, i + 1, currentWeight + items[i]);
    }
  }
}
