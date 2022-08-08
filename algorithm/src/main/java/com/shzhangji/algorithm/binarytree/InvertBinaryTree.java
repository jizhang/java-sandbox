package com.shzhangji.algorithm.binarytree;

import java.util.LinkedList;
import java.util.Queue;

// https://leetcode.com/problems/invert-binary-tree/
public class InvertBinaryTree {
  public static void main(String[] args) {
    var root = new TreeNode(4);
    root.left = new TreeNode(2);
    root.right = new TreeNode(7);

    root.left.left = new TreeNode(1);
    root.left.right = new TreeNode(3);

    root.right.left = new TreeNode(6);
    root.right.right = new TreeNode(9);

    root = new InvertBinaryTree().invertTree(root);
    printTree(root);
  }

  public TreeNode invertTree(TreeNode root) {
    return root;
  }

  static void printTree(TreeNode root) {
    if (root == null) {
      return;
    }

    Queue<TreeNode> queue = new LinkedList<>();
    queue.add(root);
    while (!queue.isEmpty()) {
      var node = queue.remove();
      System.out.print(node.val + " ");
      if (node.left != null) {
        queue.add(node.left);
      }
      if (node.right != null) {
        queue.add(node.right);
      }
    }
  }
}
