package com.shzhangji.algorithm.backtracking;

// https://leetcode.com/problems/word-search/
public class WordSearch {
  public static void main(String[] args) {
    var obj = new WordSearch();
    var board = new char[][] {
      {'A', 'B', 'C', 'E'},
      {'S', 'F', 'C', 'S'},
      {'A', 'D', 'E', 'E'},
    };
    System.out.println(obj.exist(board, "ABCCED")); // true
    System.out.println(obj.exist(board, "SEE")); // true
    System.out.println(obj.exist(board, "ABCB")); // false
  }

  public boolean exist(char[][] board, String word) {
    for (int i = 0; i < board.length; ++i) {
      for (int j = 0; j < board[i].length; ++j) {
        if (recur(board, word, i, j, 0)) {
          return true;
        }
      }
    }
    return false;
  }

  boolean recur(char[][] board, String word, int i, int j, int k) {
    if (k == word.length()) return true;
    if (i < 0 || i == board.length || j < 0 || j == board[i].length) return false;
    if (board[i][j] != word.charAt(k)) return false;

    char t = board[i][j];
    board[i][j] = ' ';
    boolean found = recur(board, word, i, j + 1, k + 1)
        || recur(board, word, i, j - 1, k + 1)
        || recur(board, word, i + 1, j, k + 1)
        || recur(board, word, i - 1, j, k + 1);
    board[i][j] = t;
    return found;
  }
}
