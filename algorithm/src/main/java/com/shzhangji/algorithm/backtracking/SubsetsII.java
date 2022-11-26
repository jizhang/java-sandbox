package com.shzhangji.algorithm.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// https://leetcode.com/problems/subsets-ii/
public class SubsetsII {
  public static void main(String[] args) {
    var obj = new SubsetsII();
    System.out.println(obj.subsetsWithDup(new int[] {1,2,2}));
    // [[],[1],[1,2],[1,2,2],[2],[2,2]]

    System.out.println(obj.subsetsWithDup(new int[] {0}));
    // [[],[0]]
  }

  public List<List<Integer>> subsetsWithDup(int[] nums) {
    Arrays.sort(nums);
    var result = new ArrayList<List<Integer>>();
    var tmp = new ArrayList<Integer>();
    recur(nums, 0, tmp, result);
    return result;
  }

  void recur(int[] nums, int start, List<Integer> tmp, List<List<Integer>> result) {
    result.add(List.copyOf(tmp));
    for (int i = start; i < nums.length; ++i) {
      if (i > start && nums[i] == nums[i - 1]) continue;
      tmp.add(nums[i]);
      recur(nums, i + 1, tmp, result);
      tmp.remove(tmp.size() - 1);
    }
  }
}
