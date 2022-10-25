package com.shzhangji.algorithm.slidingwindow;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

// https://leetcode.com/problems/repeated-dna-sequences/
public class RepeatedDNASequences {
  public static void main(String[] args) {
    var obj = new RepeatedDNASequences();

    System.out.println(obj.findRepeatedDnaSequences("AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"));
    // ["AAAAACCCCC","CCCCCAAAAA"]

    System.out.println(obj.findRepeatedDnaSequences("AAAAAAAAAAAAA"));
    // ["AAAAAAAAAA"]

    System.out.println(obj.findRepeatedDnaSequences("AAAAAAAAAAA"));
    // ["AAAAAAAAAA"]
  }

  public List<String> findRepeatedDnaSequences(String s) {
    return useSet(s);
  }

  // https://leetcode.com/problems/repeated-dna-sequences/discuss/53971/Easy-understand-and-straightforward-java-solution
  List<String> useSet(String s) {
    if (s.length() <= 10) return List.of();

    var set = new HashSet<String>();
    var result = new HashSet<String>();

    for (int i = 0; i <= s.length() - 10; ++i) {
      var sub = s.substring(i, i + 10);
      if (!set.add(sub)) {
        result.add(sub);
      }
    }

    return new ArrayList<>(result);
  }
}
