package com.shzhangji.algorithm.linkedlist;

import java.util.NoSuchElementException;

public class DoublyLinkedList {
  Node head, tail;

  public DoublyLinkedList() {
    head = new Node();
    tail = new Node();
    head.next = tail;
    tail.prev = head;
  }

  // Append to the end.
  public boolean add(int element) {
    var node = new Node(element, tail.prev, tail);
    tail.prev.next = node;
    tail.prev = node;
    return true;
  }

  public void add(int index, int element) {
    if (index < 0) throw new IndexOutOfBoundsException();

    var current = head.next;
    int i = 0;
    while (current != tail) {
      if (i == index) break;
      current = current.next;
      ++i;
    }

    if (i == index) {
      var node = new Node(element, current.prev, current);
      current.prev.next = node;
      current.prev = node;
    } else {
      throw new IndexOutOfBoundsException();
    }
  }

  // Remove first.
  public int remove() {
    if (head.next == tail) throw new NoSuchElementException();
    head.next.next.prev = head;
    head.next = head.next.next;
    return 0;
  }

  public int remove(int index) {
    if (index < 0) throw new IndexOutOfBoundsException();

    var current = head.next;
    int i = 0;
    while (current != tail) {
      if (i == index) {
        current.prev.next = current.next;
        current.next.prev = current.prev;
        return current.value;
      }
      current = current.next;
      ++i;
    }

    throw new IndexOutOfBoundsException();
  }

  public void clear() {
    head.next = tail;
  }

  @Override
  public String toString() {
    var sb = new StringBuilder("[");
    var current = head.next;
    while (current != tail) {
      sb.append(current.value);
      if (current.next != tail) {
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

    Node(int value, Node prev, Node next) {
      this.value = value;
      this.prev = prev;
      this.next = next;
    }
  }
}
