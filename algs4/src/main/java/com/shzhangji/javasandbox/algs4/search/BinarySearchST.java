package com.shzhangji.javasandbox.algs4.search;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * http://algs4.cs.princeton.edu/31elementary/
 */
public class BinarySearchST<K extends Comparable<K>, V> {

    private static final int INIT_CAPACITY = 2;;
    private K[] keys;
    private V[] values;
    private int N = 0;

    public BinarySearchST() {
        this(INIT_CAPACITY);
    }

    @SuppressWarnings("unchecked")
    public BinarySearchST(int capacity) {
        keys = (K[]) new Comparable[capacity];
        values = (V[]) new Object[capacity];
    }

    @SuppressWarnings("unchecked")
    private void resize(int capacity) {
        assert capacity >= N;
        K[] tempKeys = (K[]) new Comparable[capacity];
        V[] tempValues = (V[]) new Object[capacity];
        System.arraycopy(keys, 0, tempKeys, 0, N);
        System.arraycopy(values, 0, tempValues, 0, N);
        keys = tempKeys;
        values = tempValues;
    }

    public int size() {
        return N;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean contains(K key) {
        return get(key) != null;
    }

    public V get(K key) {
        if (isEmpty()) {
            return null;
        }

        int i = rank(key);

        if (i < N && keys[i].compareTo(key) == 0) {
            return values[i];
        }

        return null;
    }

    public int rank(K key) {
        // binary search: O(logn)
        int lo = 0, hi = N - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int cmp = key.compareTo(keys[mid]);
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

    public void put(K key, V value) {
        if (value == null) {
            delete(key);
            return;
        }

        int i = rank(key);

        if (i < N && keys[i].compareTo(key) == 0) {
            values[i] = value;
            return;
        }

        if (N == keys.length) {
            resize(2 * keys.length);
        }

        for (int j = N; j > i; --j) {
            keys[j] = keys[j - 1];
            values[j] = values[j - 1];
        }

        keys[i] = key;
        values[i] = value;
        ++N;

        assert check();
    }

    public void delete(K key) {
        if (isEmpty()) {
            return;
        }

        int i = rank(key);

        if (i == N || keys[i].compareTo(key) != 0) {
            return;
        }

        for (int j = i; j < N - 1; ++j) {
            keys[j] = keys[j + 1];
            values[j] = values[j + 1];
        }

        --N;
        keys[N] = null;
        values[N] = null;

        if (N > 0 && N == keys.length / 4) {
            resize(keys.length / 2);
        }

        assert check();
    }

    public K min() {
        if (isEmpty()) {
            return null;
        }
        return keys[0];
    }

    public K max() {
        if (isEmpty()) {
            return null;
        }
        return keys[N - 1];
    }

    public K select (int k) {
        if (k < 0 || k >= N) {
            return null;
        }
        return keys[k];
    }

    public Iterable<K> keys() {
        return keys(min(), max());
    }

    public Iterable<K> keys(K lo, K hi) {
        Queue<K> queue = new Queue<>();
        if (lo == null || hi == null || lo.compareTo(hi) > 0) {
            return queue;
        }
        for (int i = rank(lo); i <= rank(hi); ++i) {
            queue.enqueue(keys[i]);
        }
        return queue;
    }

    private boolean check() {
        return isSorted() && rankCheck();
    }

    private boolean isSorted() {
        for (int i = 1; i < size(); ++i) {
            if (keys[i].compareTo(keys[i - 1]) < 0) {
                return false;
            }
        }
        return true;
    }

    private boolean rankCheck() {
        for (int i = 0; i < size(); ++i) {
            if (i != rank(select(i))) {
                return false;
            }
        }
        for (int i = 0; i < size(); ++i) {
            if (keys[i].compareTo(select(rank(keys[i]))) != 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        BinarySearchST<String, Integer> st = new BinarySearchST<>();
        for (int i = 0; !StdIn.isEmpty(); ++i) {
            String key = StdIn.readString();
            st.put(key, i);
        }
        for (String s : st.keys()) {
            StdOut.println(s + " " + st.get(s));
        }
    }

}
