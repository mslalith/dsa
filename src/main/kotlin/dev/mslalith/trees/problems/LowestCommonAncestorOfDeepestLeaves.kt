package dev.mslalith.trees.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.trees.TreeNode
import dev.mslalith.trees.buildTreeNode

class LowestCommonAncestorOfDeepestLeaves : TestCaseProblem<TreeNode?, TreeNode?>() {
    
    companion object {
        @JvmStatic
        fun main(args: Array<String>) = LowestCommonAncestorOfDeepestLeaves().runForConsole()
    }
    
    override fun getTestCases(): Array<TestCase<TreeNode?, TreeNode?>> = arrayOf(
        TestCase(
            input = buildTreeNode(input = "3,5,1,6,2,0,8,null,null,7,4"),
            output = buildTreeNode(input = "2,7,4")
        ),
        TestCase(
            input = buildTreeNode(input = "1"),
            output = buildTreeNode(input = "1")
        ),
        TestCase(
            input = buildTreeNode(input = "0,1,3,null,2"),
            output = buildTreeNode(input = "2")
        )
    )
    
    override fun solve(testCaseInput: TreeNode?): TreeNode? {
        return lcaDeepestLeaves(testCaseInput)
    }

    private fun lcaDeepestLeaves(root: TreeNode?): TreeNode? {

        fun dfs(node: TreeNode?, depth: Int): Pair<TreeNode?, Int> {
            if (node == null) return null to depth

            val (leftLca, leftDepth) = dfs(node.left, depth + 1)
            val (rightLca, rightDepth) = dfs(node.right, depth + 1)

            return when {
                leftDepth > rightDepth -> leftLca to leftDepth
                rightDepth > leftDepth -> rightLca to rightDepth
                else -> node to leftDepth
            }
        }

        return dfs(root, 0).first
    }
}
