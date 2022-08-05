package com.shzhangji.algorithm.hashtable;

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

  Node[] hashTable;
  Node head, tail;
  int capacity;
  int count;

  public LRUCache(int capacity) {
    hashTable = new Node[capacity];
    head = new Node(0, 0, null, null, null);
    tail = new Node(0, 0, head, null, null);
    head.next = tail;
    this.capacity = capacity;
    this.count = 0;
  }

  public int get(int key) {
    var node = getTableNode(key);
    if (node == null) {
      return -1;
    }

    removeLinkedNode(node);
    appendLinkedNode(node);
    return node.value;
  }

  Node getTableNode(int key) {
    int pos = key % hashTable.length;
    var slot = hashTable[pos];

    if (slot == null || slot.hnext == null) {
      return null;
    }

    var node = slot;
    do {
      node = node.hnext;
      if (node.key == key) {
        return node;
      }
    } while (node.hnext != null);

    return null;
  }

  public void put(int key, int value) {
    var node = putTableNode(key, value);

    if (node.prev == null) {
      appendLinkedNode(node);
      ++count;
    } else {
      removeLinkedNode(node);
      appendLinkedNode(node);
    }

    if (count > capacity) {
      removeTableNode(head.next.key);
      removeLinkedNode(head.next);
      --count;
    }
  }

  Node putTableNode(int key, int value) {
    int pos = key % hashTable.length;
    var slot = hashTable[pos];

    if (slot == null) {
      slot = new Node(0, 0, null, null, null);
      hashTable[pos] = slot;
    }

    if (slot.hnext == null) {
      slot.hnext = new Node(key, value, null, null, null);
      return slot.hnext;
    } else {
      var node = slot;
      do {
        node = node.hnext;
        if (node.key == key) {
          node.value = value;
          return node;
        }
      } while (node.hnext != null);

      node.hnext = new Node(key, value, null, null, null);
      return node.hnext;
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

  void removeTableNode(int key) {
    int pos = key % hashTable.length;
    var slot = hashTable[pos];

    if (slot == null || slot.hnext == null) {
      return;
    }

    var node = slot;
    Node prev;
    do {
      prev = node;
      node = node.hnext;
      if (node.key == key) {
        prev.hnext = node.hnext;
        return;
      }
    } while (node.hnext != null);
  }

  static class Node {
    int key, value;
    Node prev, next, hnext;

    public Node(int key, int value, Node prev, Node next, Node hnext) {
      this.key = key;
      this.value = value;
      this.prev = prev;
      this.next = next;
      this.hnext = hnext;
    }
  }
}
