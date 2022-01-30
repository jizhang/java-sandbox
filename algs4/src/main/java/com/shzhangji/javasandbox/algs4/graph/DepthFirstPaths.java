package com.shzhangji.javasandbox.algs4.graph;

import com.shzhangji.javasandbox.algs4.basic.LinkedList;
import com.shzhangji.javasandbox.algs4.basic.Stack;
import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.StdOut;

public class DepthFirstPaths {

    private boolean[] marked;
    private int[] edgeTo;
    private final int s;

    public DepthFirstPaths(Graph G, int s) {
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        this.s = s;
        dfs(G, s);
    }

    private void dfs(Graph G, int v) {
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                edgeTo[w] = v;
                dfs(G, w);
            }
        }
    }

    public boolean hasPathTo(int v) {
        return marked[v];
    }

    public Iterable<Integer> pathTo(int v) {

        if (!hasPathTo(v)) {
            return null;
        }

        Stack<Integer> path = new LinkedList<Integer>();
        for (int x = v; x != s; x = edgeTo[x]) {
            path.push(x);
        }
        path.push(s);
        return path;

    }

    public static void main(String[] args) {

        Graph g = new Graph(6);

        g.addEdge(0, 5);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 4);
        g.addEdge(2, 3);
        g.addEdge(3, 4);
        g.addEdge(3, 5);

        for (int v : new DepthFirstPaths(g, 0).pathTo(4)) {
            StdOut.println(v);
        }

    }

}
