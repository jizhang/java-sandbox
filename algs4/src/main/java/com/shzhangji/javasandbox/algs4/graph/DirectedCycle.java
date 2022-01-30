package com.shzhangji.javasandbox.algs4.graph;

import com.shzhangji.javasandbox.algs4.basic.LinkedList;
import com.shzhangji.javasandbox.algs4.basic.Stack;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.StdOut;

public class DirectedCycle {

    private boolean[] marked;
    private int[] edgeTo;
    private Stack<Integer> cycle;
    private boolean[] onStack;

    public DirectedCycle(Digraph G) {
        onStack = new boolean[G.V()];
        edgeTo = new int[G.V()];
        marked = new boolean[G.V()];
        for (int v = 0; v < G.V(); ++v) {
            if (!marked[v]) {
                dfs(G, v);
            }
        }
    }

    private void dfs(Digraph G, int v) {

        onStack[v] = true;
        marked[v] = true;

        for (int w : G.adj(v)) {

            if (hasCycle()) {
                return;
            } else if (!marked[w]) {
                edgeTo[w] = v;
                dfs(G, w);
            } else if (onStack[w]) {
                cycle = new LinkedList<>();
                for (int x = v; x != w; x = edgeTo[x]) {
                    cycle.push(x);
                }
                cycle.push(w);
                cycle.push(v);
            }

        }

        onStack[v] = false;
    }

    public boolean hasCycle() {
        return cycle != null;
    }

    public Iterable<Integer> cycle() {
        return cycle;
    }

    public static void main(String[] args) {

        Digraph g = new Digraph(13);
        g.addEdge(0, 1);
        g.addEdge(0, 5);
        g.addEdge(2, 0);
        g.addEdge(2, 3);
        g.addEdge(3, 2);
        g.addEdge(3, 5);
        g.addEdge(4, 2);
        g.addEdge(4, 3);
        g.addEdge(5, 4);
        g.addEdge(6, 0);
        g.addEdge(6, 4);
        g.addEdge(6, 8);
        g.addEdge(6, 9);
        g.addEdge(7, 6);
        g.addEdge(7, 9);
        g.addEdge(8, 6);
        g.addEdge(9, 10);
        g.addEdge(9, 11);
        g.addEdge(10, 12);
        g.addEdge(11, 4);
        g.addEdge(11, 12);
        g.addEdge(12, 9);

        for (int v : new DirectedCycle(g).cycle()) {
            StdOut.println(v);
        }

    }

}
