package com.shzhangji.javasandbox.algs4.sort;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

/**
 * http://www.sanfoundry.com/java-program-implement-bucket-sort/
 * O(n+k), O(n+k), O(n^2), O(n)
 */
public class Bucket {

    public static void sort(int[] a) {

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < a.length; ++i) {
            if (a[i] > max) {
                max = a[i];
            }
        }

        int[] buckets = new int[max + 1];
        for (int i = 0; i < a.length; ++i) {
            buckets[a[i]]++;
        }

        int i = 0;
        for (int j = 0; j < buckets.length; ++j) {
            for (int k = 0; k < buckets[j]; ++k) {
                a[i++] = j;
            }
        }

    }

    public static void main(String[] args) {
        int[] a = StdIn.readAllInts();
        sort(a);
        StdOut.println(Arrays.toString(a));
    }

}
