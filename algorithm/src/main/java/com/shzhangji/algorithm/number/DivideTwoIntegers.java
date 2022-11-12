package com.shzhangji.algorithm.number;

// https://leetcode.com/problems/divide-two-integers/
public class DivideTwoIntegers {
  public static void main(String[] args) {
    var obj = new DivideTwoIntegers();
    System.out.println(obj.divide(10, 3)); // 3
    System.out.println(obj.divide(7, -3)); // -2
  }

  // https://leetcode.com/problems/divide-two-integers/discuss/2091148/Java-solution-1ms-or-Bit-Manipulation
  public int divide(int dividend, int divisor) {
    if (dividend == Integer.MIN_VALUE && divisor == -1) return Integer.MAX_VALUE;
    int a = Math.abs(dividend), b = Math.abs(divisor);
    int result = 0;

    while (a - b >= 0) {
      int temp = b;
      int count = 1;
      while (a - (temp << 1) >= 0) {
        temp <<= 1;
        count <<= 1;
      }
      a -= temp;
      result += count;
    }

    return (dividend > 0 && divisor > 0) || (dividend < 0 && divisor < 0) ? result : -result;
  }
}
