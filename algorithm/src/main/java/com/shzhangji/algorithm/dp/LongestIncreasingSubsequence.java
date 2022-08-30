package com.shzhangji.algorithm.dp;

import java.util.Arrays;

// https://leetcode.com/problems/longest-increasing-subsequence/
public class LongestIncreasingSubsequence {
  public static void main(String[] args) {
    var obj = new LongestIncreasingSubsequence();
    System.out.println(obj.lengthOfLIS(new int[] { 10, 9, 2, 5, 3, 7, 101, 18 })); // 4
    System.out.println(obj.lengthOfLIS(new int[] { 0, 1, 0, 3, 2, 3 })); // 4
    System.out.println(obj.lengthOfLIS(new int[] { 7, 7, 7, 7, 7, 7, 7 })); // 1
  }

  public int lengthOfLIS(int[] nums) {
    var states = new int[nums.length];
    Arrays.fill(states, 1);

    for (int i = 1; i < nums.length; ++i) {
      for (int j = 0; j < i; ++j) {
        if (nums[i] > nums[j] && states[i] < states[j] + 1) {
          states[i] = states[j] + 1;
        }
      }
    }

    int max = 1;
    for (int state : states) {
      if (state > max) {
        max = state;
      }
    }
    return max;
  }
}
