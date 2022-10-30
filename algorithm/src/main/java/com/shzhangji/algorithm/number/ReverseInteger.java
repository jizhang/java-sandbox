package com.shzhangji.algorithm.number;

// https://leetcode.com/problems/reverse-integer/
public class ReverseInteger {
  public static void main(String[] args) {
    var obj = new ReverseInteger();
    System.out.println(obj.reverse(123)); // 321
    System.out.println(obj.reverse(-123)); // -321
    System.out.println(obj.reverse(120)); // 21

    System.out.println(obj.reverse(1463847413)); // 0
  }

  // Assume the environment does not allow you to store 64-bit integers (signed or unsigned).
  public int reverse(int x) {
    int result = 0;
    while (x != 0) {
      if (result > Integer.MAX_VALUE / 10 || result < Integer.MIN_VALUE / 10) {
        return 0;
      }
      result = result * 10 + x % 10;
      x /= 10;
    }
    return result;
  }
}
