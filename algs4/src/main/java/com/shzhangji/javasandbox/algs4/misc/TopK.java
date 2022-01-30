package com.shzhangji.javasandbox.algs4.misc;

import com.shzhangji.javasandbox.algs4.util.StringUtils;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

@SuppressWarnings({"rawtypes", "unchecked"})
public class TopK {

    public static Comparable[] findTopK(Comparable[] a, int K) {

        Comparable[] heap = new Comparable[K];
        int N = 0;

        for (Comparable item : a) {
            if (N < K) {
                heap[N] = item;
                siftUp(heap, N);
                ++N;
            } else if (item.compareTo(heap[0]) > 0) {
                heap[0] = item;
                siftDown(heap, 0, N);
            }
        }

        while (N > 1) {
            swap(heap, 0, N - 1);
            --N;
            siftDown(heap, 0, N);
        }

        return Arrays.copyOf(heap, K);
    }

    private static void siftUp(Comparable[] heap, int k) {

        while (k > 0) {
            int parent = (k - 1) / 2;
            if (heap[k].compareTo(heap[parent]) >= 0) {
                break;
            }
            swap(heap, k, parent);
            k = parent;
        }

    }

    private static void siftDown(Comparable[] heap, int k, int N) {

        while (k < N / 2) {
            int child = k * 2 + 1;
            if (child < N - 1 && heap[child].compareTo(heap[child + 1]) > 0) {
                ++child;
            }
            if (heap[k].compareTo(heap[child]) <= 0) {
                break;
            }
            swap(heap, k, child);
            k = child;
        }

    }

    private static void swap(Object[] a, int i, int j) {
        Object t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static void main(String[] args) {

        int K = StdIn.readInt();
        String[] a = StdIn.readAllStrings();
        StdOut.println(StringUtils.join(findTopK(a, K), " ")); // TODO in-place

    }

}
