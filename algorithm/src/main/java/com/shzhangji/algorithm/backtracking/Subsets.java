package com.shzhangji.algorithm.backtracking;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/subsets/
public class Subsets {
  public static void main(String[] args) {
    var obj = new Subsets();
    System.out.println(obj.subsets(new int[] {1,2,3}));
    // [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]

    System.out.println(obj.subsets(new int[] {0}));
    // [[],[0]]
  }

  public List<List<Integer>> subsets(int[] nums) {
    var result = new ArrayList<List<Integer>>();
    var tmp = new ArrayList<Integer>();
    recur(nums, 0, tmp, result);
    return result;
  }

  void recur(int[] nums, int start, List<Integer> tmp, List<List<Integer>> result) {
    result.add(List.copyOf(tmp));
    for (int i = start; i < nums.length; ++i) {
      tmp.add(nums[i]);
      recur(nums, i + 1, tmp, result);
      tmp.remove(tmp.size() - 1);
    }
  }
}
