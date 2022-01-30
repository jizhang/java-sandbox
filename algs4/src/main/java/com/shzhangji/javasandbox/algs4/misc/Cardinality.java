package com.shzhangji.javasandbox.algs4.misc;

import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Cardinality {

    public static int card1(Object[] a) {
        Set<Object> s = new HashSet<>();
        for (Object o : a) {
            s.add(o);
        }
        return s.size();
    }

    /**
     * http://blog.notdot.net/2012/09/Dam-Cool-Algorithms-Cardinality-Estimation
     */
    public static int card2(Object[] a, int k) {

        int numBuckets = (int) Math.pow(2, k);
        int[] maxZeroes = new int[numBuckets];

        for (Object value : a) {
            int h = value.hashCode();
            int bucket = h & (numBuckets - 1);
            int bucketHash = h >> k;
            maxZeroes[bucket] = Math.max(maxZeroes[bucket], trailingZeroes(bucketHash));
        }

        return (int) (Math.pow(2, Arrays.stream(maxZeroes).sum() / (double) numBuckets) * numBuckets * 0.79402);
    }

    private static int trailingZeroes(int num) {
        if (num == 0) {
            return 3;
        }
        int p = 0;
        while (((num >> p) & 1) == 0) {
            ++p;
        }
        return p;
    }

    public static void main(String[] args) {

        int N = 100_000;
        Double[] a = new Double[N];
        Random rand = new Random();

        for (int i = 0; i < 10; ++i) {

            for (int j = 0; j < N; ++j) {
                a[j] = rand.nextDouble();
            }

            StdOut.println((double) N / card2(a, 10));
        }
    }

}
