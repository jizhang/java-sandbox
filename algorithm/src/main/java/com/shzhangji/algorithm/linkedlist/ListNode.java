package com.shzhangji.algorithm.linkedlist;

public class ListNode {
  int val;
  ListNode next;

  ListNode() {}

  ListNode(int val) {
    this.val = val;
  }

  ListNode(int val, ListNode next) {
    this.val = val;
    this.next = next;
  }

  public void print() {
    var current = this;
    while (current != null) {
      System.out.print("->" + current.val);
      current = current.next;
    }
    System.out.println();
  }

  public static ListNode make(int... values) {
    var sentinel = new ListNode(0);
    var current = sentinel;
    for (var val : values) {
      current.next = new ListNode(val);
      current = current.next;
    }
    return sentinel.next;
  }
}
