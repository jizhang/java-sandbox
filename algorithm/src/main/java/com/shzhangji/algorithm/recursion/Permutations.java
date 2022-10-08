package com.shzhangji.algorithm.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

// https://leetcode.com/problems/permutations/
public class Permutations {
  public static void main(String[] args) {
    var obj = new Permutations();
    System.out.println(obj.permute(new int[] { 1, 2, 3 }));
    System.out.println(obj.permute(new int[] { 0, 1 }));
    System.out.println(obj.permute(new int[] { 1 }));
  }

  public List<List<Integer>> permute(int[] nums) {
    return backTracking(nums);
  }

  List<List<Integer>> naive(int[] nums) {
    if (nums.length == 1) return List.of(List.of(nums[0]));
    var result = new ArrayList<List<Integer>>();
    for (final int num : nums) {
      var sub = Arrays.stream(nums).filter(n -> n != num).toArray();
      for (var list : naive(sub)) {
        var item = new ArrayList<>(list);
        item.add(num);
        result.add(item);
      }
    }
    return result;
  }

  List<List<Integer>> backTracking(int[] nums) {
    var result = new ArrayList<List<Integer>>();
    var tmp = new ArrayList<Integer>();
    backTracking(nums, result, tmp);
    return result;
  }

  void backTracking(int[] nums, List<List<Integer>> result, List<Integer> tmp) {
    if (tmp.size() == nums.length) {
      result.add(List.copyOf(tmp));
      return;
    }

    for (int num : nums) {
      if (tmp.contains(num)) continue;
      tmp.add(num);
      backTracking(nums, result, tmp);
      tmp.remove(tmp.size() - 1);
    }
  }
}
