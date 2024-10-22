package dev.mslalith.trees.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.trees.TreeNode
import dev.mslalith.trees.buildTreeNode

class SameTree : TestCaseProblem<Pair<TreeNode?, TreeNode?>, Boolean>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = SameTree().runAll()
    }

    override fun getTestCases(): Array<TestCase<Pair<TreeNode?, TreeNode?>, Boolean>> = arrayOf(
        TestCase(
            input = buildTreeNode(input = "1,2,3") to buildTreeNode(input = "1,2,3"),
            output = true
        ),
        TestCase(
            input = buildTreeNode(input = "1,2") to buildTreeNode(input = "1,null,2"),
            output = false
        ),
        TestCase(
            input = buildTreeNode(input = "1,2,1") to buildTreeNode(input = "1,1,2"),
            output = false
        )
    )

    override fun solve(testCaseInput: Pair<TreeNode?, TreeNode?>): Boolean {
        return isSameTree(testCaseInput.first, testCaseInput.second)
    }

    private fun isSameTree(p: TreeNode?, q: TreeNode?): Boolean {
        if (p == null && q == null) return true
        if (p == null || q == null) return false
        if (p.`val` != q.`val`) return false

        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right)
    }
}
