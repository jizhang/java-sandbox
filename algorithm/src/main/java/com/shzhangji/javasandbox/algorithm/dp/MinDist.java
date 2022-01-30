package com.shzhangji.javasandbox.algorithm.dp;

public class MinDist {
  public static void main(String[] args) {
    var matrix = new int[][] {
      { 1, 3, 5, 9 },
      { 2, 1, 3, 4 },
      { 5, 2, 6, 7 },
      { 6, 8, 4, 3 },
    };
    System.out.println("Result: " + minDist(matrix));
  }

  static int minDist(int[][] matrix) {
    var states = new int[matrix.length][matrix.length];

    states[0][0] = matrix[0][0];
    for (int i = 1; i < matrix.length; ++i) {
      states[i][0] = states[i - 1][0] + matrix[i][0];
    }
    for (int j = 1; j < matrix.length; ++j) {
      states[0][j] = states[0][j - 1] + matrix[0][j];
    }

    for (int i = 1; i < matrix.length; ++i) {
      for (int j = 1; j < matrix.length; ++j) {
        states[i][j] = Math.min(states[i][j - 1], states[i - 1][j]) + matrix[i][j];
      }
    }

    return states[matrix.length - 1][matrix.length - 1];
  }
}
