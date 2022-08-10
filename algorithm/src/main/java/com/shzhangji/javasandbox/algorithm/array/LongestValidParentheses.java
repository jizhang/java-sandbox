package com.shzhangji.javasandbox.algorithm.array;

import java.util.ArrayDeque;

// https://leetcode.com/problems/longest-valid-parentheses/
public class LongestValidParentheses {
  public static void main(String[] args) {
    var obj = new LongestValidParentheses();
    System.out.println(obj.longestValidParentheses("(()")); // 2
    System.out.println(obj.longestValidParentheses(")()())")); // 4
    System.out.println(obj.longestValidParentheses("(())")); // 4
    System.out.println(obj.longestValidParentheses("())()")); // 2

    System.out.println(obj.longestValidParentheses("")); // 0
    System.out.println(obj.longestValidParentheses("()(()")); // 2
    System.out.println(obj.longestValidParentheses("()(())")); // 6
    System.out.println(obj.longestValidParentheses("(()())")); // 6

    System.out.println(obj.longestValidParentheses("()(()(()")); // 2
  }

  public int longestValidParentheses(String s) {
    var stack = new ArrayDeque<Integer>();
    stack.push(-1);

    int longest = 0;
    for (int i = 0; i < s.length(); ++i) {
      if (s.charAt(i) == '(') {
        stack.push(i);
      } else { // )
        stack.pop();
        if (stack.isEmpty()) {
          stack.push(i);
        } else {
          longest = Math.max(longest, i - stack.peek());
        }
      }
    }

    return longest;
  }
}
