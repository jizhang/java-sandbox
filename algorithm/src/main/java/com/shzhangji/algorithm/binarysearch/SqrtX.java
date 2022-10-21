package com.shzhangji.algorithm.binarysearch;

public class SqrtX {
  public static void main(String[] args) {
    var obj = new SqrtX();
    System.out.println(obj.mySqrt(1)); // 1
    System.out.println(obj.mySqrt(4)); // 2
    System.out.println(obj.mySqrt(6)); // 2
    System.out.println(obj.mySqrt(8)); // 2
    System.out.println(obj.mySqrt(36)); // 6
  }

  // https://leetcode.com/problems/sqrtx/
  public int mySqrt(int x) {
    int low = 1;
    int high = x;

    while (low <= high) {
      int mid = low + (high - low) / 2;
      if (mid == x / mid) return mid;
      if (mid < x / mid) low = mid + 1;
      else high = mid - 1;
    }

    return low - 1;
  }
}
