package dev.mslalith.dp.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.stacks.problems.LargestRectangleInHistogram
import kotlin.math.max

class MaximalRectangle : TestCaseProblem<Array<CharArray>, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = MaximalRectangle().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<Array<CharArray>, Int>> = arrayOf(
        TestCase(
            input = arrayOf(
                charArrayOf('1', '0', '1', '0', '0'),
                charArrayOf('1', '0', '1', '1', '1'),
                charArrayOf('1', '1', '1', '1', '1'),
                charArrayOf('1', '0', '0', '1', '0')
            ),
            output = 6
        ),
        TestCase(
            input = arrayOf(
                charArrayOf('0')
            ),
            output = 0
        ),
        TestCase(
            input = arrayOf(
                charArrayOf('1')
            ),
            output = 1
        )
    )

    override fun solve(testCaseInput: Array<CharArray>): Int {
        return maximalRectangle(testCaseInput)
    }

    private fun maximalRectangle(matrix: Array<CharArray>): Int {
        val m = matrix.size
        val n = matrix[0].size

        val heights = IntArray(n)
        var maxi = 0

        for (i in 0 until m) {
            for (j in 0 until n) {
                heights[j] = if (matrix[i][j] == '1') heights[j] + 1 else 0
            }
            val area = LargestRectangleInHistogram().solve(heights)
            maxi = max(maxi, area)
        }

        return maxi
    }
}
