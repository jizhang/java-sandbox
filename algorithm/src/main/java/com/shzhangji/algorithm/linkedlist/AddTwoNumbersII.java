package com.shzhangji.algorithm.linkedlist;

// https://leetcode.com/problems/add-two-numbers-ii/
public class AddTwoNumbersII {
  public static void main(String[] args) {
    var obj = new AddTwoNumbersII();
    var l1 = ListNode.make(7, 2, 4, 3);
    var l2 = ListNode.make(5, 6, 4);
    var result = obj.addTwoNumbers(l1, l2);
    result.print(); // 7, 8, 0, 7

    l1 = ListNode.make(2, 4, 3);
    l2 = ListNode.make(5, 6, 4);
    result = obj.addTwoNumbers(l1, l2);
    result.print(); // 8, 0, 7

    l1 = ListNode.make(0);
    l2 = ListNode.make(0);
    result = obj.addTwoNumbers(l1, l2);
    result.print(); // 0
  }

  // Follow up: Could you solve it without reversing the input lists?
  public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    int len1 = getLength(l1), len2 = getLength(l2);

    var head = new ListNode(0);
    while (len1 > 0) {
      int sum = 0;
      if (len1 >= len2) {
        sum += l1.val;
        l1 = l1.next;
        --len1;
      }
      if (len2 > len1) {
        sum += l2.val;
        l2 = l2.next;
        --len2;
      }
      head.next = new ListNode(sum, head.next);
    }

    var node = head.next;
    head = new ListNode(0);
    int carry = 0;
    while (node != null) {
      int sum = node.val + carry;
      carry = sum / 10;
      head.next = new ListNode(sum % 10, head.next);
      node = node.next;
    }

    if (carry > 0) head.val = carry;
    else head = head.next;

    return head;
  }

  int getLength(ListNode head) {
    int len = 0;
    while (head != null) {
      ++len;
      head = head.next;
    }
    return len;
  }
}
