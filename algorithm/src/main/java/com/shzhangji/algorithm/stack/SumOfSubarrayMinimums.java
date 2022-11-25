package com.shzhangji.algorithm.stack;

import java.util.ArrayDeque;

// https://leetcode.com/problems/sum-of-subarray-minimums/
public class SumOfSubarrayMinimums {
  public static void main(String[] args) {
    var obj = new SumOfSubarrayMinimums();
    System.out.println(obj.sumSubarrayMins(new int[] {3,1,2,4})); // 17
    System.out.println(obj.sumSubarrayMins(new int[] {11,81,94,43,3})); // 444
  }

  static final int MODULO = (int) 1e9 + 7;

  public int sumSubarrayMins(int[] arr) {
    return monotonicStack(arr);
  }

  int bruteForce(int[] arr) {
    int sum = 0;
    for (int i = 0; i < arr.length; ++i) {
      for (int j = i; j < arr.length; ++j) {
        int min = arr[i];
        for (int k = i + 1; k <= j; ++k) {
          min = Math.min(min, arr[k]);
        }
        sum = (sum + min) % MODULO;
      }
    }
    return sum;
  }

  // https://leetcode.com/problems/sum-of-subarray-minimums/discuss/2118729/Very-detailed-stack-explanation-O(n)-or-Images-and-comments
  int monotonicStack(int[] arr) {
    var stack = new ArrayDeque<Integer>();
    stack.push(-1);

    int result = 0;
    for (int i2 = 0; i2 <= arr.length; ++i2) {
      int num = i2 == arr.length ? 0 : arr[i2];

      while (!stack.isEmpty() && stack.peek() != -1 && num < arr[stack.peek()]) {
        int i = stack.pop();
        if (stack.isEmpty()) break;
        int i1 = stack.peek();
        long sum = (long) arr[i] * (i - i1) * (i2 - i);
        result = (int) ((result + sum) % MODULO);
      }

      stack.push(i2);
    }

    return result;
  }
}
