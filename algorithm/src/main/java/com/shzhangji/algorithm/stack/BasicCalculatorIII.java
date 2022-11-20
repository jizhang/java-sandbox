package com.shzhangji.algorithm.stack;

public class BasicCalculatorIII {
  public static void main(String[] args) {
    var obj = new BasicCalculatorIII();
    System.out.println(obj.calculate("1 + 1")); // 2
    System.out.println(obj.calculate(" 6-4 / 2 ")); // 4
    System.out.println(obj.calculate("2*(5+5*2)/3+(6/2+8)")); // 21
    System.out.println(obj.calculate("(2+6* 3+5- (3*14/7+2)*5)+3")); // -12
  }

  int i;

  public int calculate(String s) {
    i = 0;
    return recur(s);
  }

  // https://leetcode.com/problems/basic-calculator-ii/discuss/686493/Optimal-Generic-Solution-for-Basic-Calculator-I-II-III-Time-and-Space-O(N)
  int recur(String s) {
    int result = 0, left = 0, right = 0;
    char op = '+';
    while (i < s.length()) {
      char c = s.charAt(i++);
      if (Character.isDigit(c)) {
        right = right * 10 + (c - '0');
      } else if (c == '(') {
        right = recur(s);
      } else if (c == ')') {
        break;
      } else if (c != ' ') {
        left = cal(left, right, op);
        if (c == '+' || c == '-') {
          result += left;
          left = 0;
        }
        right = 0;
        op = c;
      }
    }
    return result + cal(left, right, op);
  }

  int cal(int left, int right, char op) {
    if (op == '+') return left + right;
    if (op == '-') return left - right;
    if (op == '*') return left * right;
    return left / right;
  }
}
