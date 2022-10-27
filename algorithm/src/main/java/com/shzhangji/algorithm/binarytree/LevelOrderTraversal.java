package com.shzhangji.algorithm.binarytree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

// https://leetcode.com/problems/binary-tree-level-order-traversal/
public class LevelOrderTraversal {
  public static void main(String[] args) {
    var obj = new LevelOrderTraversal();

    var root = new TreeNode(3, new TreeNode(9), null);
    root.right = new TreeNode(20, new TreeNode(15), new TreeNode(7));
    System.out.println(obj.levelOrder(root)); // [[3],[9,20],[15,7]]

    root = new TreeNode(1);
    System.out.println(obj.levelOrder(root)); // [[1]]

    root = null;
    System.out.println(obj.levelOrder(root)); // []
  }

  public List<List<Integer>> levelOrder(TreeNode root) {
    return dfs(root);
  }

  List<List<Integer>> bfs(TreeNode root) {
    List<List<Integer>> result = new ArrayList<>();
    if (root == null) return result;

    Queue<TreeNode> queue = new ArrayDeque<>();
    queue.add(root);

    while (!queue.isEmpty()) {
      var level = new ArrayList<Integer>();
      int size = queue.size();
      for (int i = 0; i < size; ++i) {
        var node = queue.remove();
        level.add(node.val);
        if (node.left != null) queue.add(node.left);
        if (node.right != null) queue.add(node.right);
      }
      result.add(level);
    }

    return result;
  }

  List<List<Integer>> dfs(TreeNode root) {
    var result = new ArrayList<List<Integer>>();
    dfs(root, 1, result);
    return result;
  }

  void dfs(TreeNode node, int level, List<List<Integer>> result) {
    if (node == null) return;
    if (level > result.size()) result.add(new ArrayList<>());
    result.get(level - 1).add(node.val);
    dfs(node.left, level + 1, result);
    dfs(node.right, level + 1, result);
  }
}
