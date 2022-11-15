package com.shzhangji.algorithm.string;

import java.util.List;

// https://leetcode.com/problems/substring-with-concatenation-of-all-words/
public class ConcatenatedSubstring {
  public static void main(String[] args) {
    var obj = new ConcatenatedSubstring();
    System.out.println(obj.findSubstring("barfoothefoobarman", new String[] {"foo","bar"})); // [0,9]
    System.out.println(obj.findSubstring("wordgoodgoodgoodbestword", new String[] {"word","good","best","word"})); // []
    System.out.println(obj.findSubstring("barfoofoobarthefoobarman", new String[] {"bar","foo","the"})); // [6,9,12]
  }

  public List<Integer> findSubstring(String s, String[] words) {
    return List.of();
  }
}
