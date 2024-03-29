package com.shzhangji.algorithm.dp;

import java.util.Arrays;

// https://leetcode.com/problems/longest-increasing-subsequence/
public class LongestIncreasingSubsequence {
  public static void main(String[] args) {
    var obj = new LongestIncreasingSubsequence();
    System.out.println(obj.lengthOfLIS(new int[] { 10, 9, 2, 5, 3, 7, 101, 18 })); // 4
    System.out.println(obj.lengthOfLIS(new int[] { 0, 1, 0, 3, 2, 3 })); // 4
    System.out.println(obj.lengthOfLIS(new int[] { 7, 7, 7, 7, 7, 7, 7 })); // 1
    System.out.println(obj.lengthOfLIS(new int[] { 4, 10, 4, 3, 8, 9 })); // 3
    System.out.println(obj.lengthOfLIS(new int[] { 1, 3, 6, 7, 9, 4, 10, 5, 6 })); // 6
  }

  public int lengthOfLIS(int[] nums) {
    return bottomUpBinarySearch(nums);
  }

  int topDown(int[] nums) {
    var memo = new int[nums.length];
    int result = 1;
    for (int i = 0; i < nums.length; ++i) {
      result = Math.max(result, topDown(nums, i, memo));
    }
    return result;
  }

  int topDown(int[] nums, int i, int[] memo) {
    if (i == 0) return 1;
    if (memo[i] > 0) return memo[i];
    int result = 1;
    for (int j = 0; j < i; ++j) {
      if (nums[i] > nums[j]) {
        result = Math.max(result, topDown(nums, j, memo) + 1);
      }
    }
    memo[i] = result;
    return result;
  }

  int bottomUp(int[] nums) {
    // states[i] means the longest increasing subsequence that ends with nums[i]
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

  // https://leetcode.com/problems/longest-increasing-subsequence/discuss/74824/JavaPython-Binary-search-O(nlogn)-time-with-explanation
  int bottomUpBinarySearch(int[] nums) {
    // dp[i] holds the final element of an increasing subsequence that has the length of i + 1.
    // If there are multiple such subsequences, store the smallest element.
    var dp = new int[nums.length];
    int len = 0;
    for (int num : nums) {
      int pos = Arrays.binarySearch(dp, 0, len, num);
      if (pos < 0) pos = -(pos + 1);
      dp[pos] = num;
      if (pos == len) ++len;
    }
    return len;
  }
}
