package com.shzhangji.javasandbox.algs4.search;

import com.shzhangji.javasandbox.algs4.basic.LinkedList;
import com.shzhangji.javasandbox.algs4.basic.Queue;
import com.shzhangji.javasandbox.algs4.basic.Stack;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class BST<K extends Comparable<? super K>, V> {

    class Node {
        K key;
        V value;
        Node left, right;
        int N;
        Node(K key, V value, int N) {
            this.key = key;
            this.value = value;
            this.N = N;
        }
    }

    private Node root;

    public void clear() {
        root = null;
    }

    public int size() {
        return size(root);
    }

    private int size(Node x) {
        return x == null ? 0 : x.N;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public V get(K key) {
        return get(root, key);
    }

    private V get(Node x, K key) {

        if (x == null) {
            return null;
        }

        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            return get(x.left, key);
        } else if (cmp > 0) {
            return get(x.right, key);
        } else {
            return x.value;
        }
    }

    public void put(K key, V value) {
        root = put(root, key, value);
    }

    private Node put(Node x, K key, V value) {

        if (x == null) {
            return new Node(key, value, 1);
        }

        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            x.left = put(x.left, key, value);
        } else if (cmp > 0) {
            x.right = put(x.right, key, value);
        } else {
            x.value = value;
        }

        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public K min() {
        return min(root);
    }

    private K min(Node x) {
        return x.left == null ? x.key : min(x.left);
    }

    public K max() {
        return max(root);
    }

    private K max(Node x) {
        return x.right == null ? x.key : max(x.right);
    }

    public Iterable<K> keys() {
        return keys(min(), max());
    }

    public Iterable<K> keys(K lo, K hi) {
        Queue<K> queue = new LinkedList<>();
        keys(root, queue, lo, hi);
        return queue;
    }

    private void keys(Node x, Queue<K> queue, K lo, K hi) {

        if (x == null) {
            return;
        }

        int cmplo = lo.compareTo(x.key);
        int cmphi = hi.compareTo(x.key);

        if (cmplo < 0) {
            keys(x.left, queue, lo, hi);
        }
        if (cmplo <= 0 && cmphi >= 0) {
            queue.enqueue(x.key);
        }
        if (cmphi > 0) {
            keys(x.right, queue, lo, hi);
        }
    }

    public Iterable<K> preOrder() {
        Queue<K> queue = new LinkedList<>();
        preOrder(root, queue);
        return queue;
    }

    private void preOrder(Node x, Queue<K> queue) {
        if (x != null) {
            queue.enqueue(x.key);
            preOrder(x.left, queue);
            preOrder(x.right, queue);
        }
    }

    /**
     * https://en.wikipedia.org/wiki/Tree_traversal#Post-order_2
     */
    public Iterable<K> postOrder() {

        Queue<K> queue = new LinkedList<>();
        Stack<Node> parentStack = new LinkedList<>();
        Node node = root, lastNodeVisited = null;

        while (!parentStack.isEmpty() || node != null) {
            if (node != null) {
                parentStack.push(node);
                node = node.left;
            } else {
                Node peekNode = parentStack.peek();
                if (peekNode.right != null && lastNodeVisited != peekNode.right) {
                    node = peekNode.right;
                } else {
                    queue.enqueue(peekNode.key);
                    lastNodeVisited = parentStack.pop();
                }
            }
        }

        return queue;
    }

    public Iterable<K> levelOrder() {

        Queue<K> keys = new LinkedList<>();
        Queue<Node> nodes = new LinkedList<>();

        nodes.enqueue(root);
        while (!nodes.isEmpty()) {
            Node node = nodes.dequeue();
            keys.enqueue(node.key);
            if (node.left != null) {
                nodes.enqueue(node.left);
            }
            if (node.right != null) {
                nodes.enqueue(node.right);
            }
        }

        return keys;
    }

    public static void main(String[] args) {

        BST<String, Integer> bst = new BST<>();

        for (int i = 0; !StdIn.isEmpty(); ++i) {
            bst.put(StdIn.readString(), i);
        }

        for (String s : bst.keys()) {
            StdOut.println(s + " " + bst.get(s));
        }

    }

}
