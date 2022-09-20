package com.shzhangji.algorithm.backtracking;

public class EightQueens {
  static int[] result = new int[8];
  static int numResults = 0;

  public static void main(String[] args) {
    calculate(0);
  }

  static void calculate(int row) {
    if (row >= 8) {
      print();
      return;
    }

    for (int col = 0; col < 8; ++col) {
      if (check(row, col)) {
        result[row] = col;
        calculate(row + 1);
      }
    }
  }

  static boolean check(int row, int col) {
    int leftUp = col - 1;
    int rightUp = col + 1;

    for (int i = row - 1; i >= 0; --i) {
      if (result[i] == col) {
        return false;
      }
      if (leftUp >= 0 && result[i] == leftUp) {
        return false;
      }
      if (rightUp < 8 && result[i] == rightUp) {
        return false;
      }
      --leftUp;
      ++rightUp;
    }

    return true;
  }

  static void print() {
    System.out.println("No. " + (++numResults));
    for (int row = 0; row < 8; ++row) {
      for (int col = 0; col < 8; ++col) {
        if (result[row] == col) {
          System.out.print("Q ");
        } else {
          System.out.print("* ");
        }
      }
      System.out.println();
    }
    System.out.println();
  }
}
