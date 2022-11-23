package com.shzhangji.algorithm.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// https://leetcode.com/problems/combination-sum-ii/
public class CombinationSumII {
  public static void main(String[] args) {
    var obj = new CombinationSumII();
    System.out.println(obj.combinationSum2(new int[] {10, 1, 2, 7, 6, 1, 5}, 8));
    // [1, 1, 6], [1, 2, 5], [1, 7], [2, 6]

    System.out.println(obj.combinationSum2(new int[] {2, 5, 2, 1, 2}, 5));
    // [1, 2, 2], [5]
  }

  public List<List<Integer>> combinationSum2(int[] candidates, int target) {
    Arrays.sort(candidates);
    var result = new ArrayList<List<Integer>>();
    var tmp = new ArrayList<Integer>();
    recur(candidates, target, 0, tmp, result);
    return result;
  }

  void recur(int[] candidates, int target, int start, List<Integer> tmp, List<List<Integer>> result) {
    if (target < 0) return;
    if (target == 0) {
      result.add(List.copyOf(tmp));
      return;
    }

    for (int i = start; i < candidates.length; ++i) {
      if (i > start && candidates[i] == candidates[i - 1]) continue;
      tmp.add(candidates[i]);
      recur(candidates, target - candidates[i], i + 1, tmp, result);
      tmp.remove(tmp.size() - 1);
    }
  }
}
