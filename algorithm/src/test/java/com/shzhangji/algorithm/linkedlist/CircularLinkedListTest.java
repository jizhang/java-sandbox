package com.shzhangji.algorithm.linkedlist;

import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;
import org.junit.jupiter.api.Test;

class CircularLinkedListTest {
  @Test
  void testEmpty() {
    var list = new CircularLinkedList();
    assertEquals("[]", list.toString());
  }

  @Test
  void testAdd() {
    var list = new CircularLinkedList();
    list.add(1);
    list.add(2);
    list.add(3);
    assertEquals("[1, 2, 3]", list.toString());
  }

  @Test
  void testRemove() {
    var list = new CircularLinkedList();
    list.add(1);
    assertEquals(1, list.remove());
    assertEquals("[]", list.toString());

    list.add(2);
    list.add(3);
    assertEquals(2, list.remove());
    assertEquals("[3]", list.toString());

    list.remove();
    assertThrows(NoSuchElementException.class, list::remove);
  }
}
