package dev.mslalith.trees.problems

import dev.mslalith.core.Problem
import dev.mslalith.core.TestCase
import dev.mslalith.trees.TreeNode
import dev.mslalith.trees.buildTreeNode

class DeleteNodeInBST : Problem<Pair<TreeNode?, Int>, TreeNode?>() {
    
    companion object {
        @JvmStatic
        fun main(args: Array<String>) = DeleteNodeInBST().run()
    }
    
    override fun getTestCases(): Array<TestCase<Pair<TreeNode?, Int>, TreeNode?>> = arrayOf(
        TestCase(
            input = buildTreeNode(input = "5,3,6,2,4,null,7") to 3,
            output = buildTreeNode(input = "5,4,6,2,null,null,7")
        ),
        TestCase(
            input = buildTreeNode(input = "5,3,6,2,4,null,7") to 0,
            output = buildTreeNode(input = "5,3,6,2,4,null,7")
        )
    )
    
    override fun solve(testCaseInput: Pair<TreeNode?, Int>): TreeNode? {
        return deleteNode(testCaseInput.first, testCaseInput.second)
    }

    private fun deleteNode(root: TreeNode?, key: Int): TreeNode? {
        if (root == null) return null

        when {
            key < root.`val` -> root.left = deleteNode(root.left, key)
            key > root.`val` -> root.right = deleteNode(root.right, key)
            else -> {
                if (root.left == null && root.right == null) return null
                else if (root.left == null || root.right == null) return root.left ?: root.right
                else {
                    val successor = nextInOrder(root.right)
                    if (successor != null) {
                        root.`val` = successor.`val`
                        root.right = deleteNode(root.right, root.`val`)
                    }
                }
            }
        }

        return root
    }

    private fun nextInOrder(root: TreeNode?): TreeNode? {
        var curr = root
        while (curr?.left != null) curr = curr.left
        return curr
    }
}
