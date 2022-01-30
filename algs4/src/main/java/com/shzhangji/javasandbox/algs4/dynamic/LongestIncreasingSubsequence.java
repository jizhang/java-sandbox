package com.shzhangji.javasandbox.algs4.dynamic;

import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class LongestIncreasingSubsequence {

    /**
     * https://youtu.be/4fQJGoeW5VE
     * \begin{equation}
     * L(n) = \left\{
     *   \begin{array}{ll}
     *     1 & \mbox{if } n = 1, \\
     *     max^{n - 1}_{i = 1}(L(n - i)) + 1 & \mbox{where } s_n \geq s_i
     *   \end{array}
     * \right.
     * \end{equation}
     */
    public static int[] calcN2(int[] a) {

        int[] L = new int[a.length];
        L[0] = 1;

        for (int i = 1; i < a.length; ++i) {
            L[i] = 1;
            for (int j = 0; j < i; ++j) {
                if (a[j] < a[i] && L[j] + 1 > L[i]) {
                    L[i] = L[j] + 1;
                }
            }
        }

        return makeResult(a, L);
    }

    private static int[] makeResult(int[] a, int[] L) {

        int max = 0, maxIndex = -1;
        for (int i = 0; i < L.length; ++i) {
            if (L[i] > max) {
                max = L[i];
                maxIndex = i;
            }
        }

        int[] result = new int[max];
        for (int i = maxIndex; i >= 0; --i) {
            if (L[i] == max) {
                result[--max] = a[i];
            }
        }

        return result;
    }

    /**
     * http://blog.csdn.net/linyunzju/article/details/7727254
     */
    public static int[] calcNLogN(int[] a) {

        int[] maxv = new int[a.length + 1];
        int[] L = new int[a.length];
        int len = 1;
        maxv[1] = a[0];
        L[0] = 1;

        for (int i = 1; i < a.length; ++i) {

            int u = 1, v = len;
            while (u <= v) {
                int mid = (u + v) / 2;
                if (maxv[mid] < a[i]) {
                    u = mid + 1;
                } else {
                    v = mid - 1;
                }
            }

            len = Math.max(len, v + 1);
            maxv[v + 1] = a[i];
            L[i] = v + 1;
        }

        return makeResult(a, L);
    }

    public static void main(String[] args) {
        int[] a = new int[] {1, -1, 2, -3, 4, -5, 6, -7};
        StdOut.println(Arrays.toString(calcN2(a)));
        StdOut.println(Arrays.toString(calcNLogN(a)));
    }

}
