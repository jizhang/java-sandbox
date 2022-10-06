package com.shzhangji.algorithm.stack;

import java.util.ArrayDeque;
import java.util.Set;

// https://leetcode.com/problems/evaluate-reverse-polish-notation/
public class ReversePolishNotation {
  public static void main(String[] args) {
    var obj = new ReversePolishNotation();
    System.out.println(obj.evalRPN(new String[] {"2","1","+","3","*"})); // 9
    System.out.println(obj.evalRPN(new String[] {"4","13","5","/","+"})); // 6
    System.out.println(obj.evalRPN(new String[] {"10","6","9","3","+","-11","*","/","*","17","+","5","+"})); // 22
  }

  public int evalRPN(String[] tokens) {
    var operands = new ArrayDeque<Integer>();

    var opSet = Set.of("+", "-", "*", "/");
    for (var token: tokens) {
      if (opSet.contains(token)) {
        int b = operands.pop(); // b first
        int a = operands.pop();
        switch (token) {
          case "+":
            operands.push(a + b);
            break;
          case "-":
            operands.push(a - b);
            break;
          case "*":
            operands.push(a * b);
            break;
          case "/":
            operands.push(a / b);
            break;
        }
      } else {
        operands.push(Integer.valueOf(token));
      }
    }

    return operands.pop();
  }
}
