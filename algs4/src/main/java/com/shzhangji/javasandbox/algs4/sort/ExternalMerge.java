package com.shzhangji.javasandbox.algs4.sort;

import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class ExternalMerge {

    public static final int BUCKET_SIZE = 5;

    public static void sort(int[] a) throws Exception {

        // split & sort
        int numBuckets = (int) Math.ceil((double) a.length / BUCKET_SIZE);
        int[][] buckets = new int[numBuckets][];
        for (int i = 0; i < numBuckets; ++i) {
            int size = Math.min(BUCKET_SIZE, a.length - i * BUCKET_SIZE);
            int[] bucket = new int[size];
            for (int j = 0; j < size; ++j) {
                bucket[j] = a[i * BUCKET_SIZE + j];
            }
            Quick.sort1(bucket);
            buckets[i] = bucket;
        }

        // merge
        int[] prev = new int[0];
        int[] next;
        for (int[] bucket : buckets) {
            next = new int[prev.length + bucket.length];
            int i = 0, j = 0;
            for (int k = 0; k < next.length; ++k) {
                if (i == prev.length) {
                    next[k] = bucket[j++];
                } else if (j == bucket.length) {
                    next[k] = prev[i++];
                } else if (prev[i] < bucket[j]) {
                    next[k] = prev[i++];
                } else {
                    next[k] = bucket[j++];
                }
            }
            prev = next;
        }

        // assign
        for (int i = 0; i < prev.length; ++i) {
            a[i] = prev[i];
        }
    }

    public static void main(String[] args) throws Exception {
        int[] a = new int[] { 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0 };
        sort(a);
        StdOut.println(Arrays.toString(a));
    }

}
