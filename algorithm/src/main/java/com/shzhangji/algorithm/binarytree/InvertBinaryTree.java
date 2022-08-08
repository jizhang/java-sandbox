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
    printLevelOrder(root);
  }

  public TreeNode invertTree(TreeNode root) {
    if (root == null) {
      return null;
    }

    var left = invertTree(root.left);
    root.left = invertTree(root.right);
    root.right = left;

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

    System.out.println();
  }

  static int getHeight(TreeNode root) {
    if (root == null) {
      return 0;
    }
    return 1 + Math.max(getHeight(root.left), getHeight(root.right));
  }

  static void printLevelOrder(TreeNode root) {
    for (int i = 1; i <= getHeight(root); ++i) {
      printCurrentLevel(root, i);
    }
    System.out.println();
  }

  static void printCurrentLevel(TreeNode root, int level) {
    if (root == null) {
      return;
    }

    if (level == 1) {
      System.out.print(root.val + " ");
    } else {
      printCurrentLevel(root.left, level - 1);
      printCurrentLevel(root.right, level - 1);
    }
  }
}
