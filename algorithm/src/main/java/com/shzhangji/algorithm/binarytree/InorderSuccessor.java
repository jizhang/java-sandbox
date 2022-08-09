package com.shzhangji.algorithm.binarytree;

import java.util.Deque;
import java.util.LinkedList;

public class InorderSuccessor {
  public static void main(String[] args) {
    var bst = new BinarySearchTree();
    bst.insert(20);
    bst.insert(8);
    bst.insert(22);
    bst.insert(4);
    bst.insert(12);
    bst.insert(10);
    bst.insert(14);
    bst.printTree();
    System.out.println(findSuccessor(bst, 8));
    System.out.println(findSuccessorTraversal(bst, 8));
    System.out.println(findSuccessorStack(bst, 8));
  }

  static TreeNode findSuccessor(BinarySearchTree bst, int val) {
    var node = bst.find(val);
    if (node == null) {
      return null;
    }

    if (node.right != null) {
      var successor = node.right;
      while (successor.left != null) {
        successor = successor.left;
      }
      return successor;
    }

    TreeNode successor = null;
    node = bst.root;
    while (node != null) {
      if (node.val > val) {
        successor = node;
        node = node.left;
      } else if (node.val < val) {
        node = node.right;
      } else {
        break;
      }
    }

    return successor;
  }

  static TreeNode successor;

  static TreeNode findSuccessorTraversal(BinarySearchTree bst, int val) {
    successor = null;
    findSuccessorTraversal(val, bst.root);
    return successor;
  }

  static void findSuccessorTraversal(int val, TreeNode node) {
    if (node == null) return;
    findSuccessorTraversal(val, node.left);
    if (node.val > val && successor == null) {
      successor = node;
      return;
    }
    findSuccessorTraversal(val, node.right);
  }

  static TreeNode findSuccessorStack(BinarySearchTree bst, int val) {
    Deque<TreeNode> stack = new LinkedList<>();
    var node = bst.root;

    while (node != null || !stack.isEmpty()) {
      while (node != null) {
        stack.push(node);
        node = node.left;
      }

      node = stack.pop();
      if (node.val > val) {
        return node;
      }
      node = node.right;
    }

    return null;
  }
}
