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
    if (table[pos] == null) {
      table[pos] = new Node(key, value, null);
    } else {
      var node = table[pos];
      Node prevNode = null;
      while (node != null) {
        if (node.key == key) {
          node.value = value;
          break;
        }
        prevNode = node;
        node = node.next;
      }

      if (node == null) {
        assert prevNode != null;
        prevNode.next = new Node(key, value, null);
      }
    }
  }

  public int get(int key) {
    int pos = key % table.length;
    if (table[pos] == null) {
      return -1;
    }

    var node = table[pos];
    while (node != null) {
      if (node.key == key) {
        break;
      }
      node = node.next;
    }

    assert node != null;
    return node.value;
  }

  public void remove(int key) {
    int pos = key % table.length;
    if (table[pos] == null) {
      return;
    }

    var node = table[pos];
    Node prevNode = null;
    while (node != null) {
      if (node.key == key) {
        if (prevNode == null) {
          table[pos] = null;
        } else {
          prevNode.next = node.next;
        }
        break;
      }
      prevNode = node;
      node = node.next;
    }
  }

  @AllArgsConstructor
  static class Node {
    int key, value;
    Node next;
  }
}
