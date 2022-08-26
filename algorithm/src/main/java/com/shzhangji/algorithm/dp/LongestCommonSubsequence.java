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
    var states = new int[text1.length()][text2.length()];
    states[0][0] = text1.charAt(0) == text2.charAt(0) ? 1 : 0;
    for (int i = 1; i < text1.length(); ++i) {
      states[i][0] = text1.charAt(i) == text2.charAt(0) ? 1 : states[i - 1][0];
    }
    for (int j = 1; j < text2.length(); ++j) {
      states[0][j] = text1.charAt(0) == text2.charAt(j) ? 1 : states[0][j - 1];
    }

    for (int i = 1; i < text1.length(); ++i) {
      for (int j = 1; j < text2.length(); ++j) {
        int tmp = Math.max(states[i - 1][j], states[i][j - 1]);
        if (text1.charAt(i) == text2.charAt(j)) {
          states[i][j] = Math.max(tmp, states[i - 1][j - 1] + 1);
        } else {
          states[i][j] = Math.max(tmp, states[i - 1][j - 1]);
        }
      }
    }

    return states[text1.length() - 1][text2.length() - 1];
  }
}
