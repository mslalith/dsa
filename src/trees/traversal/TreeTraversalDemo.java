package src.trees.traversal;

import src.trees.TreeNode;

import java.util.ArrayList;

public class TreeTraversalDemo {
    public static void main(String[] args) {
        TreeNode root = createTree();

        iterativeTraversal(root);
        System.out.println();
        recursiveTraversal(root);
    }

    static TreeNode createTree() {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(3);
        root.left = new TreeNode(2);
        root.left.right = new TreeNode(5);
        root.left.left = new TreeNode(4);
        root.left.left.right = new TreeNode(7);
        root.left.left.left = new TreeNode(6);
        root.left.left.left.right = new TreeNode(8);
        return root;
    }

    public static void iterativeTraversal(TreeNode root) {
        TreeTraversal treeTraversal = new IterativeTreeTraversal();
        System.out.println("Iterative Pre-Order: " + displayTraversalOutput(treeTraversal.preOrderTraversal(root))); // 1 2 4 6 8 7 5 3
        System.out.println("Iterative In-Order: " + displayTraversalOutput(treeTraversal.inOrderTraversal(root))); // 6 8 4 7 2 5 1 3
        System.out.println("Iterative Post-Order: " + displayTraversalOutput(treeTraversal.postOrderTraversal(root))); // 8 6 7 4 5 2 3 1
    }

    static void recursiveTraversal(TreeNode root) {
        TreeTraversal treeTraversal = new RecursiveTreeTraversal();
        System.out.println("Recursive Pre-Order: " + displayTraversalOutput(treeTraversal.preOrderTraversal(root))); // 1 2 4 6 8 7 5 3
        System.out.println("Recursive In-Order: " + displayTraversalOutput(treeTraversal.inOrderTraversal(root))); // 6 8 4 7 2 5 1 3
        System.out.println("Recursive Post-Order: " + displayTraversalOutput(treeTraversal.postOrderTraversal(root))); // 8 6 7 4 5 2 3 1
    }

    static String displayTraversalOutput(ArrayList<Integer> list) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            if (i != 0) stringBuilder.append(" ");
            stringBuilder.append(list.get(i));
        }
        return stringBuilder.toString();
    }
}
