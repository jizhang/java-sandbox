package com.shzhangji.algorithm.stack;

import java.util.ArrayDeque;
import java.util.stream.Collectors;

// https://leetcode.com/problems/basic-calculator-ii/
public class BasicCalculatorII {
  public static void main(String[] args) {
    var obj = new BasicCalculatorII();
    System.out.println(obj.calculate("3+2*2")); // 7
    System.out.println(obj.calculate(" 3/2 ")); // 1
    System.out.println(obj.calculate("3+5 / 2")); // 5

    System.out.println(obj.calculate("3*2+2")); // 8
  }

  public int calculate(String s) {
    int num = 0;
    char op = '+';
    var stack = new ArrayDeque<Integer>();
    for (int i = 0; i < s.length(); ++i) {
      var c = s.charAt(i);
      if (c >= '0' && c <= '9') {
        num = num * 10 + (c - '0');
      }
      if (c == '+' || c == '-' || c == '*' || c == '/' || i == s.length() - 1) {
        if (op == '+') stack.push(num);
        else if (op == '-') stack.push(-num);
        else if (op == '*') stack.push(stack.pop() * num);
        else if (op == '/') stack.push(stack.pop() / num);
        op = c;
        num = 0;
      }
    }
    return stack.stream().reduce(0, Integer::sum);
  }
}
