package dev.mslalith.trees.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.trees.TreeNode
import dev.mslalith.trees.buildTreeNode

class RecoverTreeFromPreorderTraversal : TestCaseProblem<String, TreeNode?>() {
    
    companion object {
        @JvmStatic
        fun main(args: Array<String>) = RecoverTreeFromPreorderTraversal().runForConsole()
    }
    
    override fun getTestCases(): Array<TestCase<String, TreeNode?>> = arrayOf(
        TestCase(
            input = "1-2--3--4-5--6--7",
            output = buildTreeNode(input = "1,2,5,3,4,6,7")
        ),
        TestCase(
            input = "1-2--3---4-5--6---7",
            output = buildTreeNode(input = "1,2,5,3,null,6,null,4,null,7")
        )
    )
    
    override fun solve(testCaseInput: String): TreeNode? {
        return recoverFromPreorder(testCaseInput)
    }

    private fun recoverFromPreorder(traversal: String): TreeNode? {
        val n = traversal.length
        var idx = 0

        fun dfs(depth: Int): TreeNode? {
            if (idx >= n) return null

            var dashCount = 0
            while (traversal[idx] == '-') {
                dashCount++
                idx++
            }

            if (dashCount < depth) {
                idx -= dashCount
                return null
            }

            var num = 0
            while (idx < n && traversal[idx].isDigit()) {
                num = (num * 10) + (traversal[idx] - '0')
                idx++
            }

            val node = TreeNode(num)
            node.left = dfs(depth + 1)
            node.right = dfs(depth + 1)
            return node
        }

        return dfs(0)
    }
}
