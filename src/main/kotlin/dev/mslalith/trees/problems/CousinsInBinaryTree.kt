package dev.mslalith.trees.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.trees.TreeNode
import dev.mslalith.trees.buildTreeNode
import dev.mslalith.trees.problems.CousinsInBinaryTree.Params
import java.util.LinkedList
import java.util.Queue

class CousinsInBinaryTree : TestCaseProblem<Params, Boolean>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = CousinsInBinaryTree().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<Params, Boolean>> = arrayOf(
        TestCase(
            input = Params(
                root = buildTreeNode(input = "1,2,3,4"),
                x = 4,
                y = 3
            ),
            output = false
        ),
        TestCase(
            input = Params(
                root = buildTreeNode(input = "1,2,3,null,4,null,5"),
                x = 5,
                y = 4
            ),
            output = true
        ),
        TestCase(
            input = Params(
                root = buildTreeNode(input = "1,2,3,null,4"),
                x = 2,
                y = 3
            ),
            output = false
        )
    )

    override fun solve(testCaseInput: Params): Boolean {
        return isCousins(testCaseInput.root, testCaseInput.x, testCaseInput.y)
    }

    private fun isCousins(root: TreeNode?, x: Int, y: Int): Boolean {
        if (root == null) return false

        fun findNode(node: TreeNode?, value: Int, depth: Int): Pair<TreeNode, Int>? {
            if (node == null) return null
            if (node.left?.`val` == value) return node to depth
            if (node.right?.`val` == value) return node to depth

            return findNode(node.left, value, depth + 1) ?: findNode(node.right, value, depth + 1)
        }

        val (xParent, xDepth) = findNode(root, x, 0) ?: return false
        val (yParent, yDepth) = findNode(root, y, 0) ?: return false

        if (xParent == yParent) return false
        if (xDepth != yDepth) return false

        return true
    }

    private fun isCousinsBfs(root: TreeNode?, x: Int, y: Int): Boolean {
        if (root == null) return false

        val queue: Queue<TreeNode> = LinkedList()
        queue.add(root)

        while (queue.isNotEmpty()) {
            val size = queue.size

            var isXFound = false
            var isYFound = false

            for (i in 0 until size) {
                val node = queue.poll()

                if (node.`val` == x) isXFound = true
                if (node.`val` == y) isYFound = true

                val left = node.left
                val right = node.right
                if (left != null && right != null) {
                    if (left.`val` == x && right.`val` == y) return false
                    if (left.`val` == y && right.`val` == x) return false
                }

                if (left != null) queue.add(left)
                if (right != null) queue.add(right)
            }

            if (isXFound && isYFound) return true
        }

        return false
    }

    data class Params(
        val root: TreeNode?,
        val x: Int,
        val y: Int
    ) {
        override fun toString(): String {
            return """
                
                root: $root
                x: $x
                y: $y
            """.trimIndent()
        }
    }
}
