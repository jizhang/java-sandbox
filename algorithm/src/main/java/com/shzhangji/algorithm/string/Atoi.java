package com.shzhangji.algorithm.string;

// https://leetcode.com/problems/string-to-integer-atoi/
public class Atoi {
  public static void main(String[] args) {
    var obj = new Atoi();
    System.out.println(obj.myAtoi("42"));
    System.out.println(obj.myAtoi("   -42"));
    System.out.println(obj.myAtoi("4193 with words"));
    System.out.println(obj.myAtoi("  0000000000012345678"));
  }

  public int myAtoi(String s) {
    int i = 0;
    while (i < s.length() && s.charAt(i) == ' ') ++i;
    if (i == s.length()) return 0; // "", " "

    int sign = 1;
    if (s.charAt(i) == '-') {
      sign = -1;
      ++i;
    } else if (s.charAt(i) == '+') {
      ++i;
    }
    if (i == s.length()) return 0; // "-"

    while (i < s.length() && s.charAt(i) == '0') ++i; // "-001"

    long result = 0;
    while (i < s.length() && s.charAt(i) >= '0' && s.charAt(i) <= '9') {
      result = result * 10 + (s.charAt(i) - '0') * sign;
      if (result >= Integer.MAX_VALUE) return Integer.MAX_VALUE;
      if (result <= Integer.MIN_VALUE) return Integer.MIN_VALUE;
      ++i;
    }

    return (int) result;
  }
}
