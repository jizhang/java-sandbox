package com.shzhangji.algorithm.queue;

import java.util.NoSuchElementException;

public class LinkedListQueue {
  Node head;
  Node tail;

  public LinkedListQueue() {
    head = tail = null;
  }

  public boolean add(int element) {
    if (head == null) {
      head = tail = new Node(element);
    } else {
      tail.next = new Node(element);
      tail = tail.next;
    }
    return true;
  }

  public int remove() {
    if (head == null) throw new NoSuchElementException();
    var value = head.value;
    if (head == tail) {
      head = tail = null;
    } else {
      head = head.next;
    }
    return value;
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
