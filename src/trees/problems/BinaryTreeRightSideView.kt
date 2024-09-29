package src.trees.problems

import src.core.Problem
import src.core.TestCase
import src.trees.TreeNode
import src.trees.buildTreeNode

class BinaryTreeRightSideView : Problem<TreeNode?, List<Int>>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = BinaryTreeRightSideView().run()
    }

    override fun getTestCases(): Array<TestCase<TreeNode?, List<Int>>> = arrayOf(
        TestCase(
            input = buildTreeNode(input = "1,2,3,null,5,null,4"),
            output = listOf(1, 3, 4)
        )
    )

    override fun solve(testCaseInput: TreeNode?): List<Int> {
        return rightSideView(testCaseInput)
    }

    private fun rightSideView(root: TreeNode?): List<Int> {
        if (root == null) return emptyList()

        val queue = ArrayDeque<TreeNode?>()
        queue.add(root)
        queue.add(null)

        val rightSideList = mutableListOf<Int>()
        var lastNode = root
        while (queue.isNotEmpty()) {
            val node = queue.removeFirst()
            if (node != null) {
                lastNode = node
                if (node.left != null) queue.add(node.left)
                if (node.right != null) queue.add(node.right)
            } else {
                if (lastNode != null) rightSideList.add(lastNode.`val`)
                if (queue.isNotEmpty()) queue.add(null)
            }
        }

        return rightSideList
    }
}
