package com.shzhangji.algorithm.linkedlist;

// https://leetcode.com/problems/linked-list-cycle/
public class LinkedListCycle {
  public static void main(String[] args) {
    var obj = new LinkedListCycle();
    var head = new ListNode(3);
    head.next = new ListNode(2);
    head.next.next = new ListNode(0);
    head.next.next.next = new ListNode(-4);
    System.out.println(obj.hasCycle(head));

    head.next.next.next.next = head.next;
    System.out.println(obj.hasCycle(head));
  }

  public boolean hasCycle(ListNode head) {
    var i = head;
    var j = head;
    while (i != null && i.next != null && j != null && j.next != null) {
      i = i.next;
      j = j.next.next;
      if (i == j) {
        return true;
      }
    }
    return false;
  }
}
