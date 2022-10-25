package com.shzhangji.algorithm.slidingwindow;

// https://leetcode.com/problems/maximum-average-subarray-i/
public class MaximumAverageSubarrayI {
  public static void main(String[] args) {
    var obj = new MaximumAverageSubarrayI();
    System.out.println(obj.findMaxAverage(new int[] { 1, 12, -5, -6, 50, 3 }, 4)); // 12.75
    System.out.println(obj.findMaxAverage(new int[] { 5 }, 1)); // 5
    System.out.println(obj.findMaxAverage(new int[] { 0, 4, 0, 3, 2 }, 1)); // 4
  }

  public double findMaxAverage(int[] nums, int k) {
    int i = 0, sum = 0, maxSum = Integer.MIN_VALUE;
    for (int j = 0; j < nums.length; ++j) {
      if (j - i < k) {
        sum += nums[j];
        maxSum = sum;
      } else {
        sum = sum - nums[i] + nums[j];
        maxSum = Math.max(maxSum, sum);
        ++i;
      }
    }
    return (double) maxSum / k;
  }
}
