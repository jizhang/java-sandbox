package com.shzhangji.algorithm.dp;

// https://leetcode.com/problems/longest-palindromic-substring/
// https://www.geeksforgeeks.org/longest-palindrome-substring-set-1/
public class LongestPalindromicSubstring {
  public static void main(String[] args) {
    var obj = new LongestPalindromicSubstring();
    System.out.println(obj.longestPalindrome("babad")); // bab
    System.out.println(obj.longestPalindrome("cbbd")); // bb
    System.out.println(obj.longestPalindrome("aacabdkacaa")); // aca
    System.out.println(obj.longestPalindrome("aaaaa")); // aaaaa
  }

  public String longestPalindrome(String s) {
    return topDown(s);
  }

  String topDown(String s) {
    var memo = new Boolean[s.length()][s.length()];
    int start = 0, maxLen = 0;
    for (int i = 0; i < s.length(); ++i) {
      for (int j = i; j < s.length(); ++j) {
        if (isPalindrome(s, i, j, memo) && j - i + 1 > maxLen) {
          start = i;
          maxLen = j - i + 1;
        }
      }
    }
    return s.substring(start, start + maxLen);
  }

  boolean isPalindrome(String s, int i, int j, Boolean[][] memo) {
    if (i == j) return true;
    if (memo[i][j] != null) return memo[i][j];
    boolean charEquals = s.charAt(i) == s.charAt(j);
    if (j - i == 1) {
      memo[i][j] = charEquals;
    } else {
      memo[i][j] = charEquals && isPalindrome(s, i + 1, j - 1, memo);
    }
    return memo[i][j];
  }

  String bottomUp(String s) {
    var dp = new boolean[s.length()][s.length()];
    int start = 0, maxLen = 1;

    // len == 1
    for (int i = 0; i < s.length(); ++i) {
      dp[i][i] = true;
    }

    // len == 2
    for (int i = 0; i < s.length() - 1; ++i) {
      if (s.charAt(i) == s.charAt(i + 1)) {
        dp[i][i + 1] = true;
        start = i;
        maxLen = 2;
      }
    }

    for (int len = 3; len <= s.length(); ++len) {
      for (int i = 0; i <= s.length() - len; ++i) {
        int j = i + len - 1;
        if (s.charAt(i) == s.charAt(j)) {
          dp[i][j] = dp[i + 1][j - 1];
          if (dp[i][j] && len > maxLen) {
            start = i;
            maxLen = len;
          }
        }
      }
    }

    return s.substring(start, start + maxLen);
  }
}
