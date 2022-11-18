package com.shzhangji.algorithm.array;

import java.util.Arrays;

// https://leetcode.com/problems/sum-of-even-numbers-after-queries/
public class SumEvenAfterQueries {
  public static void main(String[] args) {
    var obj = new SumEvenAfterQueries();
    System.out.println(Arrays.toString(obj.sumEvenAfterQueries(new int[] { 1, 2, 3, 4 }, new int[][] {{1, 0}, {-3, 1}, {-4, 0}, {2, 3}})));
    // [8,6,2,4]

    System.out.println(Arrays.toString(obj.sumEvenAfterQueries(new int[] { 1 }, new int[][] {{4, 0}})));
    // [0]
  }

  public int[] sumEvenAfterQueries(int[] nums, int[][] queries) {
    var result = new int[queries.length];
    int sum = Arrays.stream(nums).filter(num -> num % 2 == 0).sum();
    for (int i = 0; i < queries.length; ++i) {
      int val = queries[i][0], idx = queries[i][1];
      if (nums[idx] % 2 == 0) sum -= nums[idx];
      nums[idx] += val;
      if (nums[idx] % 2 == 0) sum += nums[idx];
      result[i] = sum;
    }

    return result;
  }
}

