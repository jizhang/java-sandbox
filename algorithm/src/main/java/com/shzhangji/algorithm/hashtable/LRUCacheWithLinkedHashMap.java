package com.shzhangji.algorithm.hashtable;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCacheWithLinkedHashMap {
  public static void main(String[] args) {
    var cache = new LRUCacheWithLinkedHashMap(2);
    cache.put(2, 1);
    cache.put(1, 1);
    cache.put(2, 3);
    cache.put(4, 1);
    System.out.println(cache.get(1)); // -1
    System.out.println(cache.get(2)); // 3
  }

  Map<Integer, Integer> hashTable;

  public LRUCacheWithLinkedHashMap(int capacity) {
    hashTable = new LinkedHashMap<>(capacity, 0.75F, true) {
      @Override
      protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
        return size() > capacity;
      }
    };
  }

  public int get(int key) {
    var value = hashTable.get(key);
    return value == null ? -1 : value;
  }

  public void put(int key, int value) {
    hashTable.put(key, value);
  }
}
