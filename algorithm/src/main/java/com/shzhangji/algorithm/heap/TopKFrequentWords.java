package com.shzhangji.algorithm.heap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

// https://leetcode.com/problems/top-k-frequent-words/
public class TopKFrequentWords {
  public static void main(String[] args) {
    var obj = new TopKFrequentWords();
    System.out.println(obj.topKFrequent(new String[] {"i","love","leetcode","i","love","coding"}, 2)); // ["i","love"]
    System.out.println(obj.topKFrequent(new String[] {"the","day","is","sunny","the","the","the","sunny","is","is"}, 4)); // ["the","is","sunny","day"]
  }

  public List<String> topKFrequent(String[] words, int k) {
    var map = new HashMap<String, Integer>();
    for (var word : words) {
      map.put(word, map.getOrDefault(word, 0) + 1);
    }

    var heap = new PriorityQueue<Map.Entry<String, Integer>>((a, b) -> {
      int c = Integer.compare(b.getValue(), a.getValue());
      return c != 0 ? c : a.getKey().compareTo(b.getKey());
    });
    heap.addAll(map.entrySet());

    var result = new ArrayList<String>();
    for (int i = 0; i < k; ++i) {
      result.add(heap.remove().getKey());
    }

    return result;
  }
}
