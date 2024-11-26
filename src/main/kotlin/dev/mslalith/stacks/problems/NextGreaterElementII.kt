package dev.mslalith.stacks.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import java.util.*

class NextGreaterElementII : TestCaseProblem<IntArray, IntArray>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = NextGreaterElementII().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<IntArray, IntArray>> = arrayOf(
        TestCase(
            input = intArrayOf(1, 2, 1),
            output = intArrayOf(2, -1, 2)
        ),
        TestCase(
            input = intArrayOf(1, 2, 3, 4, 3),
            output = intArrayOf(2, 3, 4, -1, 4)
        )
    )

    override fun solve(testCaseInput: IntArray): IntArray {
        return nextGreaterElements(testCaseInput)
    }

    private fun nextGreaterElements(nums: IntArray): IntArray {
        val n = nums.size
        val stack = Stack<Int>()
        val nge = IntArray(n)

        for (i in ((2 * n) - 1) downTo 0) {
            val ind = i % n
            val num = nums[ind]
            while (stack.isNotEmpty() && stack.peek() <= num) stack.pop()
            if (i < n) nge[i] = if (stack.isEmpty()) -1 else stack.peek()
            stack.push(num)
        }

        return nge
    }
}
