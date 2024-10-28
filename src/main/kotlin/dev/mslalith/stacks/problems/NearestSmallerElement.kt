package dev.mslalith.stacks.problems

import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.core.TestCase
import java.util.*

class NearestSmallerElement : TestCaseProblem<IntArray, IntArray>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = NearestSmallerElement().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<IntArray, IntArray>> = arrayOf(
        TestCase(
            input = intArrayOf(4, 5, 2, 10, 8),
            output = intArrayOf(-1, 4, -1, 2, 2)
        ),
        TestCase(
            input = intArrayOf(3, 2, 1),
            output = intArrayOf(-1, -1, -1)
        ),
        TestCase(
            input = intArrayOf(34, 35, 27, 42, 5, 28, 39, 20, 28),
            output = intArrayOf(-1, 34, -1, 27, -1, 5, 28, 5, 20)
        ),
        TestCase(
            input = intArrayOf(39, 27, 11, 4, 24, 32, 32, 1),
            output = intArrayOf(-1, -1, -1, -1, 4, 24, 24, -1)
        )
    )

    override fun solve(testCaseInput: IntArray): IntArray {
        return prevSmaller(testCaseInput)
    }

    private fun prevSmaller(array: IntArray): IntArray {
        val stack = Stack<Int>()
        val result = IntArray(array.size)
        stack.push(array[0])
        result[0] = -1
        for (i in 1 until array.size) {
            while (!stack.empty() && stack.peek() >= array[i]) stack.pop()
            result[i] = if (stack.empty()) -1 else stack.peek()
            stack.push(array[i])
        }
        return result
    }
}
