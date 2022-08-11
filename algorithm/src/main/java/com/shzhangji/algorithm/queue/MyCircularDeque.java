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
    System.out.println("---");

    myCircularDeque = new MyCircularDeque(3);
    System.out.println(myCircularDeque.insertFront(9)); // return True
    System.out.println(myCircularDeque.getRear());      // return 9
    System.out.println(myCircularDeque.insertFront(9)); // return True
    System.out.println(myCircularDeque.getRear());      // return 9
    System.out.println(myCircularDeque.insertLast(5));  // return True
    System.out.println(myCircularDeque.getFront());     // return 9
    System.out.println(myCircularDeque.getRear());      // return 5
    System.out.println(myCircularDeque.getFront());     // return 9
    System.out.println(myCircularDeque.insertLast(8));  // return False
    System.out.println(myCircularDeque.deleteLast());   // return True
    System.out.println(myCircularDeque.getFront());     // return 9
  }

  int[] data;
  int head, tail, count, maxCount;

  public MyCircularDeque(int k) {
    assert k >= 1;
    data = new int[k];
    head = 0;
    tail = 0;
    count = 0;
    maxCount = k;
  }

  public boolean insertFront(int value) {
    if (count == maxCount) return false;
    if (count >= 1 && --head == -1) head = data.length - 1;
    data[head] = value;
    ++count;
    return true;
  }

  public boolean insertLast(int value) {
    if (count == maxCount) return false;
    if (count >= 1 && ++tail == data.length) tail = 0;
    data[tail] = value;
    ++count;
    return true;
  }

  public boolean deleteFront() {
    if (count == 0) return false;
    if (count > 1 && ++head == data.length) head = 0;
    --count;
    return true;
  }

  public boolean deleteLast() {
    if (count == 0) return false;
    if (count > 1 && --tail == -1) tail = data.length - 1;
    --count;
    return true;
  }

  public int getFront() {
    return count == 0 ? -1 : data[head];
  }

  public int getRear() {
    return count == 0 ? -1 : data[tail];
  }

  public boolean isEmpty() {
    return count == 0;
  }

  public boolean isFull() {
    return count == maxCount;
  }
}

