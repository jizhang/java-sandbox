package com.shzhangji.javasandbox.algs4.basic;

public interface Stack<T> extends Iterable<T> {
    void push(T value);
    T pop();
    T peek();
    boolean isEmpty();
}
