package com.shzhangji.algorithm.linkedlist;

import java.util.NoSuchElementException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SinglyLinkedListTest {
  @Test
  void testEmpty() {
    var list = new SinglyLinkedList();
    assertEquals("[]", list.toString());
  }

  @Test
  void testAdd() {
    var list = new SinglyLinkedList();
    list.add(1);
    list.add(2);
    list.add(3);
    assertEquals("[1, 2, 3]", list.toString());

    list.clear();
    list.add(0, 1);
    list.add(0, 2);
    list.add(1, 3);
    list.add(3, 4);
    assertEquals("[2, 3, 1, 4]", list.toString());

    assertThrows(IndexOutOfBoundsException.class, () -> {
      list.add(5, 5);
    });
  }

  @Test
  void testRemove() {
    var list = new SinglyLinkedList();
    list.add(1);
    list.add(2);
    list.remove();
    assertEquals("[2]", list.toString());

    list.add(3);
    list.add(4);
    list.remove(0);
    assertEquals("[3, 4]", list.toString());
    list.remove(1);
    assertEquals("[3]", list.toString());

    assertThrows(IndexOutOfBoundsException.class, () -> {
      list.remove(1);
    });

    list.remove();
    assertThrows(NoSuchElementException.class, list::remove);
  }
}
