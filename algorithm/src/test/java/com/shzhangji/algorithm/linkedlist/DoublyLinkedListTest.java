package com.shzhangji.algorithm.linkedlist;

import java.util.NoSuchElementException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DoublyLinkedListTest {
  @Test
  void testEmpty() {
    var list = new DoublyLinkedList();
    assertEquals("[]", list.toString());
  }

  @Test
  void testAdd() {
    var list = new DoublyLinkedList();
    list.add(1);
    list.add(2);
    list.add(3);
    assertEquals("[1, 2, 3]", list.toString());

    list.add(0, 4);
    assertEquals("[4, 1, 2, 3]", list.toString());
    list.add(1, 5);
    assertEquals("[4, 5, 1, 2, 3]", list.toString());
    list.add(4, 6);
    assertEquals("[4, 5, 1, 2, 6, 3]", list.toString());
    list.add(6, 7);
    assertEquals("[4, 5, 1, 2, 6, 3, 7]", list.toString());

    assertThrows(IndexOutOfBoundsException.class, () -> {
      list.add(-1, 0);
    });

    assertThrows(IndexOutOfBoundsException.class, () -> {
      list.add(8, 0);
    });
  }

  @Test
  void testRemove() {
    var list = new DoublyLinkedList();
    list.add(1);
    list.add(2);
    list.remove();
    assertEquals("[2]", list.toString());

    list.remove();
    assertThrows(NoSuchElementException.class, list::remove);

    list.add(1);
    list.add(2);
    list.add(3);
    list.add(4);
    list.add(5);
    assertEquals(1, list.remove(0));
    assertEquals(3, list.remove(1));
    assertEquals(5, list.remove(2));
    assertEquals("[2, 4]", list.toString());

    list.remove();
    assertThrows(IndexOutOfBoundsException.class, () -> {
      list.remove(1);
    });

    assertEquals(4, list.remove(0));
    assertEquals("[]", list.toString());
    assertThrows(IndexOutOfBoundsException.class, () -> {
      list.remove(0);
    });
  }
}
