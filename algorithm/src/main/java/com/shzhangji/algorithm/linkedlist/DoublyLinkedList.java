package com.shzhangji.algorithm.linkedlist;

import java.util.NoSuchElementException;

public class DoublyLinkedList {
  Node head, tail;

  public DoublyLinkedList() {
    head = new Node();
    tail = new Node();
    head.next = tail;
  }

  // Append to the end.
  public boolean add(int element) {
    return true;
  }

  public void add(int index, int element) {
    if (index < 0) throw new IndexOutOfBoundsException();
  }

  // Remove first.
  public int remove() {
    if (head.next == null) throw new NoSuchElementException();
    return 0;
  }

  public int remove(int index) {
    if (index < 0) throw new IndexOutOfBoundsException();

    throw new IndexOutOfBoundsException();
  }

  public void clear() {
    head.next = tail;
  }

  @Override
  public String toString() {
    var sb = new StringBuilder("[");
    var current = head.next;
    while (current != null && current != tail) {
      sb.append(current.value);
      if (current.next != null) {
        sb.append(", ");
      }
      current = current.next;
    }
    return sb.append("]").toString();
  }

  static class Node {
    int value;
    Node prev, next;

    Node() {}

    Node(int value) {
      this.value = value;
    }
  }
}
