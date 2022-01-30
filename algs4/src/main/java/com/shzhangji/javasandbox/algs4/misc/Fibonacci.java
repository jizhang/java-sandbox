package com.shzhangji.javasandbox.algs4.misc;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Fibonacci {

    public static long fib1(long n) { // O(2^n)
        if (n <= 0) return 0;
        if (n == 1) return 1;
        return fib1(n - 1) + fib1(n - 2);
    }

    public static long fib2(long n) { // O(n)
        if (n <= 0) return 0;
        if (n == 1) return 1;
        long f1 = 0, f2 = 1, fn = 0;
        for (int i = 2; i <= n; ++i) {
            fn = f1 + f2;
            f1 = f2;
            f2 = fn;
        }
        return fn;
    }

    public static void main(String[] args) {

        int n = StdIn.readInt();

        StdOut.println(fib2(n));
        StdOut.println(fib1(n));

    }

}
