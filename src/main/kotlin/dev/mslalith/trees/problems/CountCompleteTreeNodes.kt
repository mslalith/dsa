package dev.mslalith.trees.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.trees.TreeNode
import dev.mslalith.trees.buildTreeNode

class CountCompleteTreeNodes : TestCaseProblem<TreeNode?, Int>() {
    
    companion object {
        @JvmStatic
        fun main(args: Array<String>) = CountCompleteTreeNodes().runForConsole()
    }
    
    override fun getTestCases(): Array<TestCase<TreeNode?, Int>> = arrayOf(
        TestCase(
            input = buildTreeNode(input = "1,2,3,4,5,6"),
            output = 6
        ),
        TestCase(
            input = buildTreeNode(input = ""),
            output = 0
        ),
        TestCase(
            input = buildTreeNode(input = "1"),
            output = 1
        )
    )
    
    override fun solve(testCaseInput: TreeNode?): Int {
        return countNodes(testCaseInput)
    }

    private fun countNodes(root: TreeNode?): Int {
        if (root == null) return 0

        var count = 0

        fun dfs(node: TreeNode?) {
            if (node == null) return

            count++
            dfs(node.left)
            dfs(node.right)
        }

        dfs(root)
        return count
    }
}
