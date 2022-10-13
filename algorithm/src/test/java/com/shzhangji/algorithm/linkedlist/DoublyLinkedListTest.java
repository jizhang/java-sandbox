package com.shzhangji.algorithm.linkedlist;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DoublyLinkedListTest {
  @Test
  void testEmpty() {
    var list = new DoublyLinkedList();
    assertEquals("[]", list.toString());
  }
}
