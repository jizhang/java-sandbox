package com.shzhangji.javasandbox.algs4.sort;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

/**
 * O(n), O(n2), O(n2), O(1), stable
 */
public class Insertion {

    @SuppressWarnings({"rawtypes", "unchecked"})
    public static void sort(Comparable[] a) {
        for (int i = 1; i < a.length; ++i) {
            int j = i;
            while (j > 0 && a[j].compareTo(a[j - 1]) < 0) {
                Comparable t = a[j];
                a[j] = a[j - 1];
                a[j - 1] = t;
                --j;
            }
        }
    }

    public static void main(String[] args) {
        String[] a = StdIn.readAllStrings();
        sort(a);
        StdOut.println(Arrays.toString(a));
    }

}
