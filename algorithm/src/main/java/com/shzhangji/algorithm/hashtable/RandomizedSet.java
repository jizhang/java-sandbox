package com.shzhangji.algorithm.hashtable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

// https://leetcode.com/problems/insert-delete-getrandom-o1/
public class RandomizedSet {
  public static void main(String[] args) {
    RandomizedSet obj = new RandomizedSet();
    System.out.println(obj.insert(1)); // true
    System.out.println(obj.remove(2)); // false
    System.out.println(obj.insert(2)); // true
    System.out.println(obj.getRandom()); // 1 or 2
    System.out.println(obj.remove(1)); // true
    System.out.println(obj.insert(2)); // false
    System.out.println(obj.getRandom()); // 2
  }

  Map<Integer, Integer> map;
  List<Integer> list;
  Random random;

  public RandomizedSet() {
    map = new HashMap<>();
    list = new ArrayList<>();
    random = new Random();
  }

  public boolean insert(int val) {
    if (map.containsKey(val)) return false;
    list.add(val);
    map.put(val, list.size() - 1);
    return true;
  }

  public boolean remove(int val) {
    if (!map.containsKey(val)) return false;
    int index = map.get(val);
    int lastIndex = list.size() - 1;
    if (index != lastIndex) {
      int lastVal = list.get(lastIndex);
      list.set(index, lastVal);
      map.put(lastVal, index);
    }
    list.remove(lastIndex);
    map.remove(val);
    return true;
  }

  public int getRandom() {
    return list.get(random.nextInt(list.size()));
  }
}
