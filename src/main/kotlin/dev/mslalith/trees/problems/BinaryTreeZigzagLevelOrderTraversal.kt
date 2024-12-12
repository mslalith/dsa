package dev.mslalith.trees.problems

import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.core.TestCase
import dev.mslalith.trees.TreeNode
import dev.mslalith.trees.buildTreeNode

class BinaryTreeZigzagLevelOrderTraversal : TestCaseProblem<TreeNode?, List<List<Int>>>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = BinaryTreeZigzagLevelOrderTraversal().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<TreeNode?, List<List<Int>>>> = arrayOf(
        TestCase(
            input = buildTreeNode(input = "3,9,20,null,null,15,7"),
            output = listOf(
                listOf(3),
                listOf(20, 9),
                listOf(15, 7)
            )
        ),
        TestCase(
            input = buildTreeNode(input = "1"),
            output = listOf(
                listOf(1)
            )
        )
    )

    override fun solve(testCaseInput: TreeNode?): List<List<Int>> {
        return zigzagLevelOrder(testCaseInput)
    }

    private fun zigzagLevelOrder(root: TreeNode?): List<List<Int>> {
        if (root == null) return emptyList()

        val queue = ArrayDeque<TreeNode?>()
        queue.add(root)
        queue.add(null)

        val result = mutableListOf<List<Int>>()
        val level = mutableListOf<Int>()
        var isReverse = false

        while (queue.isNotEmpty()) {
            val node = queue.removeFirst()

            if (node != null) {
                if (isReverse) level.add(0, node.`val`) else level.add(node.`val`)

                if (node.left != null) queue.add(node.left)
                if (node.right != null) queue.add(node.right)
            } else {
                isReverse = !isReverse
                result.add(level.toList())
                level.clear()

                if (queue.isNotEmpty()) queue.add(null)
            }
        }

        return result
    }
}
