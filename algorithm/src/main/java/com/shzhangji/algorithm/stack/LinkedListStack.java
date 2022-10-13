package com.shzhangji.algorithm.stack;

import java.util.NoSuchElementException;

public class LinkedListStack {
  Node head;

  public LinkedListStack() {
    head = new Node(); // W/ sentinel
  }

  public void push(int element) {
    head.next = new Node(element, head.next);
  }

  public int pop() {
    if (head.next == null) throw new NoSuchElementException();
    var value = head.next.value;
    head.next = head.next.next;
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
