package com.shzhangji.javasandbox.algs4.dynamic;

import edu.princeton.cs.algs4.StdOut;

public class LongestCommonSubsequence {

    /**
     * http://introcs.cs.princeton.edu/java/96optimization/LCS.java.html
     * https://www.youtube.com/watch?v=Wv1y45iqsbk
     * \begin{equation}
     * c[i, j] =
     * \left\{
     *   \begin{array}{ll}
     *     c[i - 1, j - 1] + 1 & \mbox{if } x[i] = y[j], \\
     *     max(c[i, j - 1], c[i - 1, j]) & \mbox{otherwise}
     *   \end{array}
     * \right.
     * \end{equation}
     */
    public static String lcs(String a, String b) {

        int M = a.length();
        int N = b.length();
        int[][] opt = new int[M + 1][N + 1];

        for (int i = 1; i <= M; ++i) {
            for (int j = 1; j <= N; ++j) {
                if (a.charAt(i - 1) == b.charAt(j - 1)) {
                    opt[i][j] = opt[i - 1][j - 1] + 1;
                } else {
                    opt[i][j] = Math.max(opt[i][j - 1], opt[i - 1][j]);
                }
            }
        }

        printMatrix(opt);

        StringBuilder sb = new StringBuilder();
        for (int i = M, j = N; i > 0 && j > 0; ) {
            if (a.charAt(i - 1) == b.charAt(j - 1)) {
                sb.append(a.charAt(i - 1));
                --i;
                --j;
            } else if (opt[i - 1][j] >= opt[i][j - 1]) {
                --i;
            } else {
                --j;
            }
        }

        return sb.reverse().toString();
    }

    public static void printMatrix(int[][] opt) {
        for (int i = 0; i < opt.length; ++i) {
            for (int j = 0; j < opt[i].length; ++j) {
                StdOut.print(opt[i][j]);
                if (j + 1 < opt[i].length) {
                    StdOut.print(" ");
                }
            }
            StdOut.println();
        }
    }

    public static void main(String[] args) {
        String x = "ACCGGGTTAC";
        String y = "AGGACCA";
        StdOut.println(lcs(x, y)); // AGGAC
    }

}
