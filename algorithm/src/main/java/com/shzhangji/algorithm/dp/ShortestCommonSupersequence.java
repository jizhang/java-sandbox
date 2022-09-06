package com.shzhangji.algorithm.dp;

// https://leetcode.com/problems/shortest-common-supersequence/
public class ShortestCommonSupersequence {
  public static void main(String[] args) {
    var obj = new ShortestCommonSupersequence();
    System.out.println(obj.shortestCommonSupersequence("abac", "cab")); // cabac
    System.out.println(obj.shortestCommonSupersequence("aaaaaaaa", "aaaaaaaa")); // aaaaaaaa
  }

  public String shortestCommonSupersequence(String str1, String str2) {
    return bottomUp(str1, str2);
  }

  public String bottomUp(String str1, String str2) {
    var dp = new int[str1.length() + 1][str2.length() + 1];

    for (int i = 1; i <= str1.length(); ++i) {
      for (int j = 1; j <= str2.length(); ++j) {
        if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
          dp[i][j] = dp[i - 1][j - 1] + 1;
        } else {
          dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
        }
      }
    }

    int i = str1.length(), j = str2.length();
    var sb = new StringBuilder();
    while (i >= 1 && j >= 1) {
      if (dp[i][j] == dp[i - 1][j]) {
        sb.append(str1.charAt(i - 1));
        --i;
      } else if (dp[i][j] == dp[i][j - 1]) {
        sb.append(str2.charAt(j - 1));
        --j;
      } else {
        sb.append(str1.charAt(i - 1));
        --i;
        --j;
      }
    }

    while (i >= 1) {
      sb.append(str1.charAt(i - 1));
      --i;
    }
    while (j >= 1) {
      sb.append(str2.charAt(j - 1));
      --j;
    }
    return sb.reverse().toString();
  }
}
