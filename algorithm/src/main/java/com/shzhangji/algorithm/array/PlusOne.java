package com.shzhangji.algorithm.array;

import java.util.Arrays;

// https://leetcode.com/problems/plus-one/
public class PlusOne {
  public static void main(String[] args) {
    var obj = new PlusOne();
    System.out.println(Arrays.toString(obj.plusOne(new int[] { 1, 2, 3 }))); // [1, 2, 4]
    System.out.println(Arrays.toString(obj.plusOne(new int[] { 4, 3, 2, 1 }))); // [4, 3, 2, 2]
    System.out.println(Arrays.toString(obj.plusOne(new int[] { 9 }))); // [1, 0]

    System.out.println(Arrays.toString(obj.plusOne(new int[] { 1, 9 }))); // [2, 0]
    System.out.println(Arrays.toString(obj.plusOne(new int[] { 9, 9 }))); // [1, 0, 0]
  }

  public int[] plusOne(int[] digits) {
    return simplified(digits);
  }

  int[] firstTry(int[] digits) {
    digits[digits.length - 1] += 1;
    boolean overflow = false;
    for (int i = digits.length - 1; i >= 0; --i) {
      if (digits[i] == 10) {
        digits[i] = 0;
        if (i > 0) {
          digits[i - 1] += 1;
        } else {
          overflow = true;
        }
      } else {
        break;
      }
    }

    if (overflow) {
      var result = new int[digits.length + 1];
      result[0] = 1;
      System.arraycopy(digits, 0, result, 1, digits.length);
      return result;
    }

    return digits;
  }

  int[] simplified(int[] digits) {
    for (int i = digits.length - 1; i >= 0; --i) {
      if (digits[i] == 9) {
        digits[i] = 0;
      } else {
        digits[i] += 1;
        return digits;
      }
    }

    var result = new int[digits.length + 1];
    result[0] = 1;
    return result;
  }
}
