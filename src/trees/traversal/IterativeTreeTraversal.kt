package src.trees.traversal;

import src.trees.TreeNode;

import java.util.ArrayList;
import java.util.Stack;

public class IterativeTreeTraversal implements TreeTraversal {
    @Override
    public ArrayList<Integer> preOrderTraversal(TreeNode root) {
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

    @Override
    public ArrayList<Integer> inOrderTraversal(TreeNode root) {
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

    @Override
    public ArrayList<Integer> postOrderTraversal(TreeNode root) {
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
}
