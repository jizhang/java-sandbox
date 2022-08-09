package com.shzhangji.algorithm.binarytree;

// https://leetcode.com/problems/maximum-depth-of-binary-tree/
public class MaxDepth {
  public static void main(String[] args) {
    var root = new TreeNode(3);
    root.left = new TreeNode(9);
    root.right = new TreeNode(20);
    root.right.left = new TreeNode(15);
    root.right.right = new TreeNode(7);
    System.out.println(new MaxDepth().maxDepth(root)); // 3
  }

  public int maxDepth(TreeNode root) {
    if (root == null) {
      return 0;
    }
    return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
  }
}
