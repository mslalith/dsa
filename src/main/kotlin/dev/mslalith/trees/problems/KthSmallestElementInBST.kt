package dev.mslalith.trees.problems

import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.core.TestCase
import dev.mslalith.trees.TreeNode
import dev.mslalith.trees.buildTreeNode
import java.util.*

class KthSmallestElementInBST : TestCaseProblem<Pair<TreeNode?, Int>, Int>() {
    
    companion object {
        @JvmStatic
        fun main(args: Array<String>) = KthSmallestElementInBST().runForConsole()
    }
    
    override fun getTestCases(): Array<TestCase<Pair<TreeNode?, Int>, Int>> = arrayOf(
        TestCase(
            input = buildTreeNode(input = "3,1,4,null,2") to 1,
            output = 1
        ),
        TestCase(
            input = buildTreeNode(input = "5,3,6,2,4,null,null,1") to 3,
            output = 3
        )
    )
    
    override fun solve(testCaseInput: Pair<TreeNode?, Int>): Int {
        return kthSmallest(testCaseInput.first, testCaseInput.second)
    }

    private fun kthSmallest(root: TreeNode?, k: Int): Int {
        var count = 0
        var value = 0

        fun inorder(node: TreeNode?) {
            if (node == null) return

            inorder(node.left)
            if (++count == k) {
                value = node.`val`
                return
            }
            inorder(node.right)
        }

        inorder(root)

        return value
    }

    private fun kthSmallestNaive(root: TreeNode?, k: Int): Int {
        val queue = PriorityQueue<Int>(k)

        fun inorder(node: TreeNode?) {
            if (node == null) return

            inorder(node.left)
            queue.add(node.`val`)
            inorder(node.right)
        }

        inorder(root)

        return queue.elementAt(k - 1)
    }
}
