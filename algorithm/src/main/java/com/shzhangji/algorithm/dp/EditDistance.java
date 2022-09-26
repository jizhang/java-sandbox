package com.shzhangji.algorithm.dp;

// https://leetcode.com/problems/edit-distance/
public class EditDistance {
  public static void main(String[] args) {
    var obj = new EditDistance();
    System.out.println(obj.minDistance("horse", "ros")); // 3
    System.out.println(obj.minDistance("intention", "execution")); // 5
  }

  public int minDistance(String word1, String word2) {
    return topDown(word1, word2);
  }

  int topDown(String word1, String word2) {
    var memo = new Integer[word1.length()][word2.length()];
    return topDown(word1, word2, 0, 0, memo);
  }

  int topDown(String word1, String word2, int i, int j, Integer[][] memo) {
    if (i == word1.length()) return word2.length() - j;
    if (j == word2.length()) return word1.length() - i;
    if (memo[i][j] != null) return memo[i][j];

    if (word1.charAt(i) == word2.charAt(j)) {
      memo[i][j] = topDown(word1, word2, i + 1, j + 1, memo);
    } else {
      var t = Math.min(topDown(word1, word2, i + 1, j, memo), topDown(word1, word2, i, j + 1, memo));
      memo[i][j] = Math.min(t, topDown(word1, word2, i + 1, j + 1, memo)) + 1;
    }
    return memo[i][j];
  }

  int bottomUp(String word1, String word2) {
    var states = new int[word1.length() + 1][word2.length() + 1]; // With sentinel
    for (int i = 1; i <= word1.length(); ++i) {
      states[i][0] = states[i - 1][0] + 1;
    }
    for (int j = 1; j <= word2.length(); ++j) {
      states[0][j] = states[0][j - 1] + 1;
    }

    for (int i = 1; i <= word1.length(); ++i) {
      for (int j = 1; j <= word2.length(); ++j) {
        if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
          states[i][j] = states[i - 1][j - 1];
        } else {
          states[i][j] = Math.min(Math.min(states[i - 1][j], states[i][j - 1]), states[i - 1][j - 1]) + 1;
        }
      }
    }

    return states[word1.length()][word2.length()];
  }
}
