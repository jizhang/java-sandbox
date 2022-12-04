package com.shzhangji.algorithm.array;

import java.util.Arrays;

// https://leetcode.com/problems/minimum-average-difference/
public class MinimumAverageDifference {
  public static void main(String[] args) {
    var obj = new MinimumAverageDifference();
    System.out.println(obj.minimumAverageDifference(new int[] {2,5,3,9,5,3})); // 3
    System.out.println(obj.minimumAverageDifference(new int[] {0})); // 0
  }

  public int minimumAverageDifference(int[] nums) {
    long sum = Arrays.stream(nums).asLongStream().sum();
    long leftSum = 0;
    long min = Integer.MAX_VALUE;
    int minIndex = -1;
    for (int i = 0; i < nums.length; ++i) {
      leftSum += nums[i];
      long leftAverage = leftSum / (i + 1);
      long rightAverage = i == nums.length - 1 ? 0 : (sum - leftSum) / (nums.length - i - 1);
      long diff = Math.abs(leftAverage - rightAverage);
      if (diff < min) {
        min = diff;
        minIndex = i;
      }
    }
    return minIndex;
  }
}
