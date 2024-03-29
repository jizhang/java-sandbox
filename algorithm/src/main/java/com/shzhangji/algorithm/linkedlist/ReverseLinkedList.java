package com.shzhangji.algorithm.linkedlist;

// https://leetcode.com/problems/reverse-linked-list/
public class ReverseLinkedList {
  public static void main(String[] args) {
    var head = new ListNode(1);
    head.next = new ListNode(2);
    head.next.next = new ListNode(3);
    head.next.next.next = new ListNode(4);
    head.next.next.next.next = new ListNode(5);
    head = new ReverseLinkedList().reverseWithRecursion(head);
    while (head != null) {
      System.out.println(head.val);
      head = head.next;
    }
  }

  public ListNode reverseList(ListNode head) {
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

  public ListNode reverseWithRecursion(ListNode head) {
    if (head == null || head.next == null) {
      return head;
    }

    var rest = reverseWithRecursion(head.next);
    head.next.next = head;
    head.next = null;

    return rest;
  }
}
