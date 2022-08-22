package com.shzhangji.algorithm.string;

// https://leetcode.com/problems/implement-trie-prefix-tree/
public class Trie {
  public static void main(String[] args) {
    var obj = new Trie();
    obj.insert("apple");
    System.out.println(obj.search("apple"));
    System.out.println(obj.search("app"));
    System.out.println(obj.startsWith("app"));
    obj.insert("app");
    System.out.println(obj.search("app"));
  }

  TrieNode root;

  public Trie() {
    root = new TrieNode('/');
  }

  public void insert(String word) {
    var node = root;
    for (int i = 0; i < word.length(); ++i) {
      int j = word.charAt(i) - 'a';
      if (node.children[j] == null) {
        node.children[j] = new TrieNode(word.charAt(i));
      }
      node = node.children[j];
    }
    node.isEndingChar = true;
  }

  public boolean search(String word) {
    var node = root;
    for (int i = 0; i < word.length(); ++i) {
      int j = word.charAt(i) - 'a';
      if (node.children[j] == null) return false;
      node = node.children[j];
    }
    return node.isEndingChar;
  }

  public boolean startsWith(String prefix) {
    var node = root;
    for (int i = 0; i < prefix.length(); ++i) {
      int j = prefix.charAt(i) - 'a';
      if (node.children[j] == null) return false;
      node = node.children[j];
    }
    return true;
  }

  static class TrieNode {
    char data;
    TrieNode[] children;
    boolean isEndingChar;
    TrieNode(char data) {
      this.data = data;
      children = new TrieNode[26];
      isEndingChar = false;
    }
  }
}
