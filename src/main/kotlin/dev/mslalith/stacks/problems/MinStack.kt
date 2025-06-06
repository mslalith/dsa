package dev.mslalith.stacks.problems

import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.core.TestCase
import dev.mslalith.stacks.problems.MinStackInputType.GetMin
import dev.mslalith.stacks.problems.MinStackInputType.Pop
import dev.mslalith.stacks.problems.MinStackInputType.Push
import dev.mslalith.stacks.problems.MinStackInputType.Top
import kotlin.math.min

class MinStack : TestCaseProblem<List<MinStackInputType>, List<Int?>>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = MinStack().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<List<MinStackInputType>, List<Int?>>> = arrayOf(
        TestCase(
            input = listOf(
                Push(-2),
                Push(0),
                Push(-3),
                GetMin,
                Pop,
                Top,
                GetMin
            ),
            output = listOf(null, null, null, -3, null, 0, -2)
        )
    )

    override fun solve(testCaseInput: List<MinStackInputType>): List<Int?> {
        val result = mutableListOf<Int?>()
        val stack = MinStackImpl()

        testCaseInput.forEach {
            when (it) {
                GetMin -> result.add(stack.getMin())
                Top -> result.add(stack.top())
                Pop -> {
                    stack.pop()
                    result.add(null)
                }

                is Push -> {
                    stack.push(it.value)
                    result.add(null)
                }
            }
        }

        return result
    }
}

private class MinStackImpl {

    private data class Node(
        val value: Int,
        val min: Int,
        var next: Node? = null
    )

    private var head: Node? = null

    fun push(`val`: Int) {
        if (head == null) {
            head = Node(`val`, `val`)
        } else {
            val lastMin = head!!.min
            val newNode = Node(`val`, min(lastMin, `val`))
            newNode.next = head
            head = newNode
        }
    }

    fun pop() {
        head = head?.next
    }

    fun top(): Int {
        return head?.value ?: 0
    }

    fun getMin(): Int {
        return head?.min ?: 0
    }
}

sealed class MinStackInputType {
    data class Push(val value: Int) : MinStackInputType()
    data object Pop : MinStackInputType()
    data object Top : MinStackInputType()
    data object GetMin : MinStackInputType()
}
