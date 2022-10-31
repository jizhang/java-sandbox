package com.shzhangji.algorithm.number;

// https://leetcode.com/problems/excel-sheet-column-number/
public class ExcelColumnNumber {
  public static void main(String[] args) {
    var obj = new ExcelColumnNumber();
    System.out.println(obj.titleToNumber("A")); // 1
    System.out.println(obj.titleToNumber("AB")); // 28
    System.out.println(obj.titleToNumber("ZY")); // 701
  }

  public int titleToNumber(String columnTitle) {
    return sol3(columnTitle);
  }

  int sol1(String columnTitle) {
    int result = 0;
    for (int i = 0; i < columnTitle.length(); ++i) {
      result += (columnTitle.charAt(columnTitle.length() - i - 1) - 'A' + 1) * Math.pow(26, i);
    }
    return result;
  }

  int sol2(String columnTitle) {
    int result = 0;
    for (char c : columnTitle.toCharArray()) {
      result = result * 26 + (c - 'A' + 1);
    }
    return result;
  }

  int sol3(String columnTitle) {
    return columnTitle.chars().reduce(0, (r, c) -> r * 26 + (c - 'A' + 1));
  }
}
