package com.shzhangji.javasandbox.algs4.sort;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

/**
 * O(n), O(n2), O(n2), O(1), stable
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public class Bubble {

    public static void sort1(Comparable[] a) {
        for (int i = 0; i < a.length; ++i) {
            for (int j = 1; j < a.length - i; ++j) {
                if (a[j - 1].compareTo(a[j]) > 0) {
                    Comparable t = a[j - 1];
                    a[j - 1] = a[j];
                    a[j] = t;
                }
            }
        }
    }

    public static void sort2(Comparable[] a) {

        while (true) {

            boolean swapped = false;

            for (int i = 0; i < a.length - 1; ++i) {
                if (a[i].compareTo(a[i + 1]) > 0) {
                    Comparable t = a[i];
                    a[i] = a[i + 1];
                    a[i + 1] = t;
                    swapped = true;
                }
            }

            if (!swapped) {
                break;
            }
        }

    }

    public static void main(String[] args) {

        int algo = StdIn.readInt();
        String[] a = StdIn.readAllStrings();

        switch (algo) {
        case 1: sort1(a); break;
        case 2: sort2(a); break;
        default: throw new IllegalArgumentException();
        }

        StdOut.println(Arrays.toString(a));
    }

}
