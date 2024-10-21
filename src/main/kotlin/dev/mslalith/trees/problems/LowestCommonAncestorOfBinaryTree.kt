package dev.mslalith.trees.problems

import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.core.TestCase
import dev.mslalith.trees.TreeNode
import dev.mslalith.trees.buildTreeNode

class LowestCommonAncestorOfBinaryTree : TestCaseProblem<LowestCommonAncestorOfBinaryTreeParams, TreeNode?>() {
    
    companion object {
        @JvmStatic
        fun main(args: Array<String>) = LowestCommonAncestorOfBinaryTree().runAll()
    }
    
    override fun getTestCases(): Array<TestCase<LowestCommonAncestorOfBinaryTreeParams, TreeNode?>> = arrayOf(
        TestCase(
            input = LowestCommonAncestorOfBinaryTreeParams(
                root = buildTreeNode(input = "3,5,1,6,2,0,8,null,null,7,4"),
                p = buildTreeNode(input = "5"),
                q = buildTreeNode(input = "1")
            ),
            output = buildTreeNode(input = "3")
        ),
        TestCase(
            input = LowestCommonAncestorOfBinaryTreeParams(
                root = buildTreeNode(input = "3,5,1,6,2,0,8,null,null,7,4"),
                p = buildTreeNode(input = "5"),
                q = buildTreeNode(input = "4")
            ),
            output = buildTreeNode(input = "5")
        ),
        TestCase(
            input = LowestCommonAncestorOfBinaryTreeParams(
                root = buildTreeNode(input = "1,2"),
                p = buildTreeNode(input = "1"),
                q = buildTreeNode(input = "2")
            ),
            output = buildTreeNode(input = "1")
        )
    )

    override fun isTestPassed(actual: TreeNode?, expected: TreeNode?): Boolean {
        if (actual == null && expected == null) return true
        if (actual == null || expected == null) return false

        return actual.`val` == expected.`val`
    }
    
    override fun solve(testCaseInput: LowestCommonAncestorOfBinaryTreeParams): TreeNode? {
        return lowestCommonAncestor(testCaseInput.root, testCaseInput.p, testCaseInput.q)
    }

    private fun lowestCommonAncestor(root: TreeNode?, p: TreeNode?, q: TreeNode?): TreeNode? {
        if (root == null) return null

        val parentMap = mutableMapOf<Int, TreeNode>()
        findParents(root, root, parentMap)

        val seen = hashSetOf<Int?>()
        var curr = p
        while (curr?.`val` !in seen) {
            seen.add(curr?.`val`)
            curr = parentMap[curr?.`val`]
        }

        curr = q
        while (curr?.`val` !in seen) {
            curr = parentMap[curr?.`val`]
        }

        return curr
    }

    private fun findParents(parent: TreeNode, node: TreeNode?, parentMap: MutableMap<Int, TreeNode>) {
        if (node == null) return
        parentMap[node.`val`] = parent
        findParents(node, node.left, parentMap)
        findParents(node, node.right, parentMap)
    }
}

data class LowestCommonAncestorOfBinaryTreeParams(
    val root: TreeNode?,
    val p: TreeNode?,
    val q: TreeNode?
) {
    override fun toString(): String {
        return """
            
            root: $root
            p: $p
            q: $q
        """.trimIndent()
    }
}
