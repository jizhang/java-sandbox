package com.shzhangji.javasandbox.algorithm.sort;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TopologicalSort {
  public static void main(String[] args) {
    var graph = new Graph(6);
    graph.addEdge(1, 0);
    graph.addEdge(5, 2);
    graph.addEdge(5, 0);
    graph.addEdge(4, 0);
    graph.addEdge(4, 1);
    graph.addEdge(2, 3);
    graph.addEdge(3, 1);
    sortByKahn(graph); // 5 4 2 3 1 0
  }

  static void sortByKahn(Graph graph) {
    int[] inDegree = new int[graph.vertexCount];
    for (int i = 0; i < graph.vertexCount; ++i) {
      for (int target : graph.adjacencyList.get(i)) {
        ++inDegree[target];
      }
    }

    Queue<Integer> queue = new LinkedList<>();
    for (int i = 0; i < graph.vertexCount; ++i) {
      if (inDegree[i] == 0) {
        queue.add(i);
      }
    }

    while (!queue.isEmpty()) {
      int i = queue.remove();
      System.out.print(i + " ");
      for (int target : graph.adjacencyList.get(i)) {
        --inDegree[target];
        if (inDegree[target] == 0) {
          queue.add(target);
        }
      }
    }
  }

  static class Graph {
    int vertexCount;
    List<LinkedList<Integer>> adjacencyList;

    Graph(int vertexCount) {
      this.vertexCount = vertexCount;
      adjacencyList = new ArrayList<>(vertexCount);
      for (int i = 0; i < vertexCount; ++i) {
        adjacencyList.add(new LinkedList<>());
      }
    }

    void addEdge(int source, int target) {
      adjacencyList.get(source).add(target);
    }
  }
}
