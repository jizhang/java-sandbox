package com.shzhangji.algorithm.binarytree;

// https://leetcode.com/problems/same-tree/
public class SameTree {
  public static void main(String[] args) {
    var obj = new SameTree();
    var p = new TreeNode(1, new TreeNode(2), new TreeNode(3));
    System.out.println(obj.isSameTree(p, p)); // true

    p.right = null;
    var q = new TreeNode(1, null, new TreeNode(2));
    System.out.println(obj.isSameTree(p, q)); // false

    p.right = new TreeNode(1);
    q.left = new TreeNode(1);
    System.out.println(obj.isSameTree(p, q)); // false
  }

  public boolean isSameTree(TreeNode p, TreeNode q) {
    if (p == null && q == null) return true;
    if (p == null || q == null || (p.val != q.val)) return false;
    return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
  }
}
