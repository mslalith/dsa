package dev.mslalith.trees.problems

import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.core.TestCase
import dev.mslalith.trees.TreeNode
import dev.mslalith.trees.buildTreeNode

class AverageOfLevelsInBinaryTree : TestCaseProblem<TreeNode?, DoubleArray>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = AverageOfLevelsInBinaryTree().runAll()
    }

    override fun getTestCases(): Array<TestCase<TreeNode?, DoubleArray>> = arrayOf(
        TestCase(
            input = buildTreeNode(input = "3,9,20,null,null,15,7"),
            output = doubleArrayOf(3.0, 14.5, 11.0)
        ),
        TestCase(
            input = buildTreeNode(input = "3,9,20,15,7"),
            output = doubleArrayOf(3.0, 14.5, 11.0)
        ),
        TestCase(
            input = buildTreeNode(input = "2147483647,2147483647,2147483647"),
            output = doubleArrayOf(2147483647.0, 2147483647.0)
        )
    )

    override fun solve(testCaseInput: TreeNode?): DoubleArray {
        return averageOfLevels(testCaseInput)
    }

    private fun averageOfLevels(root: TreeNode?): DoubleArray {
        if (root == null) return doubleArrayOf()

        val list = mutableListOf<Double>()
        val queue = ArrayDeque<TreeNode?>()
        queue.add(root)
        queue.add(null)

        var sum = 0.0
        var count = 0

        while (queue.isNotEmpty()) {
            val node = queue.removeFirst()
            if (node != null) {
                sum += node.`val`
                count++
                if (node.left != null) queue.add(node.left)
                if (node.right != null) queue.add(node.right)
            } else {
                list.add(sum / count)
                sum = 0.0
                count = 0
                if (queue.isNotEmpty()) queue.add(null)
            }
        }

        return list.toDoubleArray()
    }
}
