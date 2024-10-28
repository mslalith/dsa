package dev.mslalith.array.problems

import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.core.TestCase

class NumberOfIslands : TestCaseProblem<Array<CharArray>, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = NumberOfIslands().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<Array<CharArray>, Int>> = arrayOf(
        TestCase(
            input = arrayOf(
                charArrayOf('1', '1', '1', '1', '0'),
                charArrayOf('1', '1', '0', '1', '0'),
                charArrayOf('1', '1', '0', '0', '0'),
                charArrayOf('0', '0', '0', '0', '0'),
            ),
            output = 1
        ),
        TestCase(
            input = arrayOf(
                charArrayOf('1', '1', '0', '0', '0'),
                charArrayOf('1', '1', '0', '0', '0'),
                charArrayOf('0', '0', '1', '0', '0'),
                charArrayOf('0', '0', '0', '1', '1')
            ),
            output = 3
        )
    )

    override fun solve(testCaseInput: Array<CharArray>): Int {
        return numIslands(testCaseInput)
    }

    private fun numIslands(grid: Array<CharArray>): Int {
        val m = grid.size
        val n = grid[0].size

        var islandCount = 0

        fun markVisited(
            row: Int,
            column: Int
        ) {
            if (row < 0 || row >= m) return
            if (column < 0 || column >= n) return
            if (grid[row][column] == '0') return

            grid[row][column] = '0'
            markVisited(row + 1, column)
            markVisited(row - 1, column)
            markVisited(row, column + 1)
            markVisited(row, column - 1)
        }

        for (i in 0 until m) {
            for (j in 0 until n) {
                if (grid[i][j] != '0') {
                    islandCount++
                    markVisited(i, j)
                }
            }
        }

        return islandCount
    }
}
