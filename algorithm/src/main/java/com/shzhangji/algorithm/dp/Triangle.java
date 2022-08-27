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
    var states = new int[rows];
    states[0] = triangle.get(0).get(0);
    for (int i = 1; i < rows; ++i) {
      for (int j = i; j >= 0; --j) { // Iterate backward
        if (j == 0) {
          states[j] += triangle.get(i).get(j);
        } else if (j == i) {
          states[j] = states[j - 1] + triangle.get(i).get(j);
        } else {
          states[j] = Math.min(states[j - 1], states[j]) + triangle.get(i).get(j);
        }
      }
    }

    int result = Integer.MAX_VALUE;
    for (int j = 0; j < rows; ++j) {
      result = Math.min(result, states[j]);
    }
    return result;
  }
}
