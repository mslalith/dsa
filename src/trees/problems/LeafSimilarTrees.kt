package src.trees.problems

import src.core.Problem
import src.core.TestCase
import src.trees.TreeNode
import src.trees.buildTreeNode

class LeafSimilarTrees : Problem<Pair<TreeNode?, TreeNode?>, Boolean>() {
    
    companion object {
        @JvmStatic
        fun main(args: Array<String>) = LeafSimilarTrees().run()
    }
    
    override fun getTestCases(): Array<TestCase<Pair<TreeNode?, TreeNode?>, Boolean>> = arrayOf(
        TestCase(
            input = buildTreeNode(input = "3,5,1,6,2,9,8,null,null,7,4") to buildTreeNode(input = "3,5,1,6,7,4,2,null,null,null,null,null,null,9,8"),
            output = true
        ),
        TestCase(
            input = buildTreeNode(input = "1,2,3") to buildTreeNode(input = "1,3,2"),
            output = false
        ),
        TestCase(
            input = buildTreeNode(input = "3,5,1,6,2,9,8,null,null,7,14") to buildTreeNode(input = "3,5,1,6,71,4,2,null,null,null,null,null,null,9,8"),
            output = false
        )
    )
    
    override fun solve(testCaseInput: Pair<TreeNode?, TreeNode?>): Boolean {
        return leafSimilar(testCaseInput.first, testCaseInput.second)
    }

    private fun leafSimilar(root1: TreeNode?, root2: TreeNode?): Boolean {
        if (root1 == null && root2 == null) return false
        val root1LeafNodes = arrayListOf<Int>()
        val root2LeafNodes = arrayListOf<Int>()
        allLeafValues(root1, root1LeafNodes)
        allLeafValues(root2, root2LeafNodes)
        return root1LeafNodes == root2LeafNodes
    }

    private fun allLeafValues(root: TreeNode?, list: ArrayList<Int>) {
        if (root == null) return
        if (root.left == null && root.right == null) {
            list.add(root.`val`)
            return
        }
        allLeafValues(root.left, list)
        allLeafValues(root.right, list)
    }
}