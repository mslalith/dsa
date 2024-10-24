package dev.mslalith.trees.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.trees.TreeNode
import dev.mslalith.trees.buildTreeNode

class ConstructBinaryTreeFromPreorderAndInorderTraversal : TestCaseProblem<Pair<IntArray, IntArray>, TreeNode?>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = ConstructBinaryTreeFromPreorderAndInorderTraversal().runAll()
    }

    override fun getTestCases(): Array<TestCase<Pair<IntArray, IntArray>, TreeNode?>> = arrayOf(
        TestCase(
            input = intArrayOf(3, 9, 20, 15, 7) to intArrayOf(9, 3, 15, 20, 7),
            output = buildTreeNode(input = "3,9,20,null,null,15,7")
        )
    )

    override fun solve(testCaseInput: Pair<IntArray, IntArray>): TreeNode? {
        return buildTree(testCaseInput.first, testCaseInput.second)
    }

    private fun buildTree(preorder: IntArray, inorder: IntArray): TreeNode? {
        val map = hashMapOf<Int, Int>()
        for (i in inorder.indices) map[inorder[i]] = i

        fun build(preStart: Int, preEnd: Int, inStart: Int, inEnd: Int): TreeNode? {
            if (preStart > preEnd || inStart > inEnd) return null

            val node = TreeNode(preorder[preStart])
            val inIndex = map.getValue(node.`val`)
            val leftSize = inIndex - inStart

            node.left = build(preStart + 1, preStart + leftSize, inStart, inIndex - 1)
            node.right = build(preStart + leftSize + 1, preEnd, inIndex + 1, inEnd)
            return node
        }



        return build(0, preorder.lastIndex, 0, inorder.lastIndex)
    }
}
