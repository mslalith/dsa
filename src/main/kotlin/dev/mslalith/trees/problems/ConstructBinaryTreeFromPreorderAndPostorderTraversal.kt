package dev.mslalith.trees.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.trees.TreeNode
import dev.mslalith.trees.buildTreeNode
import java.util.Stack

class ConstructBinaryTreeFromPreorderAndPostorderTraversal : TestCaseProblem<Pair<IntArray, IntArray>, TreeNode?>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = ConstructBinaryTreeFromPreorderAndPostorderTraversal().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<Pair<IntArray, IntArray>, TreeNode?>> = arrayOf(
        TestCase(
            input = intArrayOf(1, 2, 4, 5, 3, 6, 7) to intArrayOf(4, 5, 2, 6, 7, 3, 1),
            output = buildTreeNode(input = "1,2,3,4,5,6,7")
        ),
        TestCase(
            input = intArrayOf(1) to intArrayOf(1),
            output = buildTreeNode(input = "1")
        )
    )

    override fun solve(testCaseInput: Pair<IntArray, IntArray>): TreeNode? {
        return constructFromPrePost(testCaseInput.first, testCaseInput.second)
    }

    private fun constructFromPrePost(preorder: IntArray, postorder: IntArray): TreeNode? {
        val n = preorder.size
        if (n == 0) return null

        val root = TreeNode(preorder[0])
        val stack = Stack<TreeNode>()
        stack.push(root)

        var preOrderIndex = 1
        var postOrderIndex = 0

        while (stack.isNotEmpty()) {
            val node = stack.peek()

            if (node.`val` == postorder[postOrderIndex]) {
                stack.pop()
                postOrderIndex++
            } else {
                val newNode = TreeNode(preorder[preOrderIndex++])
                if (node.left == null) node.left = newNode else node.right = newNode
                stack.push(newNode)
            }
        }

        return root
    }
}
