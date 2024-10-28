package dev.mslalith.trees.problems

import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.core.TestCase
import dev.mslalith.trees.TreeNode
import dev.mslalith.trees.buildTreeNode

class ValidateBinarySearchTree : TestCaseProblem<TreeNode?, Boolean>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = ValidateBinarySearchTree().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<TreeNode?, Boolean>> = arrayOf(
        TestCase(
            input = buildTreeNode(input = "2,1,3"),
            output = true
        ),
        TestCase(
            input = buildTreeNode(input = "5,1,4,null,null,3,6"),
            output = false
        ),
        TestCase(
            input = buildTreeNode(input = "5,4,6,null,null,3,7"),
            output = false
        ),
        TestCase(
            input = buildTreeNode(input = "2147483647"),
            output = true
        )
    )

    override fun solve(testCaseInput: TreeNode?): Boolean {
        return isValidBST(testCaseInput)
    }

    private fun isValidBST(root: TreeNode?): Boolean {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE)
    }

    private fun isValidBST(root: TreeNode?, min: Long, max: Long): Boolean {
        if (root == null) return true
        if (root.`val` <= min || root.`val` >= max) return false

        val value = root.`val`.toLong()
        return isValidBST(root.left, min, value) && isValidBST(root.right, value, max)
    }
}
