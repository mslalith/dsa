package dev.mslalith.array.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem

class FindMinimumAreaToCoverAllOnesI : TestCaseProblem<Array<IntArray>, Int>() {
    
    companion object {
        @JvmStatic
        fun main(args: Array<String>) = FindMinimumAreaToCoverAllOnesI().runForConsole()
    }
    
    override fun getTestCases(): Array<TestCase<Array<IntArray>, Int>> = arrayOf(
        TestCase(
            input = arrayOf(
                intArrayOf(0, 1, 0),
                intArrayOf(1, 0, 1)
            ),
            output = 6
        ),
        TestCase(
            input = arrayOf(
                intArrayOf(1, 0),
                intArrayOf(0, 0)
            ),
            output = 1
        )
    )
    
    override fun solve(testCaseInput: Array<IntArray>): Int {
        return minimumArea(testCaseInput)
    }

    private fun minimumArea(grid: Array<IntArray>): Int {
        var minRow = grid.size
        var maxRow = -1

        var minCol = grid[0].size
        var maxCol = -1

        for (i in 0..<grid.size) {
            for (j in 0..<grid[i].size) {
                if (grid[i][j] == 1) {
                    minRow = minOf(minRow, i)
                    maxRow = maxOf(maxRow, i)
                    minCol = minOf(minCol, j)
                    maxCol = maxOf(maxCol, j)
                }
            }
        }

        return (maxRow - minRow + 1) * (maxCol - minCol + 1)
    }
}
