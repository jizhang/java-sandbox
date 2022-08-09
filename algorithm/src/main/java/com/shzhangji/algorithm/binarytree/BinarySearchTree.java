package com.shzhangji.algorithm.binarytree;

public class BinarySearchTree {
  public static void main(String[] args) {
    var bst = new BinarySearchTree();
    bst.insert(20);
    bst.insert(8);
    bst.insert(22);
    bst.insert(4);
    bst.insert(12);
    bst.insert(10);
    bst.insert(14);

    bst.print();
    System.out.println(bst.find(12));
    System.out.println(bst.find(13));

    bst.delete(14);
    bst.print();
  }

  TreeNode root;

  public void insert(int val) {
    if (root == null) {
      root = new TreeNode(val);
      return;
    }

    var node = root;
    while (node != null) {
      if (node.val > val) {
        if (node.left == null) {
          node.left = new TreeNode(val);
          return;
        }
        node = node.left;
      } else {
        if (node.right == null) {
          node.right = new TreeNode(val);
          return;
        }
        node = node.right;
      }
    }
  }

  public TreeNode find(int val) {
    var node = root;
    while (node != null) {
      if (node.val == val) {
        return node;
      }
      node = node.val > val ? node.left : node.right;
    }
    return null;
  }

  public void delete(int val) {
    var node = root;
    TreeNode parent = null;
    while (node != null) {
      if (node.val == val) {
        break;
      }
      parent = node;
      node = node.val > val ? node.left : node.right;
    }

    if (node == null) {
      return;
    }

    if (node.left != null && node.right != null) {
      var minNode = node.right;
      var minNodeParent = node;
      while (minNode.left != null) {
        minNodeParent = minNode;
        minNode = minNode.left;
      }

      node.val = minNode.val;
      node = minNode;
      parent = minNodeParent;
    }

    TreeNode child = node.left != null ? node.left : node.right;
    if (parent == null) {
      root = child;
    } else if (parent.left == node) {
      parent.left = child;
    } else {
      parent.right = child;
    }
  }

  public void print() {
    print0(root);
    System.out.println();
  }

  void print0(TreeNode node) {
    if (node == null) {
      return;
    }
    print0(node.left);
    System.out.print(node.val + " ");
    print0(node.right);
  }
}
