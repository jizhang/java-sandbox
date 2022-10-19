package com.shzhangji.algorithm.graph;

import java.util.HashSet;

// https://leetcode.com/problems/valid-sudoku/
public class ValidSudoku {
  public static void main(String[] args) {
    var obj = new ValidSudoku();

    var board = new char[][]{
      new char[] {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
      new char[] {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
      new char[] {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
      new char[] {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
      new char[] {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
      new char[] {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
      new char[] {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
      new char[] {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
      new char[] {'.', '.', '.', '.', '8', '.', '.', '7', '9'},
    };
    System.out.println(obj.isValidSudoku(board)); // true

    board = new char[][]{
      new char[] {'8', '3', '.', '.', '7', '.', '.', '.', '.'},
      new char[] {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
      new char[] {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
      new char[] {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
      new char[] {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
      new char[] {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
      new char[] {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
      new char[] {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
      new char[] {'.', '.', '.', '.', '8', '.', '.', '7', '9'},
    };
    System.out.println(obj.isValidSudoku(board)); // false
  }

  public boolean isValidSudoku(char[][] board) {
    var set = new HashSet<String>();
    for (int i = 0; i < 9; ++i) {
      for (int j = 0; j < 9; ++j) {
        if (board[i][j] == '.') continue;
        if (!set.add("r" + i + board[i][j])) return false;
        if (!set.add("c" + j + board[i][j])) return false;
        if (!set.add("s" + (i / 3) + (j / 3) + board[i][j])) return false;
      }
    }
    return true;
  }
}
