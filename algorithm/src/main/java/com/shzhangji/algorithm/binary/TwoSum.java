package com.shzhangji.algorithm.binary;

// https://leetcode.com/problems/sum-of-two-integers/
public class TwoSum {
  public static void main(String[] args) {
    var obj = new TwoSum();
    System.out.println(obj.getSum(1, 2)); // 3
    System.out.println(obj.getSum(2, 3)); // 5
  }

  public int getSum(int a, int b) {
    while (b != 0) {
      int carry = (a & b) << 1;
      a ^= b;
      b = carry;
    }
    return a;
  }
}
