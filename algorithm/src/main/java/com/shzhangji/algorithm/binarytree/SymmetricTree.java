package com.shzhangji.algorithm.binarytree;

import java.util.Stack;

// https://leetcode.com/problems/symmetric-tree/
public class SymmetricTree {
  public static void main(String[] args) {
    var obj = new SymmetricTree();
    var root = new TreeNode(1);
    root.left = new TreeNode(2, new TreeNode(3), new TreeNode(4));
    root.right = new TreeNode(2, new TreeNode(4), new TreeNode(3));
    System.out.println(obj.isSymmetric(root)); // true

    root.left = new TreeNode(2, null, new TreeNode(3));
    root.right = new TreeNode(2, null, new TreeNode(3));
    System.out.println(obj.isSymmetric(root)); // false
  }

  // Follow up: Could you solve it both recursively and iteratively?
  public boolean isSymmetric(TreeNode root) {
    return useIteration(root);
  }

  boolean useRecursion(TreeNode root) {
    return recur(root.left, root.right);
  }

  boolean recur(TreeNode left, TreeNode right) {
    if (left == null && right == null) return true;
    if (left == null || right == null) return false;
    if (left.val != right.val) return false;
    return recur(left.left, right.right) && recur(left.right, right.left);
  }

  boolean useIteration(TreeNode root) {
    var stack = new Stack<TreeNode>(); // ArrayDeque does not allow NULL values.
    stack.push(root.left);
    stack.push(root.right);

    // Mimic inorder traversal.
    while (!stack.isEmpty()) {
      var t1 = stack.pop();
      var t2 = stack.pop();
      if (t1 == null && t2 == null) continue;
      if (t1 == null || t2 == null) return false;
      if (t1.val != t2.val) return false;
      stack.push(t1.left);
      stack.push(t2.right);
      stack.push(t1.right);
      stack.push(t2.left);
    }

    return true;
  }
}
