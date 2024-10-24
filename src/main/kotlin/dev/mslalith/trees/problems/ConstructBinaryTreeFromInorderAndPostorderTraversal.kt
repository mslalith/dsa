package dev.mslalith.trees.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.trees.TreeNode
import dev.mslalith.trees.buildTreeNode

class ConstructBinaryTreeFromInorderAndPostorderTraversal : TestCaseProblem<Pair<IntArray, IntArray>, TreeNode?>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = ConstructBinaryTreeFromInorderAndPostorderTraversal().runAll()
    }

    override fun getTestCases(): Array<TestCase<Pair<IntArray, IntArray>, TreeNode?>> = arrayOf(
        TestCase(
            input = intArrayOf(9, 3, 15, 20, 7) to intArrayOf(9, 15, 7, 20, 3),
            output = buildTreeNode(input = "3,9,20,null,null,15,7")
        ),
        TestCase(
            input = intArrayOf(-1) to intArrayOf(-1),
            output = buildTreeNode(input = "-1")
        )
    )

    override fun solve(testCaseInput: Pair<IntArray, IntArray>): TreeNode? {
        return buildTree(testCaseInput.first, testCaseInput.second)
    }

    private fun buildTree(inorder: IntArray, postorder: IntArray): TreeNode? {
        val map = hashMapOf<Int, Int>()
        for (i in inorder.indices) map[inorder[i]] = i

        fun build(postStart: Int, postEnd: Int, inStart: Int, inEnd: Int): TreeNode? {
            if (postStart > postEnd || inStart > inEnd) return null

            val node = TreeNode(postorder[postEnd])
            val inIndex = map.getValue(node.`val`)
            val leftSize = inIndex - inStart

            node.left = build(postStart, postStart + leftSize - 1, inStart, inIndex - 1)
            node.right = build(postStart + leftSize, postEnd - 1, inIndex + 1, inEnd)
            return node
        }

        return build(0, postorder.lastIndex, 0, inorder.lastIndex)
    }
}
