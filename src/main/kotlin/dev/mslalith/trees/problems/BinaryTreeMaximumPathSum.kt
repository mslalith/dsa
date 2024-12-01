package dev.mslalith.trees.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.trees.TreeNode
import dev.mslalith.trees.buildTreeNode
import kotlin.math.max

class BinaryTreeMaximumPathSum : TestCaseProblem<TreeNode?, Int>() {
    
    companion object {
        @JvmStatic
        fun main(args: Array<String>) = BinaryTreeMaximumPathSum().runForConsole()
    }
    
    override fun getTestCases(): Array<TestCase<TreeNode?, Int>> = arrayOf(
        TestCase(
            input = buildTreeNode(input = "1,2,3"),
            output = 6
        ),
        TestCase(
            input = buildTreeNode(input = "-10,9,20,null,null,15,7"),
            output = 42
        ),
        TestCase(
            input = buildTreeNode(input = "-3"),
            output = -3
        ),
        TestCase(
            input = buildTreeNode(input = "1,2"),
            output = 3
        ),
        TestCase(
            input = buildTreeNode(input = "2,-1"),
            output = 2
        ),
        TestCase(
            input = buildTreeNode(input = "1,-2,3"),
            output = 4
        ),
        TestCase(
            input = buildTreeNode(input = "1,-2,-3,1,3,-2,null,-1"),
            output = 3
        ),
        TestCase(
            input = buildTreeNode(input = "9,6,-3,null,null,-6,2,null,null,2,null,-6,-6,-6"),
            output = 16
        )
    )
    
    override fun solve(testCaseInput: TreeNode?): Int {
        return maxPathSum(testCaseInput)
    }

    private fun maxPathSum(root: TreeNode?): Int {
        if (root == null) return Int.MIN_VALUE

        var maxSum = Int.MIN_VALUE

        fun findMaxPathSum(node: TreeNode?): Int {
            if (node == null) return 0

            val value = node.`val`
            val left = max(0, findMaxPathSum(node.left))
            val right = max(0, findMaxPathSum(node.right))
            val leftAndRight = value + left + right

            var pathSum = max(value, max(value + left, value + right))
            pathSum = max(pathSum, leftAndRight)

            maxSum = max(maxSum, pathSum)
            return value + max(left, right)
        }

        findMaxPathSum(root)
        return maxSum
    }
}
