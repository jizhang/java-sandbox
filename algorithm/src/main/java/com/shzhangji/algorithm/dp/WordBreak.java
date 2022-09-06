package com.shzhangji.algorithm.dp;

import java.util.HashSet;
import java.util.List;

// https://leetcode.com/problems/word-break/
public class WordBreak {
  public static void main(String[] args) {
    var obj = new WordBreak();
    System.out.println(obj.wordBreak("leetcode", List.of("leet", "code"))); // true
    System.out.println(obj.wordBreak("applepenapple", List.of("apple", "pen"))); // true
    System.out.println(obj.wordBreak("catsandog", List.of("cats", "dog", "sand", "and", "cat"))); // false
  }

  public boolean wordBreak(String s, List<String> wordDict) {
    return bottomUp(s, wordDict);
  }

  boolean bottomUp(String s, List<String> wordDict) {
    var wordSet = new HashSet<>(wordDict);
    var dp = new boolean[s.length()];
    for (int i = 0; i < s.length(); ++i) {
      if (wordSet.contains(s.substring(0, i + 1))) {
        dp[i] = true;
      }
    }

    for (int i = 0; i < s.length(); ++i) {
      for (int j = 0; j < i; ++j) {
        if (dp[j] && wordSet.contains(s.substring(j + 1, i + 1))) {
          dp[i] = true;
          break;
        }
      }
    }

    return dp[s.length() - 1];
  }

  boolean bottomUpSentinel(String s, List<String> wordDict) {
    var wordSet = new HashSet<>(wordDict);
    var dp = new boolean[s.length() + 1];
    dp[0] = true;

    for (int i = 1; i <= s.length(); ++i) {
      for (int j = 0; j < i; ++j) {
        if (dp[j] && wordSet.contains(s.substring(j, i))) {
          dp[i] = true;
          break;
        }
      }
    }

    return dp[s.length()];
  }
}
