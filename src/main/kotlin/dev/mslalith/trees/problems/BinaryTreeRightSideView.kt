package dev.mslalith.trees.problems

import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.core.TestCase
import dev.mslalith.trees.TreeNode
import dev.mslalith.trees.buildTreeNode

class BinaryTreeRightSideView : TestCaseProblem<TreeNode?, List<Int>>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = BinaryTreeRightSideView().runAll()
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
