package com.shzhangji.algorithm.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

// https://leetcode.com/problems/n-queens/
public class NQueens {
  public static void main(String[] args) {
    var obj = new NQueens();
    System.out.println(obj.solveNQueens(4));
    // [[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]

    System.out.println(obj.solveNQueens(1)); // [["Q"]]
  }

  List<List<String>> result;

  public List<List<String>> solveNQueens(int n) {
    result = new ArrayList<>();
    calculate(new int[n], 0);
    return result;
  }

  void calculate(int[] board, int row) {
    if (row == board.length) {
      result.add(make(board));
      return;
    }

    for (int col = 0; col < board.length; ++col) {
      if (check(board, row, col)) {
        board[row] = col;
        calculate(board, row + 1);
      }
    }
  }

  boolean check(int[] board, int row, int col) {
    int left = col - 1, right = col + 1;
    --row;
    while (row >= 0) {
      if (board[row] == col || board[row] == left || board[row] == right) return false;
      --row;
      --left;
      ++right;
    }
    return true;
  }

  List<String> make(int[] board) {
    return Arrays.stream(board)
      .mapToObj(col -> {
        var arr = new char[board.length];
        Arrays.fill(arr, '.');
        arr[col] = 'Q';
        return new String(arr);
      })
      .collect(Collectors.toList());
  }
}
