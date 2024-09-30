package src.trees.problems

import src.core.Problem
import src.core.TestCase
import src.trees.TreeNode
import src.trees.buildTreeNode

class SearchInBST : Problem<Pair<TreeNode?, Int>, TreeNode?>() {
    
    companion object {
        @JvmStatic
        fun main(args: Array<String>) = SearchInBST().run()
    }
    
    override fun getTestCases(): Array<TestCase<Pair<TreeNode?, Int>, TreeNode?>> = arrayOf(
        TestCase(
            input = buildTreeNode(input = "4,2,7,1,3") to 2,
            output = buildTreeNode(input = "2,1,3")
        ),
        TestCase(
            input = buildTreeNode(input = "4,2,7,1,3") to 5,
            output = null
        )
    )
    
    override fun solve(testCaseInput: Pair<TreeNode?, Int>): TreeNode? {
        return searchBST(testCaseInput.first, testCaseInput.second)
    }

    private fun searchBST(root: TreeNode?, `val`: Int): TreeNode? {
        if (root == null) return null
        if (root.`val` == `val`) return root

        return if (`val` < root.`val`) searchBST(root.left, `val`) else searchBST(root.right, `val`)
    }
}
