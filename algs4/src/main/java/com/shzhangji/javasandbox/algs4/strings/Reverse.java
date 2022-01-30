package com.shzhangji.javasandbox.algs4.strings;

import edu.princeton.cs.algs4.StdOut;

public class Reverse {

    public static void reverse(char[] s) {
        for (int i = 0; i < s.length / 2; ++i) {
            char t = s[i];
            s[i] = s[s.length - 1 - i];
            s[s.length - 1 - i] = t;
        }
    }

    public static void main(String[] args) {
        char[] s = "abc".toCharArray();
        reverse(s);
        StdOut.println(String.valueOf(s));
    }

}
