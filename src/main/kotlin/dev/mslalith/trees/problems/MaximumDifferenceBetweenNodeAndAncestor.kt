package dev.mslalith.trees.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.trees.TreeNode
import dev.mslalith.trees.buildTreeNode
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

class MaximumDifferenceBetweenNodeAndAncestor : TestCaseProblem<TreeNode?, Int>() {
    
    companion object {
        @JvmStatic
        fun main(args: Array<String>) = MaximumDifferenceBetweenNodeAndAncestor().runForConsole()
    }
    
    override fun getTestCases(): Array<TestCase<TreeNode?, Int>> = arrayOf(
        TestCase(
            input = buildTreeNode(input = "8,3,10,1,6,null,14,null,null,4,7,13"),
            output = 7
        ),
        TestCase(
            input = buildTreeNode(input = "1,null,2,null,0,3"),
            output = 3
        )
    )
    
    override fun solve(testCaseInput: TreeNode?): Int {
        return maxAncestorDiff(testCaseInput)
    }

    private fun maxAncestorDiff(root: TreeNode?): Int {
        if (root == null) return 0

        fun findMaxAncestorDiff(node: TreeNode?, mini: Int, maxi: Int): Int {
            if (node == null) return maxi - mini

            val nextMin = min(mini, node.`val`)
            val nextMax = max(maxi, node.`val`)
            val left = findMaxAncestorDiff(node.left, nextMin, nextMax)
            val right = findMaxAncestorDiff(node.right, nextMin, nextMax)
            return max(left, right)
        }

        return findMaxAncestorDiff(root, root.`val`, root.`val`)
    }

    private fun maxAncestorDiffNaive(root: TreeNode?): Int {
        if (root == null) return 0

        fun findMaxAncestorDiff(node: TreeNode?, parent: Int): Int {
            if (node == null) return 0
            val diff = abs(parent - node.`val`)
            return max(diff, max(findMaxAncestorDiff(node.left, parent), findMaxAncestorDiff(node.right, parent)))
        }

        val maxi = findMaxAncestorDiff(root, root.`val`)
        val left = maxAncestorDiff(root.left)
        val right = maxAncestorDiff(root.right)
        return max(maxi, max(left, right))
    }
}
