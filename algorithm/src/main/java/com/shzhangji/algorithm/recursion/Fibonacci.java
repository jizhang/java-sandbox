package com.shzhangji.algorithm.recursion;

public class Fibonacci {
  public static void main(String[] args) {
    for (int i = 0; i < 10; ++i) {
      System.out.print(fibonacci(i) + " ");
    }
  }

  // The Fibonacci series is a series of elements where,
  // the previous two elements are added to get the next element,
  // starting with 0 and 1.
  static int fibonacci(int n) {
    assert n >= 0;
    if (n == 0) return 0;
    if (n == 1) return 1;
    return fibonacci(n - 1) + fibonacci(n - 2);
  }
}
