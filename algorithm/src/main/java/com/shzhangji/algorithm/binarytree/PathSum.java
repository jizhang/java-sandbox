package com.shzhangji.algorithm.binarytree;

// https://leetcode.com/problems/path-sum/
public class PathSum {
  public static void main(String[] args) {
    var obj = new PathSum();

    var root = new TreeNode(5);
    root.left = new TreeNode(4);
    root.left.left = new TreeNode(11);
    root.left.left.left = new TreeNode(7);
    root.left.left.right = new TreeNode(2);
    root.right = new TreeNode(8);
    root.right.left = new TreeNode(13);
    root.right.right = new TreeNode(4);
    root.right.right.right = new TreeNode(1);
    System.out.println(obj.hasPathSum(root, 22)); // true

    root = new TreeNode(1, new TreeNode(2), new TreeNode(3));
    System.out.println(obj.hasPathSum(root, 5)); // false

    System.out.println(obj.hasPathSum(null, 0)); // false

    root = new TreeNode(-2, null, new TreeNode(-3));
    System.out.println(obj.hasPathSum(root, -5)); // true
  }

  boolean found;

  public boolean hasPathSum(TreeNode root, int targetSum) {
    found = false;
    dfs(root, targetSum);
    return found;
  }

  void dfs(TreeNode root, int targetSum) {
    if (root == null) return;
    if (root.left == null && root.right == null && root.val == targetSum) {
      found = true;
      return;
    }
    dfs(root.left, targetSum - root.val);
    dfs(root.right, targetSum - root.val);
  }
}
