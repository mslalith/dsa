package dev.mslalith.trees.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.trees.TreeNode
import dev.mslalith.trees.buildTreeNode
import kotlin.math.absoluteValue
import kotlin.math.max

class BalancedBinaryTree : TestCaseProblem<TreeNode?, Boolean>() {
    
    companion object {
        @JvmStatic
        fun main(args: Array<String>) = BalancedBinaryTree().runForConsole()
    }
    
    override fun getTestCases(): Array<TestCase<TreeNode?, Boolean>> = arrayOf(
        TestCase(
            input = buildTreeNode(input = "3,9,20,null,null,15,7"),
            output = true
        ),
        TestCase(
            input = buildTreeNode(input = "1,2,2,3,3,null,null,4,4"),
            output = false
        ),
        TestCase(
            input = buildTreeNode(input = ""),
            output = true
        ),
        TestCase(
            input = buildTreeNode(input = "1,2,2,3,null,null,3,4,null,null,4"),
            output = false
        )
    )
    
    override fun solve(testCaseInput: TreeNode?): Boolean {
        return isBalanced(testCaseInput)
    }

    private fun isBalanced(root: TreeNode?): Boolean {
        if (root == null) return true

        fun height(node: TreeNode?): Int {
            if (node == null) return 0

            val left = height(node.left)
            if (left == -1) return -1

            val right = height(node.right)
            if (right == -1) return -1

            val diff = (left - right).absoluteValue
            if (diff > 1) return -1

            return 1 + max(left, right)
        }

        return height(root) != -1
    }
}
