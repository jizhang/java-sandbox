package com.shzhangji.algorithm.recursion;

public class Factorial {
  public static void main(String[] args) {
    var obj = new Factorial();
    System.out.println(obj.factorial(1));
    System.out.println(obj.factorial(3));
  }

  int factorial(int n) {
    if (n == 0 || n == 1) return 1;
    return n * factorial(n - 1);
  }
}
