package com.shzhangji.algorithm.recursion;

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
    assert n > 0;
    return climbStairs(n, new int[n + 1]);
  }

  int climbStairs(int n, int[] cache) {
    if (n == 1) return 1;
    if (n == 2) return 2;
    if (cache[n] > 0) return cache[n];
    int result = climbStairs(n - 1, cache) + climbStairs(n - 2, cache);
    cache[n] = result;
    return result;
  }
}
