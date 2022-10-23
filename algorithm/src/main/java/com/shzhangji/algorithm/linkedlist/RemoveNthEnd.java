package com.shzhangji.algorithm.linkedlist;

import java.util.ArrayList;

// https://leetcode.com/problems/remove-nth-node-from-end-of-list/
public class RemoveNthEnd {
  public static void main(String[] args) {
    var obj = new RemoveNthEnd();
    var head = new ListNode(1);
    head.next = new ListNode(2);
    head.next.next = new ListNode(3);
    head.next.next.next = new ListNode(4);
    head.next.next.next.next = new ListNode(5);
    head = obj.removeNthFromEnd(head, 2);
    head.print(); // 1, 2, 3, 5

    head = new ListNode(1);
    head = obj.removeNthFromEnd(head, 1);
    assert head == null;

    head = new ListNode(1);
    head.next = new ListNode(2);
    head = obj.removeNthFromEnd(head, 1);
    head.print(); // 1
  }

  // Follow up: Could you do this in one pass?
  public ListNode removeNthFromEnd(ListNode head, int n) {
    return usePointers(head, n);
  }

  ListNode usePointers(ListNode head, int n) {
    assert head != null;

    var fast = head;
    for (int i = 0; i < n; ++i) {
      fast = fast.next;
    }

    if (fast == null) return head.next;

    var slow = head;
    while (fast.next != null) {
      slow = slow.next;
      fast = fast.next;
    }

    slow.next = slow.next.next;
    return head;
  }

  ListNode useArray(ListNode head, int n) {
    assert head != null;

    var nodes = new ArrayList<ListNode>();
    var current = head;
    while (current != null) {
      nodes.add(current);
      current = current.next;
    }

    int i = nodes.size() - n;
    if (i == 0) {
      return head.next;
    }

    nodes.get(i - 1).next = nodes.get(i).next;
    return head;
  }
}
