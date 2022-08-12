package com.shzhangji.algorithm.graph;

import java.util.LinkedList;

// https://leetcode.com/problems/course-schedule/
public class CourseSchedule {
  public static void main(String[] args) {
    System.out.println(canFinish(2, new int[][] { { 1, 0 } }));
    System.out.println(canFinish(2, new int[][] { { 1, 0 }, { 0, 1 } }));
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
}
