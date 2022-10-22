package trees;

import java.util.ArrayList;
import java.util.Stack;

public class TreeTraversal {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(3);
        root.left = new TreeNode(2);
        root.left.right = new TreeNode(5);
        root.left.left = new TreeNode(4);
        root.left.left.right = new TreeNode(7);
        root.left.left.left = new TreeNode(6);
        root.left.left.left.right = new TreeNode(8);

        iterativeTraversal(root);
        System.out.println();
        recursiveTraversal(root);
    }

    public static void iterativeTraversal(TreeNode root) {
        System.out.println("Iterative Pre-Order: " + preOrderTraversal(root)); // 1 2 4 6 8 7 5 3
        System.out.println("Iterative In-Order: " + inOrderTraversal(root)); // 6 8 4 7 2 5 1 3
        System.out.println("Iterative Post-Order: " + postOrderTraversal(root)); // 8 6 7 4 5 2 3 1
    }

    public static void recursiveTraversal(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();
        System.out.println("Recursive Pre-Order: " + preOrderTraversalRec(list, root)); // 1 2 4 6 8 7 5 3
        list.clear();
        System.out.println("Recursive In-Order: " + inOrderTraversalRec(list, root)); // 6 8 4 7 2 5 1 3
        list.clear();
        System.out.println("Recursive Post-Order: " + postOrderTraversalRec(list, root)); // 8 6 7 4 5 2 3 1
    }

    public static ArrayList<Integer> preOrderTraversal(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        ArrayList<Integer> list = new ArrayList<>();
        TreeNode node = root;

        while (node != null || !stack.empty()) {
            while (node != null) {
                stack.add(node);
                list.add(node.val);
                node = node.left;
            }
            node = stack.pop();
            node = node.right;
        }

        return list;
    }

    public static ArrayList<Integer> inOrderTraversal(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        ArrayList<Integer> list = new ArrayList<>();
        TreeNode node = root;

        while (node != null || !stack.empty()) {
            while (node != null) {
                stack.add(node);
                node = node.left;
            }
            node = stack.pop();
            list.add(node.val);
            node = node.right;
        }

        return list;
    }

    public static ArrayList<Integer> postOrderTraversal(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        ArrayList<Integer> list = new ArrayList<>();

        stack.add(root);
        while (!stack.empty()) {
            TreeNode node = stack.pop();
            stack2.add(node);
            if (node.left != null)
                stack.push(node.left);
            if (node.right != null)
                stack.push(node.right);
        }
        while (!stack2.isEmpty()) {
            list.add(stack2.pop().val);
        }
        return list;
    }

    public static ArrayList<Integer> preOrderTraversalRec(ArrayList<Integer> list, TreeNode root) {
        if (root == null) return list;
        list.add(root.val);
        preOrderTraversalRec(list, root.left);
        preOrderTraversalRec(list, root.right);
        return list;
    }

    public static ArrayList<Integer> inOrderTraversalRec(ArrayList<Integer> list, TreeNode root) {
        if (root == null) return list;
        inOrderTraversalRec(list, root.left);
        list.add(root.val);
        inOrderTraversalRec(list, root.right);
        return list;
    }

    public static ArrayList<Integer> postOrderTraversalRec(ArrayList<Integer> list, TreeNode root) {
        if (root == null) return list;
        postOrderTraversalRec(list, root.left);
        postOrderTraversalRec(list, root.right);
        list.add(root.val);
        return list;
    }
}
