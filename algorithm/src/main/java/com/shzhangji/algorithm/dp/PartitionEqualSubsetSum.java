package com.shzhangji.algorithm.dp;

// https://leetcode.com/problems/partition-equal-subset-sum/
// https://www.educative.io/courses/grokking-dynamic-programming-patterns-for-coding-interviews/3jEPRo5PDvx
public class PartitionEqualSubsetSum {
  public static void main(String[] args) {
    var obj = new PartitionEqualSubsetSum();
    System.out.println(obj.canPartition(new int[] { 1, 5, 11, 5 })); // true
    System.out.println(obj.canPartition(new int[] { 1, 2, 3, 5 })); // false
  }

  public boolean canPartition(int[] nums) {
    int sum = 0;
    for (int num : nums) {
      sum += num;
    }
    if (sum % 2 != 0) {
      return false;
    }

    var states = new boolean[nums.length][sum / 2 + 1];
    for (int i = 0; i < nums.length; ++i) states[i][0] = true;

    for (int j = 1; j <= sum / 2; ++j) {
      if (j == nums[0]) {
        states[0][j] = true;
      }
    }

    for (int i = 1; i < nums.length; ++i) {
      for (int j = 1; j <= sum / 2; ++j) {
        if (j >= nums[i]) {
          states[i][j] = states[i - 1][j] || states[i - 1][j - nums[i]];
        } else {
          states[i][j] = states[i - 1][j];
        }
      }
    }

    return states[nums.length - 1][sum / 2];
  }
}
