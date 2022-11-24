package com.shzhangji.algorithm.backtracking;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/combination-sum-iii/
public class CombinationSumIII {
  public static void main(String[] args) {
    var obj = new CombinationSumIII();
    System.out.println(obj.combinationSum3(3, 7)); // [1, 2, 4]
    System.out.println(obj.combinationSum3(3, 9)); // [1, 2, 6], [1, 3, 5], [2, 3, 4]
    System.out.println(obj.combinationSum3(4, 1)); // []
  }

  public List<List<Integer>> combinationSum3(int k, int n) {
    var result = new ArrayList<List<Integer>>();
    var tmp = new ArrayList<Integer>();
    recur(k, n, 1, tmp, result);
    return result;
  }

  void recur(int k, int n, int start, List<Integer> tmp, List<List<Integer>> result) {
    if (k == 0 && n == 0) {
      result.add(List.copyOf(tmp));
      return;
    }
    if (k == 0 || n < 0) return;

    for (int i = start; i <= 9; ++i) {
      tmp.add(i);
      recur(k - 1, n - i, i + 1, tmp, result);
      tmp.remove(tmp.size() - 1);
    }
  }
}
