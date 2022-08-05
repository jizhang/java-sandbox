package com.shzhangji.algorithm.linkedlist;

// https://leetcode.com/problems/merge-two-sorted-lists/
public class MergeSortedList {
  public static void main(String[] args) {
    var list1 = new ListNode(1);
    list1.next = new ListNode(2);
    list1.next.next = new ListNode(4);
    var list2 = new ListNode(1);
    list2.next = new ListNode(3);
    list2.next.next = new ListNode(4);

    var result = new MergeSortedList().mergeTwoLists(list1, list2);
    while (result != null) {
      System.out.println(result.val);
      result = result.next;
    }
  }

  public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
    var head = new ListNode();
    var tail = head;
    while (list1 != null && list2 != null) {
      ListNode node;
      if (list1.val <= list2.val) {
        node = list1;
        list1 = list1.next;
      } else {
        node = list2;
        list2 = list2.next;
      }

      tail.next = node;
      tail = node;
    }

    if (list1 != null) {
      tail.next = list1;
    } else {
      tail.next = list2;
    }

    return head.next;
  }

  static class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; };
  }
}
