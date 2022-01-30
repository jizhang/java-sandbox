package com.shzhangji.javasandbox.algs4.sort;

import com.shzhangji.javasandbox.algs4.util.StringUtils;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;

/**
 * http://algs4.cs.princeton.edu/23quicksort/
 * O(nlogn), O(nlogn), O(n2), O(logn)
 */
public class Quick {

    public static <T extends Comparable<? super T>> void sort(T[] a) {
        StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
    }

    private static <T extends Comparable<? super T>> void sort(T[] a, int lo, int hi) {
        if (lo >= hi) {
            return;
        }
        int j = partition(a, lo, hi);
        sort(a, lo, j - 1);
        sort(a, j + 1, hi);
    }

    private static <T extends Comparable<? super T>> int partition(T[] a, int lo, int hi) {

        int i = lo;
        int j = hi + 1;

        while (true) {

            while (less(a[++i], a[lo]) && i < hi);
            while (less(a[lo], a[--j]) && j > lo);

            if (i >= j) {
                break;
            }

            exch(a, i, j);
        }

        exch(a, lo, j);

        return j;
    }

    private static <T extends Comparable<? super T>> boolean less(T v, T w) {
        return v.compareTo(w) < 0;
    }

    private static void exch(Object[] pq, int i, int j) {
        Object swap = pq[i];
        pq[i] = pq[j];
        pq[j] = swap;
    }

    public static <T extends Comparable<? super T>> void sort1(T[] a) {
        StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
    }

    public static <T extends Comparable<? super T>> void sort1(T[] a, int lo, int hi) {

        T pivot = a[lo + (hi - lo) / 2];
        int i = lo, j = hi;

        while (i <= j) {
            while (less(a[i], pivot)) ++i;
            while (less(pivot, a[j])) --j;
            if (i <= j) {
                exch(a, i, j);
                ++i;
                --j;
            }
        }

        if (lo < j) sort1(a, lo, j);
        if (hi > i) sort1(a, i, hi);
    }

    public static void sort1(int[] a) {
        Integer[] aa = new Integer[a.length];
        for (int i = 0; i < a.length; ++i) {
            aa[i] = a[i];
        }
        sort(aa);
        for (int i = 0; i < aa.length; ++i) {
            a[i] = aa[i];
        }
    }

    public static void main(String[] args) {

        String[] a = StdIn.readAllStrings();
        String[] b = Arrays.copyOf(a, a.length);

        Quick.sort(a);
        StdOut.println(StringUtils.join(a, " "));

        Quick.sort1(b);
        StdOut.println(StringUtils.join(b, " "));

    }

}
