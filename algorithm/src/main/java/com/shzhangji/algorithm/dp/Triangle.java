package com.shzhangji.algorithm.dp;

import java.util.List;

// https://leetcode.com/problems/triangle/
public class Triangle {
  public static void main(String[] args) {
    var obj = new Triangle();
    System.out.println(obj.minimumTotal(List.of(
      List.of(2),
      List.of(3, 4),
      List.of(6, 5, 7),
      List.of(4, 1, 8, 3)
    ))); // 11;
    System.out.println(obj.minimumTotal(List.of(List.of(-10)))); // -10
  }

  public int minimumTotal(List<List<Integer>> triangle) {
    int rows = triangle.size();
    var states = new int[rows][rows];
    states[0][0] = triangle.get(0).get(0);
    for (int i = 1; i < rows; ++i) {
      states[i][0] = states[i - 1][0] + triangle.get(i).get(0);
    }

    for (int i = 1; i < rows; ++i) {
      for (int j = 1; j <= i; ++j) {
        if (j == i) {
          states[i][j] = states[i - 1][j - 1] + triangle.get(i).get(j);
        } else {
          states[i][j] = Math.min(states[i - 1][j - 1], states[i - 1][j]) + triangle.get(i).get(j);
        }
      }
    }

    int result = Integer.MAX_VALUE;
    for (int j = 0; j < rows; ++j) {
      if (states[rows - 1][j] < result) {
        result = states[rows - 1][j];
      }
    }
    return result;
  }
}
