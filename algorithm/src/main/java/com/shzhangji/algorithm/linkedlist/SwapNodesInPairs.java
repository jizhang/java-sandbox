package com.shzhangji.algorithm.linkedlist;

// https://leetcode.com/problems/swap-nodes-in-pairs/
public class SwapNodesInPairs {
  public static void main(String[] args) {
    var obj = new SwapNodesInPairs();

    var head = new ListNode(1);
    head.next = new ListNode(2);
    head.next.next = new ListNode(3);
    head.next.next.next = new ListNode(4);
    head = obj.swapPairs(head);
    head.print(); // 2, 1, 4, 3

    head.next.next.next.next = new ListNode(5);
    head = obj.swapPairs(head);
    head.print(); // 1, 2, 3, 4, 5

    obj.swapPairs(null);
    obj.swapPairs(new ListNode(1));
  }

  public ListNode swapPairs(ListNode head) {
    return withIteration(head);
  }

  ListNode firstTry(ListNode head) {
    if (head == null || head.next == null) return head;
    var sentinel = new ListNode(0, head);

    ListNode pp = sentinel, p = head, current = head.next;
    int i = 1;

    while (current != null) {
      if (i % 2 > 0) {
        pp.next = current;
        p.next = current.next;
        current.next = p;
        p = current;
      }

      pp = p;
      p = pp.next;
      current = p.next;
      ++i;
    }

    return sentinel.next;
  }

  ListNode withIteration(ListNode head) {
    if (head == null || head.next == null) return head;
    var sentinel = new ListNode(0, head);
    ListNode prev = sentinel, current = head;
    while (current != null && current.next != null) {
      prev.next = current.next;
      current.next = current.next.next;
      prev.next.next = current;

      prev = current;
      current = current.next;
    }
    return sentinel.next;
  }

  // https://leetcode.com/problems/swap-nodes-in-pairs/discuss/11030/My-accepted-java-code.-used-recursion.
  ListNode withRecursion(ListNode head) {
    if (head == null || head.next == null) return head;
    var result = head.next;
    head.next = withRecursion(head.next.next);
    result.next = head;
    return result;
  }
}
