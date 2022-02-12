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

    // https://leetcode.com/problems/course-schedule/
    System.out.println(canFinish(2, new int[][] { { 1, 0 } }));
    System.out.println(canFinish(2, new int[][] { { 1, 0 }, { 0, 1 } }));
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

    System.out.println();
  }

  static boolean canFinish(int numCourses, int[][] prerequisites) {
    var inDegree = new int[numCourses];
    for (var prerequisite : prerequisites) {
      ++inDegree[prerequisite[0]];
    }

    var queue = new LinkedList<Integer>();
    for (int i = 0; i < inDegree.length; ++i) {
      if (inDegree[i] == 0) queue.add(i);
    }

    int taken = 0;
    while (!queue.isEmpty()) {
      int i = queue.remove();
      ++taken;
      for (var prerequisite : prerequisites) {
        if (prerequisite[1] == i) {
          --inDegree[prerequisite[0]];
          if (inDegree[prerequisite[0]] == 0) {
            queue.add(prerequisite[0]);
          }
        }
      }
    }

    return taken == numCourses;
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
