package com.shzhangji.algorithm.dp;

// https://leetcode.com/problems/burst-balloons/
public class BurstBalloons {
  public static void main(String[] args) {
    var obj = new BurstBalloons();
    System.out.println(obj.maxCoins(new int[] { 3, 1, 5, 8})); // 167
    System.out.println(obj.maxCoins(new int[] { 1, 5 })); // 10
  }

  public int maxCoins(int[] nums) {
    return topDown(nums);
  }

  int topDown(int[] nums) {
    return topDown(nums, 0, nums.length - 1);
  }

  // TODO Not finished.
  int topDown(int[] nums, int start, int end) {
    if (start == end) return nums[start];

    int coins = (start > 0 ? nums[start - 1] : 1) * nums[start] * (start < nums.length - 1 ? nums[start + 1] : 1);
    int result = 0;
    for (int i = start + 1; i <= end; ++i) {
      result = Math.max(result, coins + topDown(nums, i, end));
    }

    return result;
  }
}
