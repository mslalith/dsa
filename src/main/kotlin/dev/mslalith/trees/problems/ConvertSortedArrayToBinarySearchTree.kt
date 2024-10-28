package dev.mslalith.trees.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.trees.TreeNode
import dev.mslalith.trees.buildTreeNode
import kotlin.math.ceil

class ConvertSortedArrayToBinarySearchTree : TestCaseProblem<IntArray, TreeNode?>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = ConvertSortedArrayToBinarySearchTree().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<IntArray, TreeNode?>> = arrayOf(
        TestCase(
            input = intArrayOf(-10, -3, 0, 5, 9),
            output = buildTreeNode(input = "0,-3,9,-10,null,5")
        ),
        TestCase(
            input = intArrayOf(1, 3),
            output = buildTreeNode(input = "3,1")
        )
    )

    override fun solve(testCaseInput: IntArray): TreeNode? {
        return sortedArrayToBST(testCaseInput)
    }

    private fun sortedArrayToBST(nums: IntArray): TreeNode? {

        fun buildBST(left: Int, right: Int): TreeNode? {
            if (left > right) return null
            val mid = left + ceil((right - left) / 2.0).toInt()

            val node = TreeNode(nums[mid])
            node.left = buildBST(left, mid - 1)
            node.right = buildBST(mid + 1, right)
            return node
        }

        return buildBST(0, nums.lastIndex)
    }
}
