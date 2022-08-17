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

    boolean isNegative = false;
    if (s.charAt(i) == '-') {
      isNegative = true;
      ++i;
    } else if (s.charAt(i) == '+') {
      ++i;
    }
    if (i == s.length()) return 0; // "-"

    while (i < s.length() && s.charAt(i) == '0') ++i;

    int start = i;
    while (i < s.length() && s.charAt(i) >= '0' && s.charAt(i) <= '9') ++i;
    if (start == i) return 0; // "-a"

    if (i - start > 10) return isNegative ? Integer.MIN_VALUE : Integer.MAX_VALUE;

    long result = 0;
    int j = 0;
    while (j < i - start) {
      result += (s.charAt(start + j) - 48) * Math.pow(10, i - start - j - 1);
      ++j;
    }

    result *= isNegative ? -1 : 1;
    if (result > Integer.MAX_VALUE) return Integer.MAX_VALUE;
    if (result < Integer.MIN_VALUE) return Integer.MIN_VALUE;
    return (int) result;
  }
}
