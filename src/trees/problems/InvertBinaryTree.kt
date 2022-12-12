package src.trees.problems

import src.core.Problem
import src.core.TestCase
import src.trees.TreeNode
import src.trees.buildTreeNode

object InvertBinaryTree : Problem<TreeNode?, TreeNode?>() {

    @JvmStatic
    fun main(args: Array<String>) = run()

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
