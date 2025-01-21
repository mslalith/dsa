package dev.mslalith.array.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import kotlin.math.max
import kotlin.math.min

class GridGame : TestCaseProblem<Array<IntArray>, Long>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = GridGame().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<Array<IntArray>, Long>> = arrayOf(
        TestCase(
            input = arrayOf(
                intArrayOf(2, 5, 4),
                intArrayOf(1, 5, 1)
            ),
            output = 4
        ),
        TestCase(
            input = arrayOf(
                intArrayOf(3, 3, 1),
                intArrayOf(8, 5, 2)
            ),
            output = 4
        ),
        TestCase(
            input = arrayOf(
                intArrayOf(1, 3, 1, 15),
                intArrayOf(1, 3, 3, 1)
            ),
            output = 7
        )
    )

    override fun solve(testCaseInput: Array<IntArray>): Long {
        return gridGame(testCaseInput)
    }

    private fun gridGame(grid: Array<IntArray>): Long {
        var minResult = Long.MAX_VALUE
        var row1Sum = grid[0].sumOf { it.toLong() }
        var row2Sum = 0L

        for (i in grid[0].indices) {
            row1Sum -= grid[0][i]
            minResult = min(minResult, max(row1Sum, row2Sum))
            row2Sum += grid[1][i]
        }

        return minResult
    }
}
