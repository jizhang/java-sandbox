package com.shzhangji.algorithm.array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

// https://leetcode.com/problems/4sum/
public class FourSum {
  public static void main(String[] args) {
    var obj = new FourSum();
    System.out.println(obj.fourSum(new int[] { 1, 0, -1, 0, -2, 2 }, 0));
    // [[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]

    System.out.println(obj.fourSum(new int[] { 2, 2, 2, 2, 2 }, 8)); // [[2,2,2,2]]

    System.out.println(obj.fourSum(new int[] { 1000000000, 1000000000, 1000000000, 1000000000 }, -294967296)); // []
  }

  public List<List<Integer>> fourSum(int[] nums, int target) {
    Arrays.sort(nums);
    var result = new HashSet<List<Integer>>();
    for (int i = 0; i < nums.length - 3; ++i) {
      for (int j = i + 1; j < nums.length - 2; ++j) {
        long sum = (long) target - nums[i] - nums[j];
        int left = j + 1, right = nums.length - 1;
        while (left < right) {
          long cur = (long) nums[left] + nums[right];
          if (cur == sum) {
            result.add(List.of(nums[i], nums[j], nums[left], nums[right]));
            ++left;
            --right;
          } else if (cur < sum) {
            ++left;
          } else {
            --right;
          }
        }
      }
    }
    return List.copyOf(result);
  }
}
