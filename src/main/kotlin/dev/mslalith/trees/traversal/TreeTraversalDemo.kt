package dev.mslalith.trees.traversal

import dev.mslalith.trees.TreeNode
import dev.mslalith.trees.buildTreeNode

object TreeTraversalDemo {

    @JvmStatic
    fun main(args: Array<String>) {
        val root = buildTreeNode("1,2,3,4,5,null,null,6,7,null,null,null,8")
        println("*** Iterative Traversal ***")
        iterativeTraversal(root)
        println()

        println("*** Recursive Traversal ***")
        recursiveTraversal(root)
    }

    private fun iterativeTraversal(root: TreeNode?) {
        val treeTraversal: TreeTraversal = IterativeTreeTraversal()
        println("Pre-Order: " + displayTraversalOutput(treeTraversal.preOrderTraversal(root))) // 1 2 4 6 8 7 5 3
        println("In-Order: " + displayTraversalOutput(treeTraversal.inOrderTraversal(root))) // 6 8 4 7 2 5 1 3
        println("Post-Order: " + displayTraversalOutput(treeTraversal.postOrderTraversal(root))) // 8 6 7 4 5 2 3 1
    }

    private fun recursiveTraversal(root: TreeNode?) {
        val treeTraversal: TreeTraversal = RecursiveTreeTraversal()
        println("Pre-Order: " + displayTraversalOutput(treeTraversal.preOrderTraversal(root))) // 1 2 4 6 8 7 5 3
        println("In-Order: " + displayTraversalOutput(treeTraversal.inOrderTraversal(root))) // 6 8 4 7 2 5 1 3
        println("Post-Order: " + displayTraversalOutput(treeTraversal.postOrderTraversal(root))) // 8 6 7 4 5 2 3 1
    }

    private fun displayTraversalOutput(list: List<Int>): String = buildString {
        for (i in list.indices) {
            if (i != 0) append(" ")
            append(list[i])
        }
    }
}
