package com.shzhangji.algorithm.number;

import java.util.HashSet;

// https://leetcode.com/problems/happy-number/
public class HappyNumber {
  public static void main(String[] args) {
    var obj = new HappyNumber();
    System.out.println(obj.isHappy(19)); // true
    System.out.println(obj.isHappy(2)); // false
  }

  public boolean isHappy(int n) {
    var set = new HashSet<Integer>();
    while (set.add(n)) {
      int x = n;
      n = 0;
      while (x > 0) {
        n += Math.pow(x % 10, 2);
        x /= 10;
      }
      if (n == 1) return true;
    }
    return false;
  }
}
