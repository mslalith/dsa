package dev.mslalith.trees.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.trees.TreeNode
import dev.mslalith.trees.buildTreeNode

class SymmetricTree : TestCaseProblem<TreeNode?, Boolean>() {
    
    companion object {
        @JvmStatic
        fun main(args: Array<String>) = SymmetricTree().runAll()
    }
    
    override fun getTestCases(): Array<TestCase<TreeNode?, Boolean>> = arrayOf(
        TestCase(
            input = buildTreeNode(input = "1,2,2,3,4,4,3"),
            output = true
        ),
        TestCase(
            input = buildTreeNode(input = "1,2,2,null,3,null,3"),
            output = false
        )
    )
    
    override fun solve(testCaseInput: TreeNode?): Boolean {
        return isSymmetric(testCaseInput)
    }

    private fun isSymmetric(root: TreeNode?): Boolean {
        if (root == null) return false
        return isSymmetric(root.left, root.right)
    }

    private fun isSymmetric(left: TreeNode?, right: TreeNode?): Boolean {
        if (left == null && right == null) return true
        if (left == null || right == null) return false
        if (left.`val` != right.`val`) return false

        return isSymmetric(left.left, right.right) && isSymmetric(left.right, right.left)
    }
}
