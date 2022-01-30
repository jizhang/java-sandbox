package com.shzhangji.javasandbox.algs4.context;

import com.shzhangji.javasandbox.algs4.dynamic.LongestCommonSubsequence;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class LongestCommonSubstring {

    public static String bruteForce(String a, String b) {

        String result = "";

        for (int i = 0; i < a.length(); ++i) {
            for (int j = 0; j < b.length(); ++j) {
                int k;
                for (k = 0; i + k < a.length() && j + k < b.length(); ++k) {
                    if (a.charAt(i + k) != b.charAt(j + k)) {
                        break;
                    }
                }
                if (k > result.length()) {
                    result = a.substring(i, i + k);
                }
            }
        }

        return result;
    }

    public static String suffixArray(String a, String b) {

        String text = a + "\001" + b;
        SuffixArray suffix = new SuffixArray(text);

        int N = text.length();
        int N1 = a.length();

        String result = "";
        for (int i = 1; i < N; ++i) {
            if (suffix.index(i) < N1 && suffix.index(i - 1) < N1) {
                continue;
            }
            if (suffix.index(i) > N1 && suffix.index(i - 1) > N1) {
                continue;
            }
            int length = suffix.lcp(i);
            if (length > result.length()) {
                result = text.substring(suffix.index(i), suffix.index(i) + length);
            }
        }

        return result;
    }

    /**
     * http://introcs.cs.princeton.edu/java/96optimization/LCS.java.html
     */
    public static String dynamic(String a, String b) {

        int M = a.length();
        int N = b.length();
        int[][] opt = new int[M + 1][N + 1];

        for (int i = M - 1; i >= 0; --i) {
            for (int j = N - 1; j >= 0; --j) {
                if (a.charAt(i) == b.charAt(j)) {
                    opt[i][j] = opt[i + 1][j + 1] + 1;
                }
            }
        }

        LongestCommonSubsequence.printMatrix(opt);

        int max = 0, iMax = 0, jMax = 0;
        for (int i = 0; i < opt.length; ++i) {
            for (int j = 0; j < opt[i].length; ++j) {
                if (opt[i][j] > max) {
                    max = opt[i][j];
                    iMax = i;
                    jMax = j;
                }
            }
        }

        if (max == 0) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        for (int i = iMax, j = jMax; i < M && j < N; ) {
            if (a.charAt(i) == b.charAt(j)) {
                sb.append(a.charAt(i));
                ++i;
                ++j;
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        String a = StdIn.readLine();
        String b = StdIn.readLine();
        StdOut.println("bruteForce: " + bruteForce(a, b));
        StdOut.println("suffixArray: " + suffixArray(a, b));
        StdOut.println("dynamic: " + dynamic(a, b));
    }

}
