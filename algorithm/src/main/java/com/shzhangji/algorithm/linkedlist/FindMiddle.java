package com.shzhangji.algorithm.linkedlist;

// https://leetcode.com/problems/middle-of-the-linked-list/
public class FindMiddle {
  public static void main(String[] args) {
    var obj = new FindMiddle();
    var head = new ListNode(1);
    head.next = new ListNode(2);
    head.next.next = new ListNode(3);
    System.out.println(obj.middleNode(head).val); // 2

    head.next.next.next = new ListNode(4);
    System.out.println(obj.middleNode(head).val); // 3
  }

  public ListNode middleNode(ListNode head) {
    return towPointers(head);
  }

  ListNode firstTry(ListNode head) {
    var middle = head;
    int count = 0;
    while (head != null) {
      ++count;
      if (count % 2 == 0) {
        middle = middle.next;
      }
      head = head.next;
    }
    return middle;
  }

  ListNode towPointers(ListNode head) {
    ListNode slow = head, fast = head;
    while (fast != null && fast.next != null) {
      slow = slow.next;
      fast = fast.next.next;
    }
    return slow;
  }
}
