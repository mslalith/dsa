package src.trees.problems

import src.core.Problem
import src.core.TestCase
import src.trees.TreeNode
import src.trees.buildTreeNode
import kotlin.math.max

class LongestZigZagPathInBinaryTree : Problem<TreeNode?, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = LongestZigZagPathInBinaryTree().run()
    }

    override fun getTestCases(): Array<TestCase<TreeNode?, Int>> = arrayOf(
        TestCase(
            input = buildTreeNode(input = "1,null,1,1,1,null,null,1,1,null,1,null,null,null,1"),
            output = 3
        ),
        TestCase(
            input = buildTreeNode(input = "1,1,1,null,1,null,null,1,1,null,1"),
            output = 4
        ),
        TestCase(
            input = buildTreeNode(input = "1"),
            output = 0
        )
    )

    override fun solve(testCaseInput: TreeNode?): Int {
        return longestZigZag(testCaseInput)
    }

    private fun longestZigZag(root: TreeNode?): Int {
        if (root == null) return 0

        return max(
            longestZigZag(root.left, true, 0),
            longestZigZag(root.right, false, 0)
        )
    }

    private fun longestZigZag(root: TreeNode?, isLeft: Boolean, depth: Int): Int {
        if (root == null) return depth

        return max(
            longestZigZag(root.left, true, if (isLeft) 0 else depth + 1),
            longestZigZag(root.right, false, if (isLeft) depth + 1 else 0)
        )
    }
}
