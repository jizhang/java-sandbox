package com.shzhangji.javasandbox.algs4.context;

import java.util.Arrays;

public class SuffixArray {

    private Suffix[] suffixes;

    public SuffixArray(String text) {
        int N = text.length();
        this.suffixes = new Suffix[N];
        for (int i = 0; i < N; ++i) {
            suffixes[i] = new Suffix(text, i);
        }
        Arrays.sort(suffixes);
    }

    private class Suffix implements Comparable<Suffix> {

        private final String text;
        private final int index;

        private Suffix(String text, int index) {
            this.text = text;
            this.index = index;
        }

        private int length() {
            return text.length() - index;
        }

        private char charAt(int i) {
            return text.charAt(index + i);
        }

        @Override
        public int compareTo(Suffix that) {
            if (this == that) {
                return 0;
            }
            int N = Math.min(this.length(), that.length());
            for (int i = 0; i < N; ++i) {
                if (this.charAt(i) < that.charAt(i)) {
                    return -1;
                }
                if (this.charAt(i) > that.charAt(i)) {
                    return 1;
                }
            }
            return this.length() - that.length();
        }

        @Override
        public String toString() {
            return text.substring(index);
        }

    }

    public int length() {
        return suffixes.length;
    }

    public int index(int i) {
        if (i < 0 || i >= suffixes.length) {
            throw new IndexOutOfBoundsException();
        }
        return suffixes[i].index;
    }

    public int lcp(int i) {
        if (i < 1 || i >= suffixes.length) {
            throw new IndexOutOfBoundsException();
        }
        return lcp(suffixes[i], suffixes[i - 1]);
    }

    private int lcp(Suffix s, Suffix t) {
        int N = Math.min(s.length(), t.length());
        for (int i = 0; i < N; ++i) {
            if (s.charAt(i) != t.charAt(i)) {
                return i;
            }
        }
        return N;
    }

    public String select(int i) {
        if (i < 0 || i >= suffixes.length) {
            throw new IndexOutOfBoundsException();
        }
        return suffixes[i].toString();
    }

    public int rank(String query) {
        int lo = 0, hi = suffixes.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int cmp = compare(query, suffixes[mid]);
            if (cmp < 0) {
                hi = mid - 1;
            } else if (cmp > 0) {
                lo = mid + 1;
            } else {
                return mid;
            }
        }
        return lo;
    }

    private int compare(String query, Suffix suffix) {
        int N = Math.min(query.length(),  suffix.length());
        for (int i = 0; i < N; ++i) {
            if (query.charAt(i) < suffix.charAt(i)) {
                return -1;
            }
            if (query.charAt(i) > suffix.charAt(i)) {
                return 1;
            }
        }
        return query.length() - suffix.length();
    }

}
