package com.shzhangji.algorithm.dp;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

// https://leetcode.com/problems/word-break/
public class WordBreak {
  public static void main(String[] args) {
    var obj = new WordBreak();
    System.out.println(obj.wordBreak("leetcode", List.of("leet", "code"))); // true
    System.out.println(obj.wordBreak("applepenapple", List.of("apple", "pen"))); // true
    System.out.println(obj.wordBreak("catsandog", List.of("cats", "dog", "sand", "and", "cat"))); // false
  }

  public boolean wordBreak(String s, List<String> wordDict) {
    return bottomUpSentinel(s, wordDict);
  }

  boolean topDown(String s, List<String> wordDict) {
    var wordSet = new HashSet<>(wordDict);
    return topDown(s, wordSet, new HashMap<>());
  }

  boolean topDown(String s, Set<String> wordSet, Map<String, Boolean> memo) {
    if (wordSet.contains(s)) return true;
    if (memo.containsKey(s)) return memo.get(s);

    for (int i = 0; i < s.length(); ++i) {
      if (wordSet.contains(s.substring(0, i + 1))) {
        var s1 = s.substring(i + 1);
        var r1 = topDown(s1, wordSet, memo);
        memo.put(s1, r1);
        if (r1) {
          return true;
        }
      }
    }

    return false;
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
    dp[0] = true; // Empty string can always be segmented.

    for (int i = 1; i <= s.length(); ++i) {
      for (int j = 1; j <= i; ++j) {
        if (dp[j - 1] && wordSet.contains(s.substring(j - 1, i))) {
          dp[i] = true;
          break;
        }
      }
    }

    return dp[s.length()];
  }
}
