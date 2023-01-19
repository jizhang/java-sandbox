package com.shzhangji.algorithm.dp;

// https://leetcode.com/problems/jump-game/
public class JumpGame {
  public static void main(String[] args) {
    var obj = new JumpGame();
    System.out.println(obj.canJump(new int[] {2, 3, 1, 1, 4})); // true
    System.out.println(obj.canJump(new int[] {3, 2, 1, 0, 4})); // false
  }

  public boolean canJump(int[] nums) {
    return bottomUp(nums);
  }

  boolean bottomUp(int[] nums) {
    var dp = new boolean[nums.length];
    dp[0] = true;

    for (int i = 1; i < nums.length; ++i) {
      for (int j = 0; j < i; ++j) {
        if (nums[j] >= i - j && dp[j]) {
          dp[i] = true;
          break;
        }
      }
    }

    return dp[nums.length - 1];
  }
}
