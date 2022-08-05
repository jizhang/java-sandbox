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
//    cache.getAndPrint(2);
//    cache.put(3, 2);
//    cache.getAndPrint(2);
//    cache.getAndPrint(3);

    var cache = new LRUCache(2);
    cache.put(2, 1);
    cache.put(2, 2);
    cache.getAndPrint(2);
    cache.put(1, 1);
    cache.put(4, 1);
    cache.get(2);
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
    head = tail = new Node(0, 0, null, null, null);
    this.capacity = capacity;
    this.count = 0;
  }

  public int get(int key) {
    int pos = key % hashTable.length;
    var node = hashTable[pos];

    while (node != null) {
      if (node.key == key) {
        break;
      }
      node = node.hnext;
    }

    if (node == null) {
      return -1;
    }

    removeLinkedNode(node);
    appendLinkedNode(node);

    return node.value;
  }

  public void put(int key, int value) {
    int pos = key % hashTable.length;
    var node = hashTable[pos];
    if (node == null) {
      node = new Node(key, value, null, null, null);
      hashTable[pos] = node;
    } else {
      Node prevNode = null;
      while (node != null) {
        if (node.key == key) {
          break;
        }
        prevNode = node;
        node = node.hnext;
      }

      if (node == null) {
        node = new Node(key, value, null, null, null);
        prevNode.hnext = node;
      }
    }

    appendLinkedNode(node);

    if (++count > capacity) {
      removeFromCache(head.next);
    }
  }

  void removeLinkedNode(Node node) {
    node.prev.next = node.next;
    if (node.next == null) {
      tail = node.prev;
    } else {
      node.next.prev = node.prev;
    }
  }

  void appendLinkedNode(Node node) {
    tail.next = node;
    node.prev = tail;
    tail = node;
  }

  void removeFromCache(Node removingNode) {
    int pos = removingNode.key % hashTable.length;
    var node = hashTable[pos];
    Node prevNode = null;
    while (node != null) {
      if (node.key == removingNode.key) {
        break;
      }
      prevNode = node;
      node = node.hnext;
    }

    assert node != null;
    hashTable[pos] = node.hnext;
    if (prevNode != null) {
      prevNode.hnext = node.hnext;
    }

    removeLinkedNode(node);
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
