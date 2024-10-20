package src.trees.problems

import src.core.Problem
import src.core.TestCase
import src.trees.TreeNode
import src.trees.buildTreeNode

class BinaryTreeZigzagLevelOrderTraversal : Problem<TreeNode?, List<List<Int>>>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = BinaryTreeZigzagLevelOrderTraversal().run()
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
        var level = mutableListOf<Int>()
        var isReverse = false

        while (queue.isNotEmpty()) {
            val node = queue.removeFirst()
            if (node != null) {
                if (isReverse) level.add(0, node.`val`) else level.add(node.`val`)
                if (node.left != null) queue.add(node.left)
                if (node.right != null) queue.add(node.right)
            } else {
                isReverse = !isReverse
                result.add(level)
                level = mutableListOf()
                if (queue.isNotEmpty()) queue.add(null)
            }
        }

        return result
    }
}
