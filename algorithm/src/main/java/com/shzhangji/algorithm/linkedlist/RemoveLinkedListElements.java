package com.shzhangji.algorithm.linkedlist;

// https://leetcode.com/problems/remove-linked-list-elements/
public class RemoveLinkedListElements {
  public static void main(String[] args) {
    var obj = new RemoveLinkedListElements();

    var head = new ListNode(1);
    head.next = new ListNode(2);
    head.next.next = new ListNode(6);
    head.next.next.next = new ListNode(3);
    head.next.next.next.next = new ListNode(4);
    head.next.next.next.next.next = new ListNode(5);
    head.next.next.next.next.next.next = new ListNode(6);
    print(obj.removeElements(head, 6));

    head = null;
    print(obj.removeElements(head, 1));

    head = new ListNode(7);
    head.next = new ListNode(7);
    head.next.next = new ListNode(7);
    head.next.next.next = new ListNode(7);
    print(obj.removeElements(head, 7));
  }

  static void print(ListNode head) {
    if (head == null) System.out.println("null");
    else head.print();
  }

  public ListNode removeElements(ListNode head, int val) {
    return withRecursion(head, val);
  }

  ListNode firstTry(ListNode head, int val) {
    ListNode prev = null, current = head;
    while (current != null) {
      if (current.val == val) {
        if (prev == null) {
          head = current.next;
        } else {
          prev.next = current.next;
        }
      } else {
        prev = current;
      }
      current = current.next;
    }
    return head;
  }

  ListNode withSentinel(ListNode head, int val) {
    var sentinel = new ListNode(0, head);
    ListNode prev = sentinel, current = head;
    while (current != null) {
      if (current.val == val) {
        prev.next = current.next;
      } else {
        prev = current;
      }
      current = current.next;
    }
    return sentinel.next;
  }

  // https://leetcode.com/problems/remove-linked-list-elements/discuss/57306/3-line-recursive-solution
  ListNode withRecursion(ListNode head, int val) {
    if (head == null) return null;
    if (head.val == val) return withRecursion(head.next, val);
    head.next = withRecursion(head.next, val);
    return head;
  }
}
