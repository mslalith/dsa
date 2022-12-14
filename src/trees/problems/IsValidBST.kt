package src.trees.problems

import src.core.Problem
import src.core.TestCase
import src.trees.TreeNode
import src.trees.buildTreeNode

class IsValidBST : Problem<String, Boolean>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = IsValidBST().run()
    }

    override fun getTestCases(): Array<TestCase<String, Boolean>> = arrayOf(
        TestCase(input = "5,1,4,null,null,3,6", output = false),
        TestCase(input = "2,2,2", output = false),
        TestCase(input = "5,4,6,null,null,3,7", output = false)
    )

    override fun solve(testCaseInput: String): Boolean {
        return isValidBST(buildTreeNode(testCaseInput), Long.MIN_VALUE, Long.MAX_VALUE)
    }

    private fun isValidBST(root: TreeNode?, min: Long, max: Long): Boolean {
        if (root == null) return true
        if (root.`val` >= max || root.`val` <= min) return false

        val value = root.`val`.toLong()
        return isValidBST(root.left, min, value) && isValidBST(root.right, value, max)
    }
}
