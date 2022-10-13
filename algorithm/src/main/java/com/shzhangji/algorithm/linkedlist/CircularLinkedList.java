package com.shzhangji.algorithm.linkedlist;

import java.util.NoSuchElementException;

public class CircularLinkedList {
  Node head;

  public CircularLinkedList() {
    // W/o sentinel.
  }

  // Append to the end.
  public boolean add(int element) {
    if (head == null) {
      head = new Node(element);
      head.next = head;
      return true;
    }

    var current = head;
    while (current.next != head) current = current.next;
    current.next = new Node(element, head);
    return true;
  }

  // Remove first.
  public int remove() {
    if (head == null) throw new NoSuchElementException();

    if (head.next == head) {
      var value = head.value;
      head = null;
      return value;
    }

    var tail = head.next;
    while (tail.next != head) tail = tail.next;
    var value = head.value;
    head = head.next;
    tail.next = head;
    return value;
  }

  @Override
  public String toString() {
    if (head == null) return "[]";

    var sb = new StringBuilder("[");
    var current = head;
    do {
      sb.append(current.value);
      if (current.next != head) {
        sb.append(", ");
      }
      current = current.next;
    } while (current != head);
    return sb.append("]").toString();
  }

  static class Node {
    int value;
    Node next;

    Node() {}

    Node(int value) {
      this.value = value;
    }

    Node(int value, Node next) {
      this.value = value;
      this.next = next;
    }
  }
}
