package com.shzhangji.algorithm.binarytree;

import lombok.ToString;

@ToString
public class TreeNode {
  int val;
  @ToString.Exclude
  TreeNode left, right;

  TreeNode() {}

  TreeNode(int val) {
    this.val = val;
  }

  TreeNode(int val, TreeNode left, TreeNode right) {
    this.val = val;
    this.left = left;
    this.right = right;
  }
}
