package dev.mslalith.trees.problems

import dev.mslalith.core.Problem
import dev.mslalith.core.TestCase
import dev.mslalith.trees.TreeNode
import dev.mslalith.trees.buildTreeNode

class InvertBinaryTree : Problem<TreeNode?, TreeNode?>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = InvertBinaryTree().run()
    }

    override fun getTestCases(): Array<TestCase<TreeNode?, TreeNode?>> = arrayOf(
        TestCase(
            input = buildTreeNode("4,2,7,1,3,6,9"),
            output = buildTreeNode("4,7,2,9,6,3,1")
        )
    )

    override fun solve(testCaseInput: TreeNode?): TreeNode? {
        return invertTree(testCaseInput)
    }

    private fun invertTree(root: TreeNode?): TreeNode? {
        if (root == null) return null

        val node = root.left
        root.left = root.right
        root.right = node

        invertTree(root.left)
        invertTree(root.right)

        return root
    }
}
