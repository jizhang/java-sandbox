package com.shzhangji.algorithm.dp;

// https://leetcode.com/problems/maximum-subarray/
// https://leetcode.com/problems/maximum-subarray/discuss/503255/DP-Thought-Process-(From-Top-down-to-Optimized-Bottom-up)
public class MaximumSubarray {
  public static void main(String[] args) {
    var obj = new MaximumSubarray();
    System.out.println(obj.maxSubArray(new int[] { -2, 1, -3, 4, -1, 2, 1, -5, 4 })); // 6
    System.out.println(obj.maxSubArray(new int[] { 1 })); // 1
    System.out.println(obj.maxSubArray(new int[] { 5, 4, -1, 7, 8 })); // 23
  }

  public int maxSubArray(int[] nums) {
    return topDown(nums);
  }

  int bruteForce(int[] nums) {
    int max = nums[0];
    for (int i = 0; i < nums.length; ++i) {
      int sum = 0;
      for (int j = i; j < nums.length; ++j) {
        sum += nums[j];
        if (sum > max) max = sum;
      }
    }
    return max;
  }

  int kadane(int[] nums) {
    int result = nums[0], max = nums[0];
    for (int i = 1; i < nums.length; ++i) {
      max = Math.max(max + nums[i], nums[i]);
      if (max > result) result = max;
    }
    return result;
  }

  int bottomUp(int[] nums) {
    var dp = new int[nums.length];
    dp[0] = nums[0];
    for (int i = 1; i < nums.length; ++i) {
      dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
    }

    int max = Integer.MIN_VALUE;
    for (int sum : dp) {
      if (sum > max) max = sum;
    }
    return max;
  }

  int max;

  int topDown(int[] nums) {
    max = nums[0];
    topDown(nums, nums.length - 1);
    return max;
  }

  int topDown(int[] nums, int i) {
    if (i == 0) return nums[0];
    int sum = Math.max(topDown(nums, i - 1) + nums[i], nums[i]);
    if (sum > max) max = sum;
    return sum;
  }
}
