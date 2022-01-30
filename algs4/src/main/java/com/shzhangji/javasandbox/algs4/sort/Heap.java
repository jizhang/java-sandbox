package com.shzhangji.javasandbox.algs4.sort;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * http://algs4.cs.princeton.edu/24pq/
 * O(nlogn), O(nlogn), O(nlogn), O(1)
 */
public class Heap {

    public static <T extends Comparable<? super T>> void sort(T[] pq) {

        int N = pq.length;

        // heap construction
        for (int k = N / 2; k >= 1; --k) {
            sink(pq, k, N);
        }

        // sortdown
        while (N > 1) {
            exch(pq, 1, N);
            --N;
            sink(pq, 1, N);
        }

    }

    private static <T extends Comparable<? super T>> void sink(T[] pq, int k, int N) {

        while (2 * k <= N) {
            int j = 2 * k;
            if (j < N && less(pq, j, j + 1)) {
                ++j;
            }
            if (!less(pq, k, j)) {
                break;
            }
            exch(pq, k, j);
            k = j;
        }

    }

    private static <T extends Comparable<? super T>> boolean less(T[] pq, int i, int j) {
        return pq[i - 1].compareTo(pq[j - 1]) < 0;
    }

    private static void exch(Object[] pq, int i, int j) {
        Object swap = pq[i - 1];
        pq[i - 1] = pq[j - 1];
        pq[j - 1] = swap;
    }

    private static void show(Object[] a) {
        for (int i = 0; i < a.length; ++i) {
            StdOut.println(a[i]);
        }
    }

    public static void main(String[] args) {
        String[] a = StdIn.readAllStrings();
        sort(a);
        show(a);
    }

}
