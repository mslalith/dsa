package dev.mslalith.trees.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.trees.TreeNode
import dev.mslalith.trees.buildTreeNode
import java.util.LinkedList
import java.util.Queue
import kotlin.math.min

class MinimumDepthOfBinaryTree : TestCaseProblem<TreeNode?, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = MinimumDepthOfBinaryTree().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<TreeNode?, Int>> = arrayOf(
        TestCase(
            input = buildTreeNode(input = "3,9,20,null,null,15,7"),
            output = 2
        ),
        TestCase(
            input = buildTreeNode(input = "2,null,3,null,4,null,5,null,6"),
            output = 5
        )
    )

    override fun solve(testCaseInput: TreeNode?): Int {
        return minDepth(testCaseInput)
    }

    private fun minDepth(root: TreeNode?): Int {
        if (root == null) return 0

        val queue: Queue<TreeNode> = LinkedList()
        queue.add(root)

        var depth = 1

        while (queue.isNotEmpty()) {
            repeat(queue.size) {
                val node = queue.poll()
                if (node.left == null && node.right == null) return depth
                if (node.left != null) queue.add(node.left)
                if (node.right != null) queue.add(node.right)
            }

            depth++
        }

        return depth
    }

    private fun minDepthRecursive(root: TreeNode?): Int {
        if (root == null) return 0

        fun findMinDepth(node: TreeNode?): Int {
            if (node == null) return Int.MAX_VALUE

            val left = findMinDepth(node.left)
            val right = findMinDepth(node.right)

            return when {
                left != Int.MAX_VALUE && right != Int.MAX_VALUE -> 1 + min(left, right)
                left != Int.MAX_VALUE -> 1 + left
                right != Int.MAX_VALUE -> 1 + right
                else -> 0
            }
        }

        return 1 + findMinDepth(root)
    }
}
