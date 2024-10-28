package dev.mslalith.trees.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.trees.TreeNode
import dev.mslalith.trees.buildTreeNode

class PathSum : TestCaseProblem<Pair<TreeNode?, Int>, Boolean>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = PathSum().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<Pair<TreeNode?, Int>, Boolean>> = arrayOf(
        TestCase(
            input = buildTreeNode(input = "5,4,8,11,null,13,4,7,2,null,null,null,1") to 22,
            output = true
        ),
        TestCase(
            input = buildTreeNode(input = "-2,null,-3") to -5,
            output = true
        ),
        TestCase(
            input = buildTreeNode(input = "1,-2,-3,1,3,-2,null,-1") to -1,
            output = true
        ),
        TestCase(
            input = buildTreeNode(input = "1,2") to 1,
            output = false
        )
    )

    override fun solve(testCaseInput: Pair<TreeNode?, Int>): Boolean {
        return hasPathSum(testCaseInput.first, testCaseInput.second)
    }

    private fun hasPathSum(root: TreeNode?, targetSum: Int): Boolean {
        if (root == null) return false
        if (root.`val` == targetSum && root.left == null && root.right == null) return true

        val nextTargetSum = targetSum - root.`val`
        return hasPathSum(root.left, nextTargetSum) || hasPathSum(root.right, nextTargetSum)
    }
}
