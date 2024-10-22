package dev.mslalith.trees.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.trees.TreeNode
import dev.mslalith.trees.buildTreeNode

class PathSumII : TestCaseProblem<Pair<TreeNode?, Int>, List<List<Int>>>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = PathSumII().runAll()
    }

    override fun getTestCases(): Array<TestCase<Pair<TreeNode?, Int>, List<List<Int>>>> = arrayOf(
        TestCase(
            input = buildTreeNode(input = "5,4,8,11,null,13,4,7,2,null,null,5,1") to 22,
            output = listOf(
                listOf(5, 4, 11, 2),
                listOf(5, 8, 4, 5)
            )
        ),
        TestCase(
            input = buildTreeNode(input = "1,2,3") to 5,
            output = emptyList()
        )
    )

    override fun solve(testCaseInput: Pair<TreeNode?, Int>): List<List<Int>> {
        return pathSum(testCaseInput.first, testCaseInput.second)
    }

    private fun pathSum(root: TreeNode?, targetSum: Int): List<List<Int>> {
        val paths = mutableListOf<List<Int>>()
        val currentPath = mutableListOf<Int>()

        fun pathSumFrom(root: TreeNode?, targetSum: Int) {
            if (root == null) return

            currentPath.add(root.`val`)

            if (root.`val` == targetSum && root.left == null && root.right == null) {
                paths.add(currentPath.toList())
            } else {
                pathSumFrom(root.left, targetSum - root.`val`)
                pathSumFrom(root.right, targetSum - root.`val`)
            }

            currentPath.removeLast()
        }

        pathSumFrom(root, targetSum)

        return paths
    }

}
