package com.shzhangji.algorithm.slidingwindow;

// https://leetcode.com/problems/max-consecutive-ones/
public class MaxConsecutiveOnes {
  public static void main(String[] args) {
    var obj = new MaxConsecutiveOnes();
    System.out.println(obj.findMaxConsecutiveOnes(new int[] { 1, 1, 0, 1, 1, 1 })); // 3
    System.out.println(obj.findMaxConsecutiveOnes(new int[] { 1, 0, 1, 1, 0, 1 })); // 2
  }

  public int findMaxConsecutiveOnes(int[] nums) {
    int i = 0, max = 0;
    for (int j = 0; j < nums.length; ++j) {
      if (nums[j] == 1) {
        max = Math.max(max, j - i + 1);
      } else {
        i = j + 1;
      }
    }
    return max;
  }
}
