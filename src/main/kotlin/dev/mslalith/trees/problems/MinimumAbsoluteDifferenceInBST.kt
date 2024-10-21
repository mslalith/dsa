package dev.mslalith.trees.problems

import dev.mslalith.core.Problem
import dev.mslalith.core.TestCase
import dev.mslalith.trees.TreeNode
import dev.mslalith.trees.buildTreeNode
import kotlin.math.min

class MinimumAbsoluteDifferenceInBST : Problem<TreeNode?, Int>() {
    
    companion object {
        @JvmStatic
        fun main(args: Array<String>) = MinimumAbsoluteDifferenceInBST().run()
    }
    
    override fun getTestCases(): Array<TestCase<TreeNode?, Int>> = arrayOf(
        TestCase(
            input = buildTreeNode(input = "4,2,6,1,3"),
            output = 1
        ),
        TestCase(
            input = buildTreeNode(input = "1,0,48,null,null,12,49"),
            output = 1
        ),
        TestCase(
            input = buildTreeNode(input = "236,104,701,null,227,null,911"),
            output = 9
        ),
        TestCase(
            input = buildTreeNode(input = "726,296,785,null,543,null,822"),
            output = 37
        )
    )

    override fun solve(testCaseInput: TreeNode?): Int {
        return getMinimumDifference(testCaseInput)
    }

    private fun getMinimumDifference(root: TreeNode?): Int {
        if (root == null) return 0

        var minimum = Int.MAX_VALUE
        var prev: TreeNode? = null

        fun inorder(node: TreeNode?) {
            if (node == null) return

            inorder(node.left)

            if (prev != null) minimum = min(minimum, node.`val` - prev!!.`val`)
            prev = node

            inorder(node.right)
        }

        inorder(root)

        return minimum
    }
}
