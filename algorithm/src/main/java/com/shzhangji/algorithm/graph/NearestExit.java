package com.shzhangji.algorithm.graph;

import java.util.ArrayDeque;

// https://leetcode.com/problems/nearest-exit-from-entrance-in-maze/
public class NearestExit {
  public static void main(String[] args) {
    var obj = new NearestExit();

    var maze = new char[][] {
      {'+', '+', '.', '+'},
      {'.', '.', '.', '+'},
      {'+', '+', '+', '.'},
    };
    System.out.println(obj.nearestExit(maze, new int[] {1, 2})); // 1

    maze = new char[][] {
      {'+', '+', '+'},
      {'.', '.', '.'},
      {'+', '+', '+'},
    };
    System.out.println(obj.nearestExit(maze, new int[] {1, 0})); // 2

    maze = new char[][] {
      {'.', '+'},
    };
    System.out.println(obj.nearestExit(maze, new int[] {0, 0})); // -1

    maze = new char[][]{
      {'+', '.', '+', '+', '+', '+', '+'},
      {'+', '.', '+', '.', '.', '.', '+'},
      {'+', '.', '+', '.', '+', '.', '+'},
      {'+', '.', '.', '.', '.', '.', '+'},
      {'+', '+', '+', '+', '.', '+', '.'},
    };
    System.out.println(obj.nearestExit(maze, new int[] {0, 1})); // 7
  }

  public int nearestExit(char[][] maze, int[] entrance) {
    var dirs = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    var queue = new ArrayDeque<int[]>();
    queue.add(entrance);
    maze[entrance[0]][entrance[1]] = '+';
    int steps = 0;
    while (!queue.isEmpty()) {
      int size = queue.size();
      ++steps;
      while (size-- > 0) {
        var point = queue.remove();
        for (var dir : dirs) {
          int row = point[0] + dir[0], col = point[1] + dir[1];
          if (row < 0 || row == maze.length || col < 0 || col == maze[row].length || maze[row][col] == '+') continue;
          if (row == 0 || row == maze.length - 1 || col == 0 || col == maze[row].length - 1) return steps;
          queue.add(new int[] { row, col });
          maze[row][col] = '+';
        }
      }
    }
    return -1;
  }
}
