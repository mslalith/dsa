package dev.mslalith.trees.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.trees.TreeNode
import dev.mslalith.trees.buildTreeNode

class SumRootToLeafNumbers : TestCaseProblem<TreeNode?, Int>() {
    
    companion object {
        @JvmStatic
        fun main(args: Array<String>) = SumRootToLeafNumbers().runForConsole()
    }
    
    override fun getTestCases(): Array<TestCase<TreeNode?, Int>> = arrayOf(
        TestCase(
            input = buildTreeNode(input = "1,2,3"),
            output = 25
        ),
        TestCase(
            input = buildTreeNode(input = "4,9,0,5,1"),
            output = 1026
        )
    )
    
    override fun solve(testCaseInput: TreeNode?): Int {
        return sumNumbers(testCaseInput)
    }

    private fun sumNumbers(root: TreeNode?): Int {
        var totalSum = 0
        var currSum = 0

        fun sumNumberFrom(root: TreeNode?) {
            if (root == null) return

            currSum = (currSum * 10) + root.`val`

            if (root.left == null && root.right == null) {
                totalSum += currSum
            } else {
                sumNumberFrom(root.left)
                sumNumberFrom(root.right)
            }

            currSum -= root.`val`
            currSum /= 10
        }

        sumNumberFrom(root)

        return totalSum
    }
}
