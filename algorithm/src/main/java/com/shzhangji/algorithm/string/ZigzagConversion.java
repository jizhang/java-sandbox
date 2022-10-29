package com.shzhangji.algorithm.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

// https://leetcode.com/problems/zigzag-conversion/
public class ZigzagConversion {
  public static void main(String[] args) {
    var obj = new ZigzagConversion();
    System.out.println(obj.convert("PAYPALISHIRING", 3)); // PAHNAPLSIIGYIR
    System.out.println(obj.convert("PAYPALISHIRING", 4)); // PINALSIGYAHRPI
    System.out.println(obj.convert("A", 1)); // A

    System.out.println(obj.convert("AB", 1)); // AB
  }

  public String convert(String s, int numRows) {
    if (numRows == 1) return s;

    var rows = new StringBuilder[numRows];
    for (int i = 0; i < numRows; ++i) rows[i] = new StringBuilder();

    int rowIndex = 0;
    int direction = -1; // Will be reverted in the first loop.

    for (char c : s.toCharArray()) {
      rows[rowIndex].append(c);
      if (rowIndex == 0 || rowIndex == numRows - 1) direction *= -1;
      rowIndex += direction;
    }

    return String.join("", rows);
  }
}
