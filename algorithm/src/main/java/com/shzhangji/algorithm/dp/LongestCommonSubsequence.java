package com.shzhangji.algorithm.dp;

import java.util.Arrays;

// https://leetcode.com/problems/longest-common-subsequence/
public class LongestCommonSubsequence {
  public static void main(String[] args) {
    var obj = new LongestCommonSubsequence();
    System.out.println(obj.longestCommonSubsequence("abcde", "ace")); // 3
    System.out.println(obj.longestCommonSubsequence("abc", "abc")); // 3
    System.out.println(obj.longestCommonSubsequence("abc", "def")); // 0
  }

  public int longestCommonSubsequence(String text1, String text2) {
    return topDown(text1, text2);
  }

  int topDown(String text1, String text2) {
    var memo = new int[text1.length()][text2.length()];
    Arrays.stream(memo).forEach(arr -> Arrays.fill(arr, -1));
    return topDown(text1, text2, 0, 0, memo);
  }

  int topDown(String text1, String text2, int i, int j, int[][] memo) {
    if (i == text1.length() || j == text2.length()) return 0;
    if (memo[i][j] != -1) return memo[i][j];
    int result;
    if (text1.charAt(i) == text2.charAt(j)) {
      result = topDown(text1, text2, i + 1, j + 1, memo) + 1;
    } else {
      result = Math.max(topDown(text1, text2, i + 1, j, memo), topDown(text1, text2, i, j + 1, memo));
    }
    memo[i][j] = result;
    return result;
  }

  int bottomUp(String text1, String text2) {
    var states = new int[text1.length() + 1][text2.length() + 1];
    for (int i = 1; i <= text1.length(); ++i) {
      for (int j = 1; j <= text2.length(); ++j) {
        if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
          states[i][j] = states[i - 1][j - 1] + 1;
        } else {
          states[i][j] = Math.max(Math.max(states[i - 1][j], states[i][j - 1]), states[i - 1][j - 1]);
        }
      }
    }

    return states[text1.length()][text2.length()];
  }
}
