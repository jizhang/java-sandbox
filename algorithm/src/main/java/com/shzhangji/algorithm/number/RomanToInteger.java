package com.shzhangji.algorithm.number;

import java.util.Map;

// https://leetcode.com/problems/roman-to-integer/
public class RomanToInteger {
  public static void main(String[] args) {
    var obj = new RomanToInteger();
    System.out.println(obj.romanToInt("III")); // 3
    System.out.println(obj.romanToInt("LVIII")); // 58
    System.out.println(obj.romanToInt("MCMXCIV")); // 1994
  }

  public int romanToInt(String s) {
    var map = Map.of(
      'I', 1,
      'V', 5,
      'X', 10,
      'L', 50,
      'C', 100,
      'D', 500,
      'M', 1000);

    int result = 0;
    for (int i = 0; i < s.length(); ++i) {
      if (i < s.length() - 1 && map.get(s.charAt(i)) < map.get(s.charAt(i + 1))) {
        result -= map.get(s.charAt(i));
      } else {
        result += map.get(s.charAt(i));
      }
    }

    return result;
  }
}
