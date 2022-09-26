package com.shzhangji.algorithm.dp;

// https://leetcode.com/problems/longest-palindromic-subsequence/
public class LongestPalindromicSubsequence {
  public static void main(String[] args) {
    var obj = new LongestPalindromicSubsequence();
    System.out.println(obj.longestPalindromeSubseq("bbbab")); // 4
    System.out.println(obj.longestPalindromeSubseq("cbbd")); // 2
  }

  public int longestPalindromeSubseq(String s) {
    return topDown(s);
  }

  int topDown(String s) {
    var memo = new Integer[s.length()][s.length()];
    return topDown(s, 0, s.length() - 1, memo);
  }

  int topDown(String s, int i, int j, Integer[][] memo) {
    if (i == j) return 1;
    if (i > j) return 0;
    if (memo[i][j] != null) return memo[i][j];

    if (s.charAt(i) == s.charAt(j)) {
      memo[i][j] = topDown(s, i + 1, j - 1, memo) + 2;
    } else {
      memo[i][j] = Math.max(topDown(s, i + 1, j, memo), topDown(s, i, j - 1, memo));
    }

    return memo[i][j];
  }

  int bottomUp(String s) {
    var dp = new int[s.length()][s.length()];

    for (int i = s.length() - 1; i >= 0; --i) { // Reversed loop
      dp[i][i] = 1;
      for (int j = i + 1; j < s.length(); ++j) {
        if (s.charAt(i) == s.charAt(j)) {
          dp[i][j] = dp[i + 1][j - 1] + 2;
        } else {
          dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
        }
      }
    }

    return dp[0][s.length() - 1];
  }
}
