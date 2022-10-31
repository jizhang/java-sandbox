package com.shzhangji.algorithm.number;

// https://leetcode.com/problems/excel-sheet-column-title/
public class ExcelColumnTitle {
  public String convertToTitle(int columnNumber) {
    var sb = new StringBuilder();
    while (columnNumber >= 1) {
      sb.append((char) ('A' + ((columnNumber - 1) % 26)));
      columnNumber = (columnNumber - 1) / 26;
    }
    return sb.reverse().toString();
  }
}
