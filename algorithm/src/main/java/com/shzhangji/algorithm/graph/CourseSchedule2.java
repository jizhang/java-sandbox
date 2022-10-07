package com.shzhangji.algorithm.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

// https://leetcode.com/problems/course-schedule-ii/
// https://leetcode.com/problems/course-schedule-ii/discuss/447774/Java-topological-sort-or-DFS-or-3ms
public class CourseSchedule2 {
  public static void main(String[] args) {
    var obj = new CourseSchedule2();
    System.out.println(Arrays.toString(obj.findOrder(2, new int[][] {{1, 0}}))); // [0, 1]
    System.out.println(Arrays.toString(obj.findOrder(4, new int[][] {{1, 0}, {2, 0}, {3, 1}, {3, 2}}))); // [0, 2, 1, 3]
    System.out.println(Arrays.toString(obj.findOrder(1, new int[][] {}))); // [0]
    System.out.println(Arrays.toString(obj.findOrder(2, new int[][] {{0, 1}, {1, 0}}))); // [1, 0]
  }

  public int[] findOrder(int numCourses, int[][] prerequisites) {
    return dfs(numCourses, prerequisites);
  }

  @SuppressWarnings("unchecked")
  int[] dfs(int numCourses, int[][] prerequisites) {
    var adj = new LinkedList[numCourses];
    for (int i = 0; i < adj.length; ++i) {
      adj[i] = new LinkedList<>();
    }
    for (var p : prerequisites) {
      adj[p[0]].add(p[1]);
    }

    // 0: not visited, 1: visited, 2: visiting
    var visited = new int[numCourses];
    var result = new ArrayList<Integer>();
    for (int i = 0; i < numCourses; ++i) {
      if (dfs(i, adj, visited, result)) {
        return new int[0];
      }
    }

    return result.stream().mapToInt(Integer::intValue).toArray();
  }

  // Returning true indicates that DAG has cycle.
  boolean dfs(int current, LinkedList<Integer>[] adj, int[] visited, List<Integer> result) {
    if (visited[current] == 2) return true;
    if (visited[current] == 1) return false;
    visited[current] = 2;
    for (int child : adj[current]) {
      if (dfs(child, adj, visited, result)) {
        return true;
      }
    }
    result.add(current);
    visited[current] = 1;
    return false;
  }
}
