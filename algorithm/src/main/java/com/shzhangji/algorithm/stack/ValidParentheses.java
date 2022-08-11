package com.shzhangji.algorithm.stack;

import java.util.ArrayDeque;
import java.util.Deque;

// https://leetcode.com/problems/valid-parentheses/
public class ValidParentheses {
  public static void main(String[] args) {
    var obj = new ValidParentheses();
    System.out.println(obj.isValid("()"));
    System.out.println(obj.isValid("()[]{}"));
    System.out.println(obj.isValid("(]"));
    System.out.println(obj.isValid("([]{})"));
  }

  public boolean isValid(String s) {
    Deque<Character> stack = new ArrayDeque<>(s.length());
    for (int i = 0; i < s.length(); ++i) {
      char c = s.charAt(i);
      if (c == '(' || c == '[' || c == '{') {
        stack.push(c);
      } else {
        if (stack.isEmpty()) return false;
        char p = stack.pop();
        if (c == ')' && p != '(') return false;
        if (c == ']' && p != '[') return false;
        if (c == '}' && p != '{') return false;
      }
    }

    return stack.isEmpty();
  }
}
