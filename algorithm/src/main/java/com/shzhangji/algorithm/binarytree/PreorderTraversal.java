package com.shzhangji.algorithm.binarytree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/binary-tree-preorder-traversal/
public class PreorderTraversal {
  public static void main(String[] args) {
    var obj = new PreorderTraversal();

    var root = new TreeNode(1);
    root.right = new TreeNode(2, new TreeNode(3), null);
    System.out.println(obj.preorderTraversal(root)); // [1, 2, 3]

    root = null;
    System.out.println(obj.preorderTraversal(root)); // []
  }

  public List<Integer> preorderTraversal(TreeNode root) {
    return withIteration(root);
  }

  List<Integer> withRecursion(TreeNode root) {
    var result = new ArrayList<Integer>();
    recur(root, result);
    return result;
  }

  void recur(TreeNode root, List<Integer> result) {
    if (root == null) return;
    result.add(root.val);
    recur(root.left, result);
    recur(root.right, result);
  }

  List<Integer> withIteration(TreeNode root) {
    var result = new ArrayList<Integer>();
    if (root == null) return result;

    var stack = new ArrayDeque<TreeNode>();
    stack.push(root);
    while (!stack.isEmpty()) {
      var node = stack.pop();
      result.add(node.val);
      if (node.right != null) stack.push(node.right);
      if (node.left != null) stack.push(node.left);
    }

    return result;
  }
}
