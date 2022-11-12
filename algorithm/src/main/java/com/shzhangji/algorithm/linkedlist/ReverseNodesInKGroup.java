package com.shzhangji.algorithm.linkedlist;

// https://leetcode.com/problems/reverse-nodes-in-k-group/
public class ReverseNodesInKGroup {
  public static void main(String[] args) {
    var obj = new ReverseNodesInKGroup();
    var head = ListNode.make(1, 2, 3, 4, 5);
    head = obj.reverseKGroup(head, 2);
    head.print(); // 2, 1, 4, 3, 5

    head = ListNode.make(1, 2, 3, 4, 5);
    head = obj.reverseKGroup(head, 3);
    head.print(); // 3, 2, 1, 4, 5
  }

  public ListNode reverseKGroup(ListNode head, int k) {
    if (head == null) return null;

    var tail = head;
    int count = 0;
    while (tail != null) {
      if (++count >= k) break;
      tail = tail.next;
    }
    if (count < k) return head;

    var rest = reverseKGroup(tail.next, k);
    tail.next = null;
    reverseNodes(head); // == tail
    head.next = rest;
    return tail;
  }

  ListNode reverseNodes(ListNode head) {
    if (head == null || head.next == null) return head;
    var rest = reverseNodes(head.next);
    head.next.next = head;
    head.next = null;
    return rest;
  }
}
