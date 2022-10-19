package com.shzhangji.algorithm.backtracking;

// https://leetcode.com/problems/regular-expression-matching
// https://leetcode.com/problems/regular-expression-matching/discuss/5847/Evolve-from-brute-force-to-dp
public class RegularExpression {
  public static void main(String[] args) {
    var obj = new RegularExpression();
    System.out.println(obj.isMatch("aa", "a")); // false
    System.out.println(obj.isMatch("aa", "a*")); // true
    System.out.println(obj.isMatch("ab", ".*")); // true
    System.out.println(obj.isMatch("aab", "c*a*b")); // true
    System.out.println(obj.isMatch("a", "ab*")); // true
  }

  boolean matched;

  public boolean isMatch(String s, String p) {
    matched = false;
    matchNext(s, p, 0, 0);
    return matched;
  }

  void matchNext(String s, String p, int i, int j) {
    if (matched) return;
    if (p.length() == j) {
      if (s.length() == i) matched = true;
      return;
    }

    var charMatches = i < s.length() && (p.charAt(j) == '.' || p.charAt(j) == s.charAt(i));
    if (j + 1 < p.length() && p.charAt(j + 1) == '*') {
      matchNext(s, p, i, j + 2);
      if (charMatches) {
          matchNext(s, p, i + 1, j);
      }
    } else if (charMatches) {
      matchNext(s, p, i + 1, j + 1);
    }
  }
}
