package dev.mslalith.trees.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.trees.TreeNode
import dev.mslalith.trees.buildTreeNode
import kotlin.math.max

class MaximumDepthOfBinaryTree : TestCaseProblem<TreeNode?, Int>() {
    
    companion object {
        @JvmStatic
        fun main(args: Array<String>) = MaximumDepthOfBinaryTree().runAll()
    }
    
    override fun getTestCases(): Array<TestCase<TreeNode?, Int>> = arrayOf(
        TestCase(
            input = buildTreeNode(input = "3,9,20,null,null,15,7"),
            output = 3
        ),
        TestCase(
            input = buildTreeNode(input = "1,null,2"),
            output = 2
        )
    )
    
    override fun solve(testCaseInput: TreeNode?): Int {
        return maxDepth(testCaseInput)
    }

    private fun maxDepth(root: TreeNode?): Int {
        if (root == null) return 0
        if (root.left == null && root.right == null) return 1

        return max(maxDepth(root.left), maxDepth(root.right)) + 1
    }
}
