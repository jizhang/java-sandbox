package com.shzhangji.algorithm.linkedlist;

// https://leetcode.com/problems/reorder-list/
public class ReorderList {
  public static void main(String[] args) {
    var obj = new ReorderList();
    var head = new ListNode(1);
    head.next = new ListNode(2);
    head.next.next = new ListNode(3);
    head.next.next.next = new ListNode(4);
    obj.reorderList(head);
    head.print(); // 1, 4, 2, 3

    head = new ListNode(1);
    head.next = new ListNode(2);
    head.next.next = new ListNode(3);
    head.next.next.next = new ListNode(4);
    head.next.next.next.next = new ListNode(5);
    obj.reorderList(head);
    head.print(); // 1, 5, 2, 4, 3
  }

  public void reorderList(ListNode head) {
    var middle = findMiddle(head);
    var list2 = middle.next;
    middle.next = null;
    list2 = reverse(list2);

    // Merge
    var list1 = head;
    while (list2 != null) {
      var t1 = list1.next;
      var t2 = list2.next;
      list1.next = list2;
      list2.next = t1;
      list2 = t2;
      list1 = t1;
    }
  }

  ListNode findMiddle(ListNode head) {
    var fast = head.next; // Different from FindMiddle.
    var slow = head;
    while (fast != null && fast.next != null) {
      fast = fast.next.next;
      slow = slow.next;
    }
    return slow;
  }

  ListNode reverse(ListNode head) {
    ListNode prev = null;
    var node = head;
    while (node != null) {
      var next = node.next;
      node.next = prev;
      prev = node;
      node = next;
    }
    return prev;
  }
}
