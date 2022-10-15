package com.shzhangji.algorithm.queue;

import java.util.NoSuchElementException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CircularQueueTest {
  @Test
  void testAdd() {
    var queue = new CircularQueue(3);
    queue.add(1);
    queue.add(2);
    assertEquals(1, queue.remove());

    queue.add(3);
    queue.add(4);
    assertThrows(IllegalStateException.class, () -> {
      queue.add(5);
    });

    queue.remove();
    queue.remove();
    queue.remove();
    assertThrows(NoSuchElementException.class, queue::remove);
  }
}
