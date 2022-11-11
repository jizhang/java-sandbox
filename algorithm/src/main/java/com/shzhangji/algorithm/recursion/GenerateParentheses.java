package com.shzhangji.algorithm.recursion;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/generate-parentheses/
public class GenerateParentheses {
  public static void main(String[] args) {
    var obj = new GenerateParentheses();
    System.out.println(obj.generateParenthesis(3)); // ["((()))","(()())","(())()","()(())","()()()"]
    System.out.println(obj.generateParenthesis(1)); // ["()"]
  }

  int n;
  List<String> result;

  public List<String> generateParenthesis(int n) {
    this.n = n;
    result = new ArrayList<>();
    recur("", 0, 0);
    return result;
  }

  void recur(String s, int left, int right) {
    if (s.length() == n * 2) {
      result.add(s);
      return;
    }

    if (left < n) recur(s + '(', left + 1, right);
    if (right < left) recur(s + ')', left, right + 1);
  }
}
