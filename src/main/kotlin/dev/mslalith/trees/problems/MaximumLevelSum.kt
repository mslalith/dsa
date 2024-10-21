package dev.mslalith.trees.problems

import dev.mslalith.core.Problem
import dev.mslalith.core.TestCase
import dev.mslalith.trees.TreeNode
import dev.mslalith.trees.buildTreeNode

class MaximumLevelSum : Problem<TreeNode?, Int>() {
    
    companion object {
        @JvmStatic
        fun main(args: Array<String>) = MaximumLevelSum().run()
    }
    
    override fun getTestCases(): Array<TestCase<TreeNode?, Int>> = arrayOf(
        TestCase(
            input = buildTreeNode(input = "1,7,0,7,-8,null,null"),
            output = 2
        ),
        TestCase(
            input = buildTreeNode(input = "-100,-200,-300,-20,-5,-10,null"),
            output = 3
        )
    )
    
    override fun solve(testCaseInput: TreeNode?): Int {
        return maxLevelSum(testCaseInput)
    }

    private fun maxLevelSum(root: TreeNode?): Int {
        if (root == null) return 0

        val queue = ArrayDeque<TreeNode?>()
        queue.add(root)
        queue.add(null)

        var maxSum = Int.MIN_VALUE
        var sum = 0
        var level = 1
        var maxLevel = 1
        while (queue.isNotEmpty()) {
            val node = queue.removeFirst()
            if (node != null) {
                sum += node.`val`
                if (node.left != null) queue.add(node.left)
                if (node.right != null) queue.add(node.right)
            } else {
                if (sum > maxSum) {
                    maxLevel = level
                    maxSum = sum
                }
                sum = 0
                if (queue.isNotEmpty()) {
                    level++
                    queue.add(null)
                }
            }
        }

        return maxLevel
    }
}
