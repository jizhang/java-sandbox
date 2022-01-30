package com.shzhangji.javasandbox.algs4.basic;

import java.util.Iterator;

public class LinkedList<T> implements Stack<T>, Queue<T> {

    class Node {
        T value;
        Node next;
        Node(T value) {
            this.value = value;
        }
    }

    private Node head;

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public void push(T value) {
        Node node = new Node(value);
        node.next = head;
        head = node;
    }

    @Override
    public T pop() {

        if (head == null) {
            return null;
        }

        Node node = head;
        head = head.next;
        return node.value;
    }

    @Override
    public T peek() {
        return head == null ? null : head.value;
    }

    @Override
    public void enqueue(T value) {

        if (head == null) {
            head = new Node(value);
            return;
        }

        Node node = head;
        while (node.next != null) {
            node = node.next;
        }
        node.next = new Node(value);
    }

    @Override
    public T dequeue() {
        return pop();
    }

    @Override
    public Iterator<T> iterator() {
        return new ListIterator();
    }

    class ListIterator implements Iterator<T> {

        Node current = head;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            Node node = current;
            current = current.next;
            return node.value;
        }

    }

    public void reverse() {
        Node parent = null;
        Node current = head;
        while (current != null) {
            Node next = current.next;
            current.next = parent;
            parent = current;
            current = next;
        }
        head = parent;
    }

    public void reverse1() {
        reverse1(head);
    }

    private void reverse1(Node current) {
        if (current == null) {
            return;
        }
        if (current.next == null) {
            head = current;
            return;
        }
        reverse1(current.next);
        current.next.next = current;
        current.next = null;
    }

}
