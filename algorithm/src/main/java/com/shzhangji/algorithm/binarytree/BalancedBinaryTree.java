package com.shzhangji.algorithm.binarytree;

// https://leetcode.com/problems/balanced-binary-tree/
public class BalancedBinaryTree {
  public static void main(String[] args) {
    var obj = new BalancedBinaryTree();

    var root = new TreeNode(3);
    root.left = new TreeNode(9);
    root.right = new TreeNode(20, new TreeNode(15), new TreeNode(7));
    System.out.println(obj.isBalanced(root)); // true

    root = new TreeNode(1, null, new TreeNode(2));
    root.left = new TreeNode(2, null, new TreeNode(3));
    root.left.left = new TreeNode(3, new TreeNode(4), new TreeNode(4));
    System.out.println(obj.isBalanced(root)); // false

    root = null;
    System.out.println(obj.isBalanced(root)); // true
  }

  public boolean isBalanced(TreeNode root) {
    return height(root) != -1;
  }

  // Return -1 if tree is not balanced.
  int height(TreeNode node) {
    if (node == null) return 0;
    int left = height(node.left);
    int right = height(node.right);
    if (left == -1 || right == -1 || Math.abs(left - right) > 1) return -1;
    return 1 + Math.max(left, right);
  }
}
