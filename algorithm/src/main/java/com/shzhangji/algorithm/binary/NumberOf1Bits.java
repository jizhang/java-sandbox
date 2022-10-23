package com.shzhangji.algorithm.binary;

// https://leetcode.com/problems/number-of-1-bits/
public class NumberOf1Bits {
  public static void main(String[] args) {
    var obj = new NumberOf1Bits();
    System.out.println(obj.hammingWeight(11)); // 3
    System.out.println(obj.hammingWeight(128)); // 1
    System.out.println(obj.hammingWeight(-3)); // 31
  }

  // you need to treat n as an unsigned value
  public int hammingWeight(int n) {
    int ones = 0;
    while (n != 0) {
      ones += (n & 1);
      n >>>= 1;
    }
    return ones;
  }
}
