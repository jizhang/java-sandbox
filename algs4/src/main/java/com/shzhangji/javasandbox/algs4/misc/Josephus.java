package com.shzhangji.javasandbox.algs4.misc;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Josephus {

    static class Node {
        Integer id;
        Node next;
    }

    public static void main(String[] args) {

        int N = StdIn.readInt();
        int M = StdIn.readInt();

        Node current = null, tail = null;
        for (int i = 0; i < N; ++i) {
            Node node = new Node();
            node.id = i + 1;
            if (current == null) {
                current = tail = node;
            } else {
                tail.next = node;
                tail = node;
            }
            node.next = current;
        }

        int count = 1;
        while (current != null) {

            if (current == current.next) {
                StdOut.println(current.id);
                break;
            }

            if (count == M - 1) {
                current.next = current.next.next;
                count = 0;
            }

            current = current.next;
            ++count;
        }

    }

}
