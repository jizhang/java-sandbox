package com.shzhangji.algorithm.queue;

import java.util.NoSuchElementException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LinkedListQueueTest {
  @Test
  void testAdd() {
    var queue = new LinkedListQueue();
    queue.add(1);
    queue.add(2);
    assertEquals(1, queue.remove());
    assertEquals(2, queue.remove());
    assertThrows(NoSuchElementException.class, queue::remove);
  }
}
