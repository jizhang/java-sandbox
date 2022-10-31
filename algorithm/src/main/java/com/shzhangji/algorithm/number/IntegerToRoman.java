package com.shzhangji.algorithm.number;

import java.util.Map;

// https://leetcode.com/problems/integer-to-roman/
public class IntegerToRoman {
  public static void main(String[] args) {
    var obj = new IntegerToRoman();
    System.out.println(obj.intToRoman(3)); // III
    System.out.println(obj.intToRoman(58)); // LVIII
    System.out.println(obj.intToRoman(1994)); // MCMXCIV
  }

  public String intToRoman(int num) {
    var val = new int[] { 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };
    var str = new String[] { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" };
    var sb = new StringBuilder();
    for (int i = 0; i < val.length && num > 0; ++i) {
      while (num >= val[i]) {
        sb.append(str[i]);
        num -= val[i];
      }
    }

    return sb.toString();
  }
}
