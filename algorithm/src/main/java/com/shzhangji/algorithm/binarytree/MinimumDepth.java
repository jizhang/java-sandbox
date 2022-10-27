package com.shzhangji.algorithm.binarytree;

import java.util.ArrayDeque;
import java.util.Queue;

// https://leetcode.com/problems/minimum-depth-of-binary-tree/
public class MinimumDepth {
  public static void main(String[] args) {
    var obj = new MinimumDepth();

    var root = new TreeNode(3, new TreeNode(9), null);
    root.right = new TreeNode(20, new TreeNode(15), new TreeNode(7));
    System.out.println(obj.minDepth(root)); // 2

    root = new TreeNode(2);
    root.right = new TreeNode(3);
    root.right.right = new TreeNode(4);
    root.right.right.right = new TreeNode(5);
    root.right.right.right.right = new TreeNode(6);
    System.out.println(obj.minDepth(root)); // 5
  }

  public int minDepth(TreeNode root) {
    return withBFS(root);
  }

  int withRecursion(TreeNode root) {
    if (root == null) return 0;
    int left = minDepth(root.left);
    int right = minDepth(root.right);
    return 1 + ((left > 0 && right > 0) ? Math.min(left, right) : left + right); // Left or right is zero.
  }

  int withBFS(TreeNode root) {
    if (root == null) return 0;

    Queue<TreeNode> queue = new ArrayDeque<>();
    queue.add(root);

    int depth = 1;
    while (!queue.isEmpty()) {
      int size = queue.size();
      for (int i = 0; i < size; ++i) {
        var node = queue.remove();
        if (node.left == null && node.right == null) return depth;
        if (node.left != null) queue.add(node.left);
        if (node.right != null) queue.add(node.right);
      }
      ++depth;
    }

    return depth;
  }
}
