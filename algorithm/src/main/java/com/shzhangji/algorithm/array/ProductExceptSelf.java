package com.shzhangji.algorithm.array;

import java.util.Arrays;

// https://leetcode.com/problems/product-of-array-except-self/
// https://leetcode.com/problems/product-of-array-except-self/discuss/1342916/3-Minute-Read-Mimicking-an-Interview
public class ProductExceptSelf {
  public static void main(String[] args) {
    var obj = new ProductExceptSelf();
    System.out.println(Arrays.toString(obj.productExceptSelf(new int[] { 1, 2, 3, 4 }))); // [24, 12, 8, 6]
    System.out.println(Arrays.toString(obj.productExceptSelf(new int[] { -1, 1, 0, -3, 3 }))); // [0, 0, 9, 0, 0]
  }

  public int[] productExceptSelf(int[] nums) {
    var result = new int[nums.length]; // result[i] = prefixProd[i - 1] * suffixProd[i + 1]
    int prod = 1;
    for (int i = 0; i < nums.length; ++i) {
      result[i] = prod; // result[i] = nums[0] * nums[1] * ... nums[i - 1]
      prod *= nums[i];
    }

    prod = 1;
    for (int i = nums.length - 1; i >= 0; --i) {
      result[i] *= prod; // result[i] *= nums[n - 1] * nums[n - 2] * ... * nums[i + 1]
      prod *= nums[i];
    }

    return result;
  }
}
