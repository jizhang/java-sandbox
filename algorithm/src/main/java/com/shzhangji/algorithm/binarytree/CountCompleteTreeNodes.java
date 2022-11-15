package com.shzhangji.algorithm.binarytree;

// https://leetcode.com/problems/count-complete-tree-nodes/
// Design an algorithm that runs in less than O(n) time complexity.
public class CountCompleteTreeNodes {
  public static void main(String[] args) {
    var obj = new CountCompleteTreeNodes();

    var root = new TreeNode(1);
    root.left = new TreeNode(2, new TreeNode(4), new TreeNode(5));
    root.right = new TreeNode(3, new TreeNode(6), null);
    System.out.println(obj.countNodes(root)); // 6

    System.out.println(obj.countNodes(null)); // 0

    root = new TreeNode(1);
    System.out.println(obj.countNodes(root)); // 1
  }

  public int countNodes(TreeNode root) {
    return useCompleteness(root);
  }

  // O(n)
  int bruteForce(TreeNode root) {
    if (root == null) return 0;
    return 1 + bruteForce(root.left) + bruteForce(root.right);
  }

  // O(log(n)^2)
  // https://leetcode.com/problems/count-complete-tree-nodes/discuss/61999/Java-concise-solution.
  int useCompleteness(TreeNode root) {
    if (root == null) return 0;
    int leftHeight = getHeight(root.left);
    int rightHeight = getHeight(root.right);
    if (leftHeight == rightHeight) { // Left subtree is full.
      int leftNodes = (int) Math.pow(2, leftHeight) - 1;
      int rightNodes = useCompleteness(root.right);
      return 1 + leftNodes + rightNodes;
    }
    return useCompleteness(root.left) + (1 << rightHeight);
  }

  int getHeight(TreeNode root) {
    int height = 0;
    while (root != null) {
      ++height;
      root = root.left;
    }
    return height;
  }
}
