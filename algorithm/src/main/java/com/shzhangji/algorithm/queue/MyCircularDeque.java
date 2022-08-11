package com.shzhangji.algorithm.queue;

// https://leetcode.com/problems/design-circular-deque/
public class MyCircularDeque {
  public static void main(String[] args) {
    MyCircularDeque myCircularDeque = new MyCircularDeque(3);
    System.out.println(myCircularDeque.insertLast(1));  // return True
    System.out.println(myCircularDeque.insertLast(2));  // return True
    System.out.println(myCircularDeque.insertFront(3)); // return True
    System.out.println(myCircularDeque.insertFront(4)); // return False, the queue is full.
    System.out.println(myCircularDeque.getRear());      // return 2
    System.out.println(myCircularDeque.isFull());       // return True
    System.out.println(myCircularDeque.deleteLast());   // return True
    System.out.println(myCircularDeque.insertFront(4)); // return True
    System.out.println(myCircularDeque.getFront());     // return 4
  }

  int[] data;
  int head, tail, count;

  public MyCircularDeque(int k) {
    data = new int[k];
  }

  public boolean insertFront(int value) {
    if (count == data.length) return false;
    data[head = dec(head)] = value;
    ++count;
    return true;
  }

  public boolean insertLast(int value) {
    if (count == data.length) return false;
    data[tail] = value;
    ++count;
    tail = inc(tail);
    return true;
  }

  public boolean deleteFront() {
    if (count == 0) return false;
    head = inc(head);
    --count;
    return true;
  }

  public boolean deleteLast() {
    if (count == 0) return false;
    --count;
    tail = dec(tail);
    return true;
  }

  public int getFront() {
    return count == 0 ? -1 : data[head];
  }

  public int getRear() {
    return count == 0 ? -1 : data[dec(tail)];
  }

  public boolean isEmpty() {
    return count == 0;
  }

  public boolean isFull() {
    return count == data.length;
  }

  int inc(int n) {
    return n == data.length - 1 ? 0 : n + 1;
  }

  int dec(int n) {
    return n == 0 ? data.length - 1 : n - 1;
  }
}

