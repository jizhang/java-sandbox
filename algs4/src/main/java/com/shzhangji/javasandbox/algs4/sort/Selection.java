package com.shzhangji.javasandbox.algs4.sort;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

/**
 * O(n2), O(n2), O(n2), O(1), unstable
 */
public class Selection {

    @SuppressWarnings({"rawtypes", "unchecked"})
    public static void sort(Comparable[] a) {
        for (int i = 0; i < a.length; ++i) {
            int min = i;
            for (int j = i; j < a.length; ++j) {
                if (a[j].compareTo(a[min]) < 0) {
                    min = j;
                }
            }
            Comparable t = a[i];
            a[i] = a[min];
            a[min] = t;
        }
    }

    public static void main(String[] args) {
        String[] a = StdIn.readAllStrings();
        sort(a);
        StdOut.println(Arrays.toString(a));
    }

}
