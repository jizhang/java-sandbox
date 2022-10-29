package com.shzhangji.algorithm.array;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

// https://leetcode.com/problems/pascals-triangle-ii/
public class PascalTriangleII {
  public static void main(String[] args) {
    var obj = new PascalTriangleII();
    System.out.println(obj.getRow(3)); // [1, 3, 3, 1]
    System.out.println(obj.getRow(0)); // [1]
    System.out.println(obj.getRow(1)); // [1, 1]
  }

  // Follow up: Could you optimize your algorithm to use only O(rowIndex) extra space?
  public List<Integer> getRow(int rowIndex) {
    var result = new int[rowIndex + 1];

    for (int i = 0; i <= rowIndex; ++i) {
      for (int j = i; j >= 0; --j) {
        if (j == i || j == 0) result[j] = 1;
        else result[j] += result[j - 1];
      }
    }

    return Arrays.stream(result).boxed().collect(Collectors.toList());
  }
}
