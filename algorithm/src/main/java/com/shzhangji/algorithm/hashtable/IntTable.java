package com.shzhangji.algorithm.hashtable;

import lombok.AllArgsConstructor;

public class IntTable {
  public static void main(String[] args) {
    var table = new IntTable();
    table.put(1, 2);
    table.put(1, 3);
    System.out.println(table.get(1)); // 3
    table.put(2, 1);
    System.out.println(table.get(2)); // 1
    table.remove(1);
    System.out.println(table.get(1)); // -1
  }

  Node[] table = new Node[1];

  public void put(int key, int value) {
    int pos = key % table.length;
    var node = table[pos];
    Node prev = null;
    while (node != null) {
      if (node.key == key) {
        node.value = value;
        return;
      }
      prev = node;
      node = node.next;
    }

    node = new Node(key, value, null);
    if (prev == null) {
      table[pos] = node;
    } else {
      prev.next = node;
    }
  }

  public int get(int key) {
    int pos = key % table.length;
    var node = table[pos];
    while (node != null) {
      if (node.key == key) {
        return node.value;
      }
      node = node.next;
    }
    return -1;
  }

  public void remove(int key) {
    int pos = key % table.length;
    var node = table[pos];

    Node prev = null;
    while (node != null) {
      if (node.key == key) {
        if (prev == null) {
          table[pos] = null;
        } else {
          prev.next = node.next;
        }
      }
      prev = node;
      node = node.next;
    }
  }

  @AllArgsConstructor
  static class Node {
    int key, value;
    Node next;
  }
}
