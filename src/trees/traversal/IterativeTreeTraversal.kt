package src.trees.traversal

import src.trees.TreeNode
import java.util.Stack

class IterativeTreeTraversal : TreeTraversal {

    override fun preOrderTraversal(root: TreeNode?): List<Int> {
        val stack = Stack<TreeNode>()
        val list = mutableListOf<Int>()
        var node: TreeNode? = root
        while (node != null || !stack.empty()) {
            while (node != null) {
                stack.add(node)
                list.add(node.`val`)
                node = node.left
            }
            node = stack.pop()
            node = node.right
        }
        return list
    }

    override fun inOrderTraversal(root: TreeNode?): List<Int> {
        val stack = Stack<TreeNode>()
        val list = mutableListOf<Int>()
        var node: TreeNode? = root
        while (node != null || !stack.empty()) {
            while (node != null) {
                stack.add(node)
                node = node.left
            }
            node = stack.pop()
            list.add(node.`val`)
            node = node.right
        }
        return list
    }

    override fun postOrderTraversal(root: TreeNode?): List<Int> {
        val stack = Stack<TreeNode?>()
        val stack2 = Stack<TreeNode?>()
        val list = mutableListOf<Int>()
        stack.add(root)
        while (!stack.empty()) {
            val node = stack.pop()
            stack2.add(node)
            if (node!!.left != null) stack.push(node.left)
            if (node.right != null) stack.push(node.right)
        }
        while (!stack2.isEmpty()) {
            list.add(stack2.pop()!!.`val`)
        }
        return list
    }
}
