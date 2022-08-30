package com.shzhangji.algorithm.dp;

// https://leetcode.com/problems/longest-common-subsequence/
public class LongestCommonSubsequence {
  public static void main(String[] args) {
    var obj = new LongestCommonSubsequence();
    System.out.println(obj.longestCommonSubsequence("abcde", "ace")); // 3
    System.out.println(obj.longestCommonSubsequence("abc", "abc")); // 3
    System.out.println(obj.longestCommonSubsequence("abc", "def")); // 0
  }

  public int longestCommonSubsequence(String text1, String text2) {
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
