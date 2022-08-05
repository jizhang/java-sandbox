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
    var head = table[pos];
    if (head == null) {
      table[pos] = new Node(0, 0, new Node(key, value, null));
      return;
    }

    var node = head;
    do {
      node = node.next;
      if (node.key == key) {
        node.value = value;
        return;
      }
    } while (node.next != null);

    node.next = new Node(key, value, null);
  }

  public int get(int key) {
    int pos = key % table.length;
    if (table[pos] == null) {
      return -1;
    }

    var node = table[pos];
    do {
      node = node.next;
      if (node.key == key) {
        return node.value;
      }
    } while (node.next != null);

    return -1;
  }

  public void remove(int key) {
    int pos = key % table.length;
    var head = table[pos];
    if (head == null || head.next == null) {
      return;
    }

    var node = head;
    Node prev = null;
    do {
      prev = node;
      node = node.next;
      if (node.key == key) {
        prev.next = node.next;
        return;
      }
    } while (node.next != null);
  }

  @AllArgsConstructor
  static class Node {
    int key, value;
    Node next;
  }
}
