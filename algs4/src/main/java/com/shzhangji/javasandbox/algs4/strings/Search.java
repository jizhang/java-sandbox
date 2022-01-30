package com.shzhangji.javasandbox.algs4.strings;

import edu.princeton.cs.algs4.StdOut;

public class Search {

    public static int bruteForce(String pat, String txt) {
        int M = pat.length();
        int N = txt.length();
        for (int i = 0; i <= N - M; ++i) {
            int j;
            for (j = 0; j < M; ++j) {
                if (txt.charAt(i + j) != pat.charAt(j)) {
                    break;
                }
            }
            if (j == M) {
                return i;
            }
        }
        return N;
    }

    public static int kmp(String pat, String txt) {

        // Build DFA from pattern.
        int M = pat.length();
        int R = 256;
        int[][] dfa = new int[R][M];
        dfa[pat.charAt(0)][0] = 1;
        for (int X = 0, j = 1; j < M; ++j) {
            for (int c = 0; c < R; ++c) {
                dfa[c][j] = dfa[c][X];
            }
            dfa[pat.charAt(j)][j] = j + 1;
            X = dfa[pat.charAt(j)][X];
        }

        // Simulate operation of DFA on txt.
        int i, j, N = txt.length();
        for (i = 0, j = 0; i < N && j < M; ++i) {
            j = dfa[txt.charAt(i)][j];
        }
        if (j == M) {
            return i - M;
        } else {
            return N;
        }
    }

    public static void main(String[] args) {

        String pattern = "needle";
        String text = "inahaystackneedleina";

        StdOut.println("bruteForce: " + bruteForce(pattern, text));
        StdOut.println("kmp: " + bruteForce(pattern, text));

    }

}
