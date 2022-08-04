package com.shzhangji.algorithm.hashtable;

// https://leetcode.com/problems/lru-cache/
public class LRUCache {
  public static void main(String[] args) {
    var cache = new LRUCache(2);
    cache.put(1, 1);
    cache.put(2, 2);
    System.out.println(cache.get(1)); // 1
    cache.put(3, 3);
    System.out.println(cache.get(2)); // -1
    cache.put(4, 4);
    System.out.println(cache.get(1)); // -1
    System.out.println(cache.get(3)); // 3
    System.out.println(cache.get(4)); // 4
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

    node.prev.next = node.next;
    tail.next = node;
    tail = node;

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

    tail.next = node;
    node.prev = tail;
    tail = node;

    if (++count > capacity) {
      pos = head.next.key % hashTable.length;
      node = hashTable[pos];
      Node prevNode = null;
      while (node != null) {
        if (node.key == head.next.key) {
          break;
        }
        prevNode = node;
        node = node.hnext;
      }

      assert node != null;
      if (prevNode == null) {
        hashTable[pos] = node.hnext;
      } else {
        prevNode.hnext = node.hnext;
      }

      head.next = head.next.next;
      head.next.prev = head;
    }
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
