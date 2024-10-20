package dev.mslalith.trees.problems

import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.core.TestCase
import dev.mslalith.trees.TreeNode
import dev.mslalith.trees.buildTreeNode

class PathSum : TestCaseProblem<PathSumParams, Boolean>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = PathSum().runAll()
    }

    override fun getTestCases(): Array<TestCase<PathSumParams, Boolean>> = arrayOf(
        TestCase(
            input = PathSumParams(treeString = "5,4,8,11,null,13,4,7,2,null,null,null,1", targetSum = 22),
            output = true
        ),
        TestCase(
            input = PathSumParams(treeString = "-2,null,-3", targetSum = -5),
            output = true
        ),
        TestCase(
            input = PathSumParams(treeString = "1,-2,-3,1,3,-2,null,-1", targetSum = -1),
            output = true
        ),
        TestCase(
            input = PathSumParams(treeString = "1,2", targetSum = 1),
            output = false
        )
    )

    override fun solve(testCaseInput: PathSumParams): Boolean {
        return hasPathSum(
            root = buildTreeNode(testCaseInput.treeString),
            targetSum = testCaseInput.targetSum
        )
    }

    private fun hasPathSum(root: TreeNode?, targetSum: Int): Boolean {
        if (root != null && root.`val` == targetSum && root.left == null && root.right == null) return true
        if (root == null) return false

        val nextTargetSum = targetSum - root.`val`
        return hasPathSum(root.left, nextTargetSum) || hasPathSum(root.right, nextTargetSum)
    }
}

data class PathSumParams(
    val treeString: String,
    val targetSum: Int
) {
    override fun toString(): String {
        return """
            
            root: $treeString
            targetSum: $targetSum
        """.trimIndent()
    }
}
