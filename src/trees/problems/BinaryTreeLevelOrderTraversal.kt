package src.trees.problems

import src.core.Problem
import src.core.TestCase
import src.trees.TreeNode
import src.trees.buildTreeNode
import java.util.LinkedList

class BinaryTreeLevelOrderTraversal : Problem<TreeNode?, List<List<Int>>>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = BinaryTreeLevelOrderTraversal().run()
    }

    override fun getTestCases(): Array<TestCase<TreeNode?, List<List<Int>>>> = arrayOf(
        TestCase(
            input = buildTreeNode(input = "3,9,20,null,null,15,7"),
            output = listOf(listOf(3), listOf(9, 20), listOf(15, 7))
        ),
        TestCase(
            input = buildTreeNode(input = "1"),
            output = listOf(listOf(1))
        ),
        TestCase(
            input = buildTreeNode(input = ""),
            output = listOf()
        )
    )

    override fun solve(testCaseInput: TreeNode?): List<List<Int>> {
        return levelOrder(testCaseInput)
    }

    private fun levelOrder(root: TreeNode?): List<List<Int>> {
        val finalList = mutableListOf<List<Int>>()
        if (root == null) return finalList

        var levelList = mutableListOf<Int>()
        val queue = LinkedList<TreeNode?>()
        queue.add(root)
        queue.add(null)
        while (queue.isNotEmpty()) {
            val node = queue.removeFirst()
            if (node != null) {
                levelList.add(node.`val`)
                node.left?.let { queue.add(it) }
                node.right?.let { queue.add(it) }
            } else {
                finalList.add(levelList)
                levelList = mutableListOf()
                if (queue.isEmpty()) break
                queue.add(null)
            }
        }
        return finalList
    }
}