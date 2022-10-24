package src.trees.traversal;

import src.trees.TreeNode;

import java.util.ArrayList;

public interface TreeTraversal {
    ArrayList<Integer> preOrderTraversal(TreeNode root);
    ArrayList<Integer> inOrderTraversal(TreeNode root);
    ArrayList<Integer> postOrderTraversal(TreeNode root);
}
