package com.shzhangji.algorithm.stack;

import java.util.NoSuchElementException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LinkedListStackTest {
  @Test
  void testPush() {
    var stack = new LinkedListStack();
    stack.push(1);
    stack.push(2);
    assertEquals(2, stack.pop());
    assertEquals(1, stack.pop());

    assertThrows(NoSuchElementException.class, stack::pop);
  }
}
