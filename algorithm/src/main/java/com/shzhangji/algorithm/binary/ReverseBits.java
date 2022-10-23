package com.shzhangji.algorithm.binary;

// https://leetcode.com/problems/reverse-bits/
public class ReverseBits {
  public static void main(String[] args) {
    var obj = new ReverseBits();
    System.out.println(obj.reverseBits(0b00000010100101000001111010011100)); // 964176192
    System.out.println(obj.reverseBits(0b11111111111111111111111111111101)); // -1073741825
  }

  // you need treat n as an unsigned value
  public int reverseBits(int n) {
    int res = 0;
    for (int i = 0; i < 32; ++i) {
      res = (res << 1) | (n & 1);
      n >>= 1;
    }
    return res;
  }
}
