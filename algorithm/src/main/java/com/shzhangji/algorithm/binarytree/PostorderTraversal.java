package com.shzhangji.algorithm.binarytree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

// https://leetcode.com/problems/binary-tree-postorder-traversal/
public class PostorderTraversal {
  public static void main(String[] args) {
    var obj = new PostorderTraversal();

    var root = new TreeNode(1);
    root.right = new TreeNode(2, new TreeNode(3), null);
    System.out.println(obj.postorderTraversal(root)); // [3, 2, 1]

    root = null;
    System.out.println(obj.postorderTraversal(root)); // []

    root = new TreeNode(1);
    System.out.println(obj.postorderTraversal(root)); // [1]
  }

  public List<Integer> postorderTraversal(TreeNode root) {
    var result = new LinkedList<Integer>();
    if (root == null) return result;

    var stack = new ArrayDeque<TreeNode>();
    stack.push(root);
    while (!stack.isEmpty()) {
      var node = stack.pop();
      result.addFirst(node.val); // Add to the beginning of result.
      if (node.left != null) stack.push(node.left); // Left first, in compared to preorder traversal.
      if (node.right != null) stack.push(node.right);
    }

    return result;
  }
}
