package src.trees.traversal;

import src.trees.TreeNode;

import java.util.ArrayList;

public class RecursiveTreeTraversal implements TreeTraversal {
    @Override
    public ArrayList<Integer> preOrderTraversal(TreeNode root) {
        return preOrderTraversalRec(new ArrayList<>(), root);
    }

    @Override
    public ArrayList<Integer> inOrderTraversal(TreeNode root) {
        return inOrderTraversalRec(new ArrayList<>(), root);
    }

    @Override
    public ArrayList<Integer> postOrderTraversal(TreeNode root) {
        return postOrderTraversalRec(new ArrayList<>(), root);
    }

    private ArrayList<Integer> preOrderTraversalRec(ArrayList<Integer> list, TreeNode root) {
        if (root == null) return list;
        list.add(root.val);
        preOrderTraversalRec(list, root.left);
        preOrderTraversalRec(list, root.right);
        return list;
    }

    private ArrayList<Integer> inOrderTraversalRec(ArrayList<Integer> list, TreeNode root) {
        if (root == null) return list;
        inOrderTraversalRec(list, root.left);
        list.add(root.val);
        inOrderTraversalRec(list, root.right);
        return list;
    }

    private ArrayList<Integer> postOrderTraversalRec(ArrayList<Integer> list, TreeNode root) {
        if (root == null) return list;
        postOrderTraversalRec(list, root.left);
        postOrderTraversalRec(list, root.right);
        list.add(root.val);
        return list;
    }
}
