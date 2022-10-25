package com.shzhangji.algorithm.slidingwindow;

// https://leetcode.com/problems/max-consecutive-ones-iii/
public class MaxConsecutiveOnesIII {
  public static void main(String[] args) {
    var obj = new MaxConsecutiveOnesIII();
    System.out.println(obj.longestOnes(new int[] { 1,1,1,0,0,0,1,1,1,1,0 }, 2)); // 6
    System.out.println(obj.longestOnes(new int[] { 0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1 }, 3)); // 10
  }

  public int longestOnes(int[] nums, int k) {
    int i = 0, max = 0;
    for (int j = 0; j < nums.length; ++j) {
      if (nums[j] == 1) {
        max = Math.max(max, j - i + 1);
      } else if (k > 0) {
        max = Math.max(max, j - i + 1);
        --k;
      } else { // nums[j] == 0 && k == 0
        while (i <= j) {
          if (nums[i] == 0) { // Give a zero to current j.
            ++i;
            break;
          }
          ++i; // If nothing to give, i becomes j + 1.
        }
      }
    }
    return max;
  }
}
