package com.shzhangji.algorithm.binary;

import java.util.Arrays;

// https://leetcode.com/problems/counting-bits/
public class CountingBits {
  public static void main(String[] args) {
    var obj = new CountingBits();
    System.out.println(Arrays.toString(obj.countBits(2))); // [0, 1, 1]
    System.out.println(Arrays.toString(obj.countBits(5))); // [0, 1, 1, 2, 1, 2]
  }

  public int[] countBits(int n) {
    var dp = new int[n + 1];
    dp[0] = 0;
    for (int i = 1; i <= n; ++i) {
      dp[i] += dp[i >> 1] + (i & 1);
    }
    return dp;
  }
}
