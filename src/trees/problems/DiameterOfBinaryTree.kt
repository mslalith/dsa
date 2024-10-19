package src.trees.problems

import src.core.Problem
import src.core.TestCase
import src.trees.TreeNode
import src.trees.buildTreeNode
import kotlin.math.max

class DiameterOfBinaryTree : Problem<TreeNode?, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = DiameterOfBinaryTree().run()
    }

    override fun getTestCases(): Array<TestCase<TreeNode?, Int>> = arrayOf(
        TestCase(
            input = buildTreeNode("1,2,3,4,5"),
            output = 3
        ),
        TestCase(
            input = buildTreeNode("1,2"),
            output = 1
        ),
        TestCase(
            input = buildTreeNode("4,-7,-3,null,null,-9,-3,9,-7,-4,null,6,null,-6,-6,null,null,0,6,5,null,9,null,null,-1,-4,null,null,null,-2"),
            output = 8
        )
    )

    override fun solve(testCaseInput: TreeNode?): Int {
        return diameterOfBinaryTree(testCaseInput)
    }

    private fun diameterOfBinaryTree(root: TreeNode?): Int {
        return diameterAndHeightOfBinaryTree(root).diameter - 1
    }

    private fun diameterAndHeightOfBinaryTree(root: TreeNode?): TreeInfo  {
        if (root == null) return TreeInfo(
            diameter = 0,
            height = 0
        )

        val left = diameterAndHeightOfBinaryTree(root.left)
        val right = diameterAndHeightOfBinaryTree(root.right)
        val height = max(left.height, right.height) + 1
        val diameter = max(max(left.diameter, right.diameter), left.height + right.height + 1)
        return TreeInfo(diameter, height)
    }

    data class TreeInfo(
        val diameter: Int,
        val height: Int
    )
}