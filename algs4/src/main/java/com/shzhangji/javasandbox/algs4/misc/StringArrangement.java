package com.shzhangji.javasandbox.algs4.misc;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class StringArrangement {

    public static void permutation(String s) {
        if (s.isEmpty()) {
            return;
        }
        permutation(s.toCharArray(), 0);
    }

    private static void permutation(char[] arr, int pos) {

        if (pos == arr.length) {
            StdOut.println(new String(arr));
            return;
        }

        for (int i = pos; i < arr.length; ++i) {
            swap(arr, i, pos);
            permutation(arr, pos + 1);
            swap(arr, i, pos);
        }
    }

    private static void swap(char[] a, int i, int j) {
        char t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static void combination(String s) {

        if (s.isEmpty()) {
            return;
        }

        for (int i = 1; i < Math.pow(2, s.length()); ++i) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < s.length(); ++j) {
                if ((i & (int) Math.pow(2, j)) != 0) {
                    sb.append(s.charAt(j));
                }
            }
            StdOut.println(sb);
        }

    }

    public static void main(String[] args) {
        String s = StdIn.readString();
        permutation(s);
        combination(s);
    }

}
