package dev.mslalith.trees.problems

import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.core.TestCase
import dev.mslalith.trees.TreeNode
import dev.mslalith.trees.buildTreeNode

class IsValidBST : TestCaseProblem<String, Boolean>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = IsValidBST().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<String, Boolean>> = arrayOf(
        TestCase(input = "5,1,4,null,null,3,6", output = false),
        TestCase(input = "2,2,2", output = false),
        TestCase(input = "5,4,6,null,null,3,7", output = false)
    )

    override fun solve(testCaseInput: String): Boolean {
        return isValidBST(buildTreeNode(testCaseInput), Long.MIN_VALUE, Long.MAX_VALUE)
    }

    private fun isValidBST(root: TreeNode?, min: Long, max: Long): Boolean {
        if (root == null) return true
        if (root.`val` >= max || root.`val` <= min) return false

        val value = root.`val`.toLong()
        return isValidBST(root.left, min, value) && isValidBST(root.right, value, max)
    }
}
