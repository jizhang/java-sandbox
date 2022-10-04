package com.shzhangji.algorithm.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

// https://leetcode.com/problems/top-k-frequent-elements/
public class TopKFrequentElements {
  public static void main(String[] args) {
    var obj = new TopKFrequentElements();
    System.out.println(Arrays.toString(obj.topKFrequent(new int[]{ 1, 1, 1, 2, 2, 3}, 2))); // [1, 2]
    System.out.println(Arrays.toString(obj.topKFrequent(new int[]{ 1 }, 1))); // [1]
  }

  @SuppressWarnings("unchecked")
  public int[] topKFrequent(int[] nums, int k) {
    var map = new HashMap<Integer, Integer>();
    for (int num : nums) {
      map.put(num, map.getOrDefault(num, 0) + 1);
    }

    List<Integer>[] buckets = new List[nums.length + 1];
    for (var entry : map.entrySet()) {
      var list = buckets[entry.getValue()];
      if (list == null) {
        list = new ArrayList<>();
        buckets[entry.getValue()] = list;
      }
      list.add(entry.getKey());
    }

    var result = new ArrayList<Integer>();
    for (int i = nums.length; i >= 0 && result.size() < k; --i) {
      var list = buckets[i];
      if (list != null) {
        result.addAll(list);
      }
    }

    return result.stream().mapToInt(Integer::intValue).toArray();
  }
}
