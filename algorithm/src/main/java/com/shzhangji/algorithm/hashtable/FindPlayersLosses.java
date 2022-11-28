package com.shzhangji.algorithm.hashtable;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

// https://leetcode.com/problems/find-players-with-zero-or-one-losses/
public class FindPlayersLosses {
  public static void main(String[] args) {
    var obj = new FindPlayersLosses();
    System.out.println(obj.findWinners(new int[][] {
      {1,3},{2,3},{3,6},{5,6},{5,7},{4,5},{4,8},{4,9},{10,4},{10,9}
    })); // [[1,2,10],[4,5,7,8]]

    System.out.println(obj.findWinners(new int[][] {
      {2,3},{1,3},{5,4},{6,4}
    })); // [[1,2,5,6],[]]
  }

  public List<List<Integer>> findWinners(int[][] matches) {
    var map = new TreeMap<Integer, Integer>();
    for (var match : matches) {
      map.put(match[0], map.getOrDefault(match[0], 0));
      map.put(match[1], map.getOrDefault(match[1], 0) + 1);
    }

    var result = List.<List<Integer>>of(new ArrayList<>(), new ArrayList<>());
    for (var entry : map.entrySet()) {
      if (entry.getValue() <= 1) {
        result.get(entry.getValue()).add(entry.getKey());
      }
    }

    return result;
  }
}
