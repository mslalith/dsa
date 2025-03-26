package dev.mslalith.array.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import kotlin.math.abs

class MinimumOperationsToMakeAUniValueGrid : TestCaseProblem<Pair<Array<IntArray>, Int>, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = MinimumOperationsToMakeAUniValueGrid().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<Pair<Array<IntArray>, Int>, Int>> = arrayOf(
        TestCase(
            input = arrayOf(
                intArrayOf(2, 4),
                intArrayOf(6, 8)
            ) to 2,
            output = 4
        ),
        TestCase(
            input = arrayOf(
                intArrayOf(1, 5),
                intArrayOf(2, 3)
            ) to 1,
            output = 5
        ),
        TestCase(
            input = arrayOf(
                intArrayOf(1, 2),
                intArrayOf(3, 4)
            ) to 2,
            output = -1
        ),
        TestCase(
            input = arrayOf(
                intArrayOf(146)
            ) to 86,
            output = 0
        )
    )

    override fun solve(testCaseInput: Pair<Array<IntArray>, Int>): Int {
        return minOperations(testCaseInput.first, testCaseInput.second)
    }

    private fun minOperations(grid: Array<IntArray>, x: Int): Int {
        val values = mutableListOf<Int>()
        for (row in grid) {
            for (num in row) {
                if (abs(num - grid[0][0]) % x != 0) return -1
                values += num
            }
        }

        values.sort()
        val median = values[values.size / 2]
        return values.sumOf { abs(it - median) / x }
    }
}
