package com.shzhangji.algorithm.heap;

import java.util.NoSuchElementException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MinHeapTest {
  @Test
  void testAdd() {
    var heap = new MinHeap(3);
    heap.add(9);
    heap.add(8);
    assertEquals(8, heap.remove());

    heap.add(6);
    heap.add(7);
    assertThrows(IllegalStateException.class, () -> {
      heap.add(5);
    });

    assertEquals(6, heap.remove());
    assertEquals(7, heap.remove());
    assertEquals(9, heap.remove());
    assertThrows(NoSuchElementException.class, heap::remove);
  }
}
