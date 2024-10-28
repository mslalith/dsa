package dev.mslalith.trees.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.trees.TreeNode
import dev.mslalith.trees.buildTreeNode
import dev.mslalith.utils.createClone

class FlattenBinaryTreeToLinkedList : TestCaseProblem<TreeNode?, TreeNode?>() {
    
    companion object {
        @JvmStatic
        fun main(args: Array<String>) = FlattenBinaryTreeToLinkedList().runForConsole()
    }
    
    override fun getTestCases(): Array<TestCase<TreeNode?, TreeNode?>> = arrayOf(
        TestCase(
            input = buildTreeNode(input = "1,2,5,3,4,null,6"),
            output = buildTreeNode(input = "1,null,2,null,3,null,4,null,5,null,6")
        ),
        TestCase(
            input = buildTreeNode(input = ""),
            output = buildTreeNode(input = "")
        ),
        TestCase(
            input = buildTreeNode(input = "0"),
            output = buildTreeNode(input = "0")
        )
    )
    
    override fun solve(testCaseInput: TreeNode?): TreeNode? {
        val root = testCaseInput?.createClone()
        flatten(root)
        return root
    }

    private fun flatten(root: TreeNode?) {
        if (root == null) return

        val list = mutableListOf<Int>()

        fun preOrderList(node: TreeNode?) {
            if (node == null) return

            list.add(node.`val`)
            preOrderList(node.left)
            preOrderList(node.right)
        }

        preOrderList(root)

        if (list.isEmpty()) return

        root.left = null
        var curr = root

        for (i in 1 until list.size) {
            curr?.right = TreeNode(list[i])
            curr = curr?.right
        }
    }
}
