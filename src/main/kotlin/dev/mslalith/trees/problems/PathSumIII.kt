package dev.mslalith.trees.problems

import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.core.TestCase
import dev.mslalith.trees.TreeNode
import dev.mslalith.trees.buildTreeNode

class PathSumIII : TestCaseProblem<Pair<TreeNode?, Int>, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = PathSumIII().runAll()
    }

    override fun getTestCases(): Array<TestCase<Pair<TreeNode?, Int>, Int>> = arrayOf(
        TestCase(
            input = buildTreeNode(input = "10,5,-3,3,2,null,11,3,-2,null,1") to 8,
            output = 3
        ),
        TestCase(
            input = buildTreeNode(input = "5,4,8,11,null,13,4,7,2,null,null,5,1") to 22,
            output = 3
        ),
        TestCase(
            input = buildTreeNode(input = "1,-2,-3,1,3,-2,null,-1") to -1,
            output = 4
        ),
        TestCase(
            input = buildTreeNode(input = "1000000000,1000000000,null,294967296,null,1000000000,null,1000000000,null,1000000000") to 0,
            output = 0
        )
    )

    override fun solve(testCaseInput: Pair<TreeNode?, Int>): Int {
        return pathSum(testCaseInput.first, testCaseInput.second)
    }

    private fun pathSum(root: TreeNode?, targetSum: Int): Int {
        if (root == null) return 0
        return pathSumFrom(root, targetSum.toLong()) + pathSum(root.left, targetSum) + pathSum(root.right, targetSum)
    }

    private fun pathSumFrom(root: TreeNode?, targetSum: Long): Int {
        if (root == null) return 0
        val match = if (root.`val`.toLong() == targetSum) 1 else 0
        val sum = targetSum - root.`val`
        return match + pathSumFrom(root.left, sum) + pathSumFrom(root.right, sum)
    }
}
