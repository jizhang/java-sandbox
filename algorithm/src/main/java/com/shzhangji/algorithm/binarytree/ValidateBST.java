package com.shzhangji.algorithm.binarytree;

// https://leetcode.com/problems/validate-binary-search-tree/
public class ValidateBST {
  public static void main(String[] args) {
    var root = new TreeNode(2);
    root.left = new TreeNode(1);
    root.right = new TreeNode(3);
    var obj = new ValidateBST();
    System.out.println(obj.isValidBST(root)); // true

    root = new TreeNode(5);
    root.left = new TreeNode(1);
    root.right = new TreeNode(4);
    root.right.left = new TreeNode(3);
    root.right.right = new TreeNode(6);
    System.out.println(obj.isValidBST(root)); // false

    root = new TreeNode(5);
    root.left = new TreeNode(4);
    root.right = new TreeNode(6);
    root.right.left = new TreeNode(3);
    root.right.right = new TreeNode(7);
    System.out.println(obj.isValidBST(root)); // false
  }

  public boolean isValidBST(TreeNode root) {
    // return validateInorder(root);
    return validateMinMax(root, Long.MIN_VALUE, Long.MAX_VALUE);
  }

  TreeNode prev;

  boolean validateInorder(TreeNode root) {
    if (root.left != null && !validateInorder(root.left)) {
      return false;
    }

    if (prev != null && prev.val >= root.val) {
      return false;
    }
    prev = root;

    if (root.right != null && !validateInorder(root.right)) {
      return false;
    }

    return true;
  }

  boolean validateMinMax(TreeNode root, long min, long max) {
    if (root == null) {
      return true;
    }

    if (root.val <= min || root.val >= max) {
      return false;
    }

    return validateMinMax(root.left, min, root.val) && validateMinMax(root.right, root.val, max);
  }
}
