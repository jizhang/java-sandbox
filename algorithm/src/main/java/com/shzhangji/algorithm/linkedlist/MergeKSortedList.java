package com.shzhangji.algorithm.linkedlist;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

// https://leetcode.com/problems/merge-k-sorted-lists/
public class MergeKSortedList {
  public static void main(String[] args) {
    var list1 = new ListNode(1);
    list1.next = new ListNode(4);
    list1.next.next = new ListNode(5);
    var list2 = new ListNode(1);
    list2.next = new ListNode(3);
    list2.next.next = new ListNode(4);
    var list3 = new ListNode(2);
    list3.next = new ListNode(6);
    var result = new MergeKSortedList().mergeKLists(new ListNode[] { list1, list2, list3 });
    while (result != null) {
      System.out.print("->" + result.val);
      result = result.next;
    }
  }

  public ListNode mergeKLists(ListNode[] lists) {
    if (lists.length == 0) {
      return null;
    }

    var heap = new PriorityQueue<>(lists.length, new ListNodeComparator());
    for (var list : lists) {
      if (list != null) {
        heap.add(list);
      }
    }

    var head = new ListNode();
    var node = head;
    while (!heap.isEmpty()) {
      node.next = heap.remove();
      node = node.next;
      if (node.next != null) {
        heap.add(node.next);
      }
    }
    return head.next;
  }

  static class ListNodeComparator implements Comparator<ListNode> {
    @Override
    public int compare(ListNode a, ListNode b) {
      if (a.val == b.val) {
        return 0;
      }
      return a.val < b.val ? -1 : 1;
    }
  }
}
