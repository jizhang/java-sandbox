package com.shzhangji.javasandbox.concurrency;

import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// https://dzone.com/articles/java-concurrency-condition
@Slf4j
public class MessageQueue<T> {
  private Queue<T> queue = new LinkedList<>();
  private Lock lock = new ReentrantLock();
  private Condition hasMessages = lock.newCondition();

  public void publish(T message) {
    lock.lock();
    try {
      queue.offer(message);
      hasMessages.signal();
    } finally {
      lock.unlock();
    }
  }

  public T receive() throws InterruptedException {
    lock.lock();
    try {
      while (queue.isEmpty()) {
        hasMessages.await();
      }
      return queue.poll();
    } finally {
      lock.unlock();
    }
  }

  public static void main(String[] args) {
    var queue = new MessageQueue<String>();
    var executor = Executors.newCachedThreadPool();

    executor.execute(() -> {
      for (int i = 0; i < 10; ++i) {
        var message = "Message #" + i;
        log.info("Sending [{}]", message);
        queue.publish(message);
        try {
          Thread.sleep(200);
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
      }
    });

    Runnable worker = () -> {
      for (int i = 0; i < 5; ++i) {
        String message = null;
        try {
          message = queue.receive();
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
        log.info("Received [{}] in {}", message, Thread.currentThread().getName());
      }
    };

    executor.execute(worker);
    executor.execute(worker);

    executor.shutdown();
  }
}
