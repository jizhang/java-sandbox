package com.shzhangji.algorithm.dp;

import java.util.Arrays;
import java.util.Comparator;

// https://leetcode.com/problems/russian-doll-envelopes/
public class RussianDollEnvelopes {
  public static void main(String[] args) {
    var obj = new RussianDollEnvelopes();
    System.out.println(obj.maxEnvelopes(new int[][] {
      new int[] { 5, 4 },
      new int[] { 6, 4 },
      new int[] { 6, 7 },
      new int[] { 2, 3 }
    })); // 3
    System.out.println(obj.maxEnvelopes(new int[][] {
      new int[] { 1, 1 },
      new int[] { 1, 1 },
      new int[] { 1, 1 }
    })); // 1
    System.out.println(obj.maxEnvelopes(new int[][] {
      new int[] { 46, 89 },
      new int[] { 50, 53 },
      new int[] { 52, 68 },
      new int[] { 72, 45 },
      new int[] { 77, 81 }
    })); // 3
  }

  public int maxEnvelopes(int[][] envelopes) {
    return bottomUpBinarySearch(envelopes);
  }

  // https://leetcode.com/problems/russian-doll-envelopes/discuss/82763/Java-NLogN-Solution-with-Explanation
  int bottomUpBinarySearch(int[][] envelopes) {
    Arrays.sort(envelopes, (a, b) -> { // ORDER BY width ASC, height DESC
      if (a[0] != b[0]) return a[0] < b[0] ? -1 : 1;
      return Integer.compare(b[1], a[1]);
    });

    var dp = new int[envelopes.length];
    int len = 0;

    for (var e : envelopes) {
      int pos = Arrays.binarySearch(dp, 0, len, e[1]);
      if (pos < 0) pos = -(pos + 1);
      dp[pos] = e[1];
      if (pos == len) ++len;
    }

    return len;
  }

  // LIS
  int bottomUpTLE(int[][] envelopes) {
    Arrays.sort(envelopes, Comparator.comparingInt(t -> t[0]));
    var dp = new int[envelopes.length];
    Arrays.fill(dp, 1);

    for (int i = 1; i < envelopes.length; ++i) {
      for (int j = 0; j < i; ++j) {
        if (envelopes[i][0] > envelopes[j][0] && envelopes[i][1] > envelopes[j][1]) {
          dp[i] = Math.max(dp[i], dp[j] + 1);
        }
      }
    }

    return Arrays.stream(dp).max().orElseThrow();
  }
}
