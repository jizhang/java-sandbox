package com.shzhangji.algorithm.dp;

// https://leetcode.com/problems/climbing-stairs/
public class ClimbingStairs {
  public static void main(String[] args) {
    var obj = new ClimbingStairs();
    System.out.println(obj.climbStairs(2)); // 2
    System.out.println(obj.climbStairs(3)); // 3
    System.out.println(obj.climbStairs(4)); // 5
    System.out.println(obj.climbStairs(45)); // 1836311903
  }

  public int climbStairs(int n) {
    if (n == 1) return 1;
    var states = new int[n + 1];
    states[1] = 1;
    states[2] = 2;
    for (int i = 3; i <= n; ++i) {
      states[i] = states[i - 1] + states[i - 2];
    }
    return states[n];
  }
}
