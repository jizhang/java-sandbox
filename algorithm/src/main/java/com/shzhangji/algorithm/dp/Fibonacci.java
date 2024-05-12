package com.shzhangji.algorithm.dp;

// https://leetcode.com/problems/fibonacci-number/
// https://leetcode.com/problems/fibonacci-number/discuss/329680/Here-is-why-this-question-is-kinda-important-and-this-is-what-you-should-take-away
public class Fibonacci {
  public static void main(String[] args) {
    var obj = new Fibonacci();
    System.out.println(obj.bottomUp(2)); // 1
    System.out.println(obj.bottomUp(3)); // 2
    System.out.println(obj.bottomUp(4)); // 3

    System.out.println(obj.bottomUp(0)); // 0
  }

  public int fib(int n) {
    return bottomUp(n);
  }

  // The Fibonacci series is a series of elements where,
  // the previous two elements are added to get the next element,
  // starting with 0 and 1.
  int topDown(int n) {
    assert n >= 0;
    if (n == 0) return 0;
    if (n == 1) return 1;
    return topDown(n - 1) + topDown(n - 2);
  }

  int bottomUp(int n) {
    if (n == 0) return 0;
    var dp = new int[n + 1];
    dp[1] = 1;
    for (int i = 2; i <= n; ++i) {
      dp[i] = dp[i - 1] + dp[i - 2];
    }
    return dp[n];
  }
}
