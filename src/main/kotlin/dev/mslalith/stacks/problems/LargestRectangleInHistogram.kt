package dev.mslalith.stacks.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import java.util.*
import kotlin.math.max

class LargestRectangleInHistogram : TestCaseProblem<IntArray, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = LargestRectangleInHistogram().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<IntArray, Int>> = arrayOf(
        TestCase(
            input = intArrayOf(2, 1, 5, 6, 2, 3),
            output = 10
        ),
        TestCase(
            input = intArrayOf(2, 4),
            output = 4
        )
    )

    override fun solve(testCaseInput: IntArray): Int {
        return largestRectangleArea(testCaseInput)
    }

    private fun largestRectangleArea(heights: IntArray): Int {
        val n = heights.size

        val stack = Stack<Int>()
        val leftSmall = IntArray(n)
        val rightSmall = IntArray(n)

        for (i in 0 until n) {
            if (stack.isEmpty()) leftSmall[i] = 0 else {
                while (stack.isNotEmpty() && heights[stack.peek()] >= heights[i]) stack.pop()
                leftSmall[i] = if (stack.isEmpty()) 0 else stack.peek() + 1
            }
            stack.push(i)
        }

        while (stack.isNotEmpty()) stack.pop()

        for (i in (n - 1) downTo 0) {
            if (stack.isEmpty()) rightSmall[i] = n - 1 else {
                while (stack.isNotEmpty() && heights[stack.peek()] >= heights[i]) stack.pop()
                rightSmall[i] = if (stack.isEmpty()) n - 1 else stack.peek() - 1
            }
            stack.push(i)
        }

        var maxi = 0
        for (i in 0 until n) {
            val area = (rightSmall[i] - leftSmall[i] + 1) * heights[i]
            maxi = max(maxi, area)
        }

        return maxi
    }

    private fun largestRectangleAreaNaive(heights: IntArray): Int {
        val n = heights.size

        var maxi = heights[0]
        for (i in 0 until n) {
            var left = i
            while (left - 1 >= 0 && heights[left - 1] >= heights[i]) left--

            var right = i
            while (right + 1 < n && heights[right + 1] >= heights[i]) right++

            val width = right - left + 1
            val area = width * heights[i]
            maxi = max(maxi, area)
        }

        return maxi
    }
}
