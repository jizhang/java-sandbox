package com.shzhangji.algorithm.hashtable;

import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/lru-cache/
public class LRUCache {
  public static void main(String[] args) {
//    var cache = new LRUCache(2);
//    cache.put(1, 1);
//    cache.put(2, 2);
//    System.out.println(cache.get(1)); // 1
//    cache.put(3, 3);
//    System.out.println(cache.get(2)); // -1
//    cache.put(4, 4);
//    System.out.println(cache.get(1)); // -1
//    System.out.println(cache.get(3)); // 3
//    System.out.println(cache.get(4)); // 4

//    var cache = new LRUCache(1);
//    cache.put(2, 1);
//    cache.getAndPrint(2); // 1
//    cache.put(3, 2);
//    cache.getAndPrint(2); // -1
//    cache.getAndPrint(3); // 2

//    var cache = new LRUCache(2);
//    cache.put(2, 1);
//    cache.put(2, 2);
//    cache.getAndPrint(2); // 2
//    cache.put(1, 1);
//    cache.put(4, 1);
//    cache.getAndPrint(2); // -1

    var cache = new LRUCache(2);
    cache.put(2, 1);
    cache.put(1, 1);
    cache.put(2, 3);
    cache.put(4, 1);
    cache.getAndPrint(1); // -1
    cache.getAndPrint(2); // 3
  }

  public void getAndPrint(int key) {
    int value = get(key);
    System.out.println(value);
  }

  Map<Integer, Node> hashTable;
  Node head, tail;
  int capacity;

  public LRUCache(int capacity) {
    hashTable = new HashMap<>(capacity);
    head = new Node(0, 0, null, null);
    tail = new Node(0, 0, head, null);
    head.next = tail;
    this.capacity = capacity;
  }

  public int get(int key) {
    var node = hashTable.get(key);
    if (node == null) {
      return -1;
    }

    removeLinkedNode(node);
    appendLinkedNode(node);
    return node.value;
  }

  public void put(int key, int value) {
    var node = hashTable.get(key);
    if (node == null) {
      node = new Node(key, value, null, null);
      hashTable.put(key, node);
      appendLinkedNode(node);
    } else {
      node.value = value;
      removeLinkedNode(node);
      appendLinkedNode(node);
    }

    if (hashTable.size() > capacity) {
      hashTable.remove(head.next.key);
      removeLinkedNode(head.next);
    }
  }

  void removeLinkedNode(Node node) {
    node.prev.next = node.next;
    node.next.prev = node.prev;
  }

  void appendLinkedNode(Node node) {
    tail.prev.next = node;
    node.prev = tail.prev;
    node.next = tail;
    tail.prev = node;
  }

  static class Node {
    int key, value;
    Node prev, next;

    public Node(int key, int value, Node prev, Node next) {
      this.key = key;
      this.value = value;
      this.prev = prev;
      this.next = next;
    }
  }
}
