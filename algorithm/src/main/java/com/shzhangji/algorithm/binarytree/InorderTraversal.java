package com.shzhangji.algorithm.binarytree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/binary-tree-inorder-traversal/
public class InorderTraversal {
  public static void main(String[] args) {
    var obj = new InorderTraversal();

    var root = new TreeNode(1);
    root.right = new TreeNode(2, new TreeNode(3), null);
    System.out.println(obj.inorderTraversal(root)); // [1, 3, 2]

    root = null;
    System.out.println(obj.inorderTraversal(root)); // []

    root = new TreeNode(1);
    System.out.println(obj.inorderTraversal(root)); // [1]
  }

  public List<Integer> inorderTraversal(TreeNode root) {
    var result = new ArrayList<Integer>();
    var stack = new ArrayDeque<TreeNode>();
    var node = root;
    while (node != null || !stack.isEmpty()) {
      while (node != null) {
        stack.push(node);
        node = node.left;
      }

      node = stack.pop();
      result.add(node.val);
      node = node.right;
    }

    return result;
  }
}
