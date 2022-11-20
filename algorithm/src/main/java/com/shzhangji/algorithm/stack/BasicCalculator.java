package com.shzhangji.algorithm.stack;

import java.util.ArrayDeque;

// https://leetcode.com/problems/basic-calculator/
// https://leetcode.com/problems/basic-calculator/discuss/62361/Iterative-Java-solution-with-stack
public class BasicCalculator {
  public static void main(String[] args) {
    var obj = new BasicCalculator();
    System.out.println(obj.calculate("1 + 1")); // 2
    System.out.println(obj.calculate(" 2-1 + 2")); // 3
    System.out.println(obj.calculate("(1+(4+5+2)-3)+(6+8)")); // 23

    System.out.println(obj.calculate("-(2+3)")); // -5
    System.out.println(obj.calculate("(-10+1)")); // -9
  }

  public int calculate(String s) {
    int num = 0;
    int sign = 1;
    int result = 0;
    var stack = new ArrayDeque<Integer>();
    for (char c : s.toCharArray()) {
      if (c >= '0' && c <= '9') {
        num = num * 10 + (c - '0');
      } else if (c == '+' || c == '-') {
        result += num * sign;
        num = 0;
        sign = c == '+' ? 1 : -1;
      } else if (c == '(') {
        stack.push(result);
        stack.push(sign);
        result = 0;
        sign = 1;
      } else if (c == ')') {
        result += num * sign;
        result = result * stack.pop() + stack.pop();
        num = 0;
        sign = 1;
      }
    }
    return result + num * sign;
  }
}
