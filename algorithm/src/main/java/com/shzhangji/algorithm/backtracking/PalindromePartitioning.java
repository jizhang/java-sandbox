package com.shzhangji.algorithm.backtracking;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/palindrome-partitioning/
public class PalindromePartitioning {
  public static void main(String[] args) {
    var obj = new PalindromePartitioning();
    System.out.println(obj.partition("aab")); // [["a","a","b"],["aa","b"]]
    System.out.println(obj.partition("a")); // [["a"]]
  }

  public List<List<String>> partition(String s) {
    var result = new ArrayList<List<String>>();
    var tmp = new ArrayList<String>();
    recur(s, 0, tmp, result);
    return result;
  }

  void recur(String s, int start, List<String> tmp, List<List<String>> result) {
    if (start == s.length()) {
      result.add(List.copyOf(tmp));
      return;
    }

    for (int i = start; i < s.length(); ++i) {
      if (isPalindrome(s, start, i)) {
        tmp.add(s.substring(start, i + 1));
        recur(s, i + 1, tmp, result);
        tmp.remove(tmp.size() - 1);
      }
    }
  }

  boolean isPalindrome(String s, int i, int j) {
    while (i < j) {
      if (s.charAt(i++) != s.charAt(j--)) return false;
    }
    return true;
  }
}
