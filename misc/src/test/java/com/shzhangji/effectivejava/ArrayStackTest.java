package com.shzhangji.effectivejava;

import org.junit.Test;
import static org.junit.Assert.*;

public class ArrayStackTest {
  @Test
  public void testStack() {
    Stack<String> stack = new ArrayStack<>(1);
    stack.push("a");
    stack.push("b");
    assertFalse(stack.isEmpty());
    assertEquals("b", stack.pop());
    assertEquals("a", stack.pop());
    assertTrue(stack.isEmpty());
  }
}
