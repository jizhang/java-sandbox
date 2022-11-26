package com.shzhangji.algorithm.backtracking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

// https://leetcode.com/problems/permutations-ii/
public class PermutationsII {
  public static void main(String[] args) {
    var obj = new PermutationsII();
    System.out.println(obj.permuteUnique(new int[] {1,1,2}));
    // [[1,1,2],[1,2,1],[2,1,1]]

    System.out.println(obj.permuteUnique(new int[]{1,2,3}));
    // [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
  }

  public List<List<Integer>> permuteUnique(int[] nums) {
    var result = new HashSet<List<Integer>>();
    var tmp = new ArrayList<Integer>();
    recur(nums, tmp, result);
    return List.copyOf(result);
  }

  void recur(int[] nums, List<Integer> tmp, Set<List<Integer>> result) {
    if (tmp.size() == nums.length) {
      result.add(tmp.stream().map(i -> nums[i]).collect(Collectors.toList()));
      return;
    }

    for (int i = 0; i < nums.length; ++i) {
      if (tmp.contains(i)) continue;
      tmp.add(i);
      recur(nums, tmp, result);
      tmp.remove(tmp.size() - 1);
    }
  }
}
