package dev.mslalith.trees.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.trees.TreeNode
import dev.mslalith.trees.buildTreeNode

class FindElementsInContaminatedBinaryTree : TestCaseProblem<Pair<TreeNode?, List<Int>>, List<Boolean>>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = FindElementsInContaminatedBinaryTree().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<Pair<TreeNode?, List<Int>>, List<Boolean>>> = arrayOf(
        TestCase(
            input = buildTreeNode(input = "-1,null,-1") to listOf(1, 2),
            output = listOf(false, true)
        ),
        TestCase(
            input = buildTreeNode(input = "-1,-1,-1,-1,-1") to listOf(1, 3, 5),
            output = listOf(true, true, false)
        ),
        TestCase(
            input = buildTreeNode(input = "-1,null,-1,-1,null,-1") to listOf(2, 3, 4, 5),
            output = listOf(true, false, false, true)
        )
    )

    override fun solve(testCaseInput: Pair<TreeNode?, List<Int>>): List<Boolean> {
        val findElements = FindElements(testCaseInput.first)
        return testCaseInput.second.map { findElements.find(it) }
    }
}

private class FindElements(root: TreeNode?) {
    private val seen = hashSetOf<Int>()

    init {
        dfs(root, 0)
    }

    fun find(target: Int): Boolean {
        return target in seen
    }

    private fun dfs(node: TreeNode?, num: Int) {
        if (node == null) return
        seen += num

        dfs(node.left, (num * 2) + 1)
        dfs(node.right, (num * 2) + 2)
    }
}

private class FindElementsNaive(
    private val root: TreeNode?
) {

    fun find(target: Int): Boolean {
        if (root == null) return false
        root.`val` = 0
        return find(root, target)
    }

    private fun find(node: TreeNode?, target: Int): Boolean {
        if (node == null) return false
        if (node.`val` == target) return true

        node.left?.`val` = (node.`val` * 2) + 1
        node.right?.`val` = (node.`val` * 2) + 2

        return find(node.left, target) || find(node.right, target)
    }
}
