package com.shzhangji.algorithm.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// https://leetcode.com/problems/combination-sum/
public class CombinationSum {
  public static void main(String[] args) {
    var obj = new CombinationSum();
    System.out.println(obj.combinationSum(new int[] {2, 3, 6, 7}, 7));
    // [2, 2, 3], [7]

    System.out.println(obj.combinationSum(new int[] {2, 3, 5}, 8));
    // [2, 2, 2, 2], [2, 3, 3], [3, 5]

    System.out.println(obj.combinationSum(new int[] {2}, 1));
    // []
  }

  public List<List<Integer>> combinationSum(int[] candidates, int target) {
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
      tmp.add(candidates[i]);
      recur(candidates, target - candidates[i], i, tmp, result);
      tmp.remove(tmp.size() - 1);
    }
  }
}
