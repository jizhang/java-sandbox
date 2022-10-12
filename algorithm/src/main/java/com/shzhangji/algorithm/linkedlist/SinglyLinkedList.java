package com.shzhangji.algorithm.linkedlist;

import java.util.NoSuchElementException;

public class SinglyLinkedList {
  Node head;

  SinglyLinkedList() {
    head = new Node();
  }

  // Append to the end.
  public boolean add(int element) {
    var current = head;
    while (current.next != null) current = current.next;
    current.next = new Node(element);
    return true;
  }

  public void add(int index, int element) {
    if (index < 0) throw new IndexOutOfBoundsException();

    var prev = head;
    var current = head.next;
    int i = 0;
    while (current != null) {
      if (i == index) break;
      prev = current;
      current = current.next;
      ++i;
    }

    if (i == index) { // In case list is empty and index = 0.
      prev.next = new Node(element, current);
    } else {
      throw new IndexOutOfBoundsException();
    }
  }

  // Remove first.
  public int remove() {
    if (head.next == null) throw new NoSuchElementException();
    var value = head.next.value;
    head.next = head.next.next;
    return value;
  }

  public int remove(int index) {
    if (index < 0) throw new IndexOutOfBoundsException();

    var prev = head;
    var current = head.next;
    int i = 0;
    while (current != null) {
      if (i == index) {
        prev.next = current.next;
        return current.value;
      }
      prev = current;
      current = current.next;
      ++i;
    }

    throw new IndexOutOfBoundsException();
  }

  public void clear() {
    head.next = null;
  }

  @Override
  public String toString() {
    var sb = new StringBuilder("[");
    var current = head.next;
    while (current != null) {
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
