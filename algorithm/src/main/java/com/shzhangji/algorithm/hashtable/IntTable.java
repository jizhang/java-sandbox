package com.shzhangji.algorithm.hashtable;

import lombok.AllArgsConstructor;

public class IntTable<V> {
  public static void main(String[] args) {
    var table = new IntTable<Integer>();
    table.put(1, 2);
    table.put(1, 3);
    System.out.println(table.get(1)); // 3
    table.put(2, 1);
    System.out.println(table.get(2)); // 1
    table.remove(1);
    System.out.println(table.get(1)); // null
  }

  @SuppressWarnings("unchecked")
  Node<V>[] table = new Node[1];

  public void put(int key, V value) {
    int pos = key % table.length;
    var head = table[pos];
    if (head == null) {
      head = new Node<>(0, null, null);
      table[pos] = head;
    }

    if (head.next == null) {
      head.next = new Node<>(0, value, null);
    } else {
      var node = head;
      do {
        node = node.next;
        if (node.key == key) {
          node.value = value;
          return;
        }
      } while (node.next != null);

      node.next = new Node<>(key, value, null);
    }
  }

  public V get(int key) {
    int pos = key % table.length;
    if (table[pos] == null) {
      return null;
    }

    var node = table[pos];
    do {
      node = node.next;
      if (node.key == key) {
        return node.value;
      }
    } while (node.next != null);

    return null;
  }

  public void remove(int key) {
    int pos = key % table.length;
    var head = table[pos];
    if (head == null || head.next == null) {
      return;
    }

    var node = head;
    Node<V> prev;
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
  static class Node<V> {
    int key;
    V value;
    Node<V> next;
  }
}
