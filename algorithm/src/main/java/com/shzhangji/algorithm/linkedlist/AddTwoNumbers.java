package com.shzhangji.algorithm.linkedlist;

// https://leetcode.com/problems/add-two-numbers/submissions/
public class AddTwoNumbers {
  public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    var head = new ListNode();
    var current = head;

    int carry = 0;
    while (l1 != null || l2 != null) {
      var node = new ListNode(0);

      if (l1 != null) {
        node.val += l1.val;
        l1 = l1.next;
      }

      if (l2 != null) {
        node.val += l2.val;
        l2 = l2.next;
      }

      node.val += carry;

      if (node.val >= 10) {
        carry = 1;
        node.val -= 10;
      } else {
        carry = 0;
      }

      current.next = node;
      current = current.next;
    }

    if (carry == 1) current.next = new ListNode(1);
    return head.next;
  }
}
