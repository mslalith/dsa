package src.trees.problems

import src.core.Problem
import src.core.TestCase
import src.trees.TreeNode
import src.trees.buildTreeNode
import kotlin.math.max

class CountGoodNodesInBinaryTree : Problem<TreeNode?, Int>() {
    
    companion object {
        @JvmStatic
        fun main(args: Array<String>) = CountGoodNodesInBinaryTree().run()
    }
    
    override fun getTestCases(): Array<TestCase<TreeNode?, Int>> = arrayOf(
        TestCase(
            input = buildTreeNode(input = "3,1,4,3,null,1,5"),
            output = 4
        ),
        TestCase(
            input = buildTreeNode(input = "3,3,null,4,2"),
            output = 3
        ),
        TestCase(
            input = buildTreeNode(input = "1"),
            output = 1
        )
    )
    
    override fun solve(testCaseInput: TreeNode?): Int {
        return goodNodes(testCaseInput)
    }

    private fun goodNodes(root: TreeNode?): Int {
        if (root == null) return 0
        return goodNodes(root, root.`val`)
    }

    private fun goodNodes(root: TreeNode?, maxSeen: Int): Int {
        if (root == null) return 0

        val match = if (root.`val` >= maxSeen) 1 else 0
        return match + goodNodes(root.left, max(maxSeen, root.`val`)) + goodNodes(root.right, max(maxSeen, root.`val`))
    }
}
