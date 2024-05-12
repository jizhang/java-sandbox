package com.shzhangji.javasandbox.concurrency;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

// http://homes.cs.washington.edu/~djg/teachingMaterials/spac/grossmanSPAC_forkJoinFramework.html
public class ForkJoinSum {

    static class Sum extends RecursiveTask<Long> {

        private static final long serialVersionUID = 1L;

        static final int SEQUENTIAL_THRESHOLD = 5_000;

        int low, high;
        int[] array;

        Sum(int[] array, int low, int high) {
            this.array = array;
            this.low = low;
            this.high = high;
        }

        @Override
        protected Long compute() {

            if (high - low <= SEQUENTIAL_THRESHOLD) {
                long sum = 0;
                for (int i = low; i < high; ++i) {
                    sum += array[i];
                }
                return sum;
            }

            int mid = low + (high - low) / 2;
            Sum left = new Sum(array, low, mid);
            Sum right = new Sum(array, mid, high);
            left.fork();
            long rightRes = right.compute();
            long leftRes = left.join();
            return leftRes + rightRes;
        }

    }

    public static void main(String[] args) {

        Random rand = new Random();
        int[] a = new int[10_000];
        for (int i = 0; i < a.length; ++i) {
            a[i] = rand.nextInt();
        }

        ForkJoinPool pool = new ForkJoinPool();
        long result = pool.invoke(new Sum(a, 0, a.length));
        System.out.println(result);
    }

}
