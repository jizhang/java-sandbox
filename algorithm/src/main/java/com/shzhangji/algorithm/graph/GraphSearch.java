package com.shzhangji.algorithm.graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class GraphSearch {
  public static void main(String[] args) {
    var graph = new Graph(8);
    graph.addEdge(0, 1);
    graph.addEdge(0, 3);
    graph.addEdge(1, 2);
    graph.addEdge(1, 4);
    graph.addEdge(3, 4);
    graph.addEdge(2, 5);
    graph.addEdge(4, 5);
    graph.addEdge(4, 6);
    graph.addEdge(5, 7);
    graph.addEdge(6, 7);
    bfs(graph, 0, 6);
  }

  static void bfs(Graph graph, int start, int target) {
    if (start == target) return;

    var visited = new boolean[graph.vertexCount];
    visited[start] = true;

    var prev = new int[graph.vertexCount];
    Arrays.fill(prev, -1);

    Queue<Integer> queue = new ArrayDeque<>();
    queue.add(start);

    while (!queue.isEmpty()) {
      int current = queue.remove();
      for (int next : graph.adjacencyList.get(current)) {
        if (visited[next]) continue;
        prev[next] = current;
        if (next == target) {
          print(prev, start, target);
          return;
        }
        visited[next] = true;
        queue.add(next);
      }
    }
  }

  static void print(int[] prev, int start, int target) {
    print0(prev, start, target);
    System.out.println();
  }

  static void print0(int[] prev, int start, int target) {
    if (start != target && prev[target] != -1) {
      print0(prev, start, prev[target]);
    }
    System.out.print(target + " ");
  }

  static class Graph {
    int vertexCount;
    List<List<Integer>> adjacencyList;

    Graph(int vertexCount) {
      this.vertexCount = vertexCount;
      adjacencyList = new ArrayList<>(vertexCount);
      for (int i = 0; i < vertexCount; ++i) {
        adjacencyList.add(new LinkedList<>());
      }
    }

    void addEdge(int source, int target) {
      adjacencyList.get(source).add(target);
      adjacencyList.get(target).add(source);
    }
  }
}
