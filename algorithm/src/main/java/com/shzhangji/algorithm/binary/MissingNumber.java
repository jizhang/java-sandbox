package com.shzhangji.algorithm.binary;

// https://leetcode.com/problems/missing-number/
public class MissingNumber {
  public static void main(String[] args) {
    var obj = new MissingNumber();
    System.out.println(obj.missingNumber(new int[] { 3, 0, 1 })); // 2
    System.out.println(obj.missingNumber(new int[] { 0, 1 })); // 2
    System.out.println(obj.missingNumber(new int[] { 9, 6, 4, 2, 3, 5, 7, 0, 1 })); // 8
  }

  // Follow up: Could you implement a solution using only O(1) extra space complexity and O(n) runtime complexity?
  public int missingNumber(int[] nums) {
    int res = 0;
    for (int i = 1; i <= nums.length; ++i) {
      res ^= i;
      res ^= nums[i - 1];
    }
    return res;
  }
}
