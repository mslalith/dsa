package dev.mslalith.trees.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.trees.TreeNode
import dev.mslalith.trees.buildTreeNode
import dev.mslalith.trees.problems.BinarySearchTreeIteratorType.*
import java.util.*

class BinarySearchTreeIterator : TestCaseProblem<BinarySearchTreeIteratorParams, List<Any>>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = BinarySearchTreeIterator().runAll()
    }

    override fun getTestCases(): Array<TestCase<BinarySearchTreeIteratorParams, List<Any>>> = arrayOf(
        TestCase(
            input = BinarySearchTreeIteratorParams(
                root = buildTreeNode(input = "7,3,15,null,null,9,20"),
                types = listOf(Next, Next, HasNext, Next, HasNext, Next, HasNext, Next, HasNext)
            ),
            output = listOf(3, 7, true, 9, true, 15, true, 20, false)
        ),
        TestCase(
            input = BinarySearchTreeIteratorParams(
                root = buildTreeNode(input = "3,1,4,null,2"),
                types = listOf(HasNext, Next, HasNext, Next, HasNext, Next, HasNext, Next, HasNext)
            ),
            output = listOf(true, 1, true, 2, true, 3, true, 4, false)
        )
    )
        .let { arrayOf(it.last() as TestCase<BinarySearchTreeIteratorParams, List<Any>>) }

    override fun solve(testCaseInput: BinarySearchTreeIteratorParams): List<Any> = buildList {
        val (root, types) = testCaseInput
        val iterator = BSTIterator(root)
        types.forEach {
            when (it) {
                HasNext -> add(iterator.hasNext())
                Next -> add(iterator.next())
            }
        }
    }
}

data class BinarySearchTreeIteratorParams(
    val root: TreeNode?,
    val types: List<BinarySearchTreeIteratorType>
)

sealed class BinarySearchTreeIteratorType {
    data object HasNext : BinarySearchTreeIteratorType()
    data object Next : BinarySearchTreeIteratorType()
}

class BSTIterator(root: TreeNode?) {

    private val stack = Stack<TreeNode>()

    init {
        populateStack(root)
    }

    fun next(): Int {
        val curr = stack.pop()
        populateStack(curr!!.right)
        return curr.`val`
    }

    fun hasNext(): Boolean {
        return stack.isNotEmpty()
    }

    private fun populateStack(node: TreeNode?) {
        var curr = node
        while (curr != null) {
            stack.push(curr)
            curr = curr.left
        }
    }
}
