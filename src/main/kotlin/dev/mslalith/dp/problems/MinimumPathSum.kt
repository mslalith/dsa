package dev.mslalith.dp.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import kotlin.math.min

class MinimumPathSum : TestCaseProblem<Array<IntArray>, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = MinimumPathSum().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<Array<IntArray>, Int>> = arrayOf(
        TestCase(
            input = arrayOf(
                intArrayOf(1, 3, 1),
                intArrayOf(1, 5, 1),
                intArrayOf(4, 2, 1)
            ),
            output = 7
        ),
        TestCase(
            input = arrayOf(
                intArrayOf(1, 2, 3),
                intArrayOf(4, 5, 6)
            ),
            output = 12
        ),
        TestCase(
            input = arrayOf(
                intArrayOf(3, 8, 6, 0, 5, 9, 9, 6, 3, 4, 0, 5, 7, 3, 9, 3),
                intArrayOf(0, 9, 2, 5, 5, 4, 9, 1, 4, 6, 9, 5, 6, 7, 3, 2),
                intArrayOf(8, 2, 2, 3, 3, 3, 1, 6, 9, 1, 1, 6, 6, 2, 1, 9),
                intArrayOf(1, 3, 6, 9, 9, 5, 0, 3, 4, 9, 1, 0, 9, 6, 2, 7),
                intArrayOf(8, 6, 2, 2, 1, 3, 0, 0, 7, 2, 7, 5, 4, 8, 4, 8),
                intArrayOf(4, 1, 9, 5, 8, 9, 9, 2, 0, 2, 5, 1, 8, 7, 0, 9),
                intArrayOf(6, 2, 1, 7, 8, 1, 8, 5, 5, 7, 0, 2, 5, 7, 2, 1),
                intArrayOf(8, 1, 7, 6, 2, 8, 1, 2, 2, 6, 4, 0, 5, 4, 1, 3),
                intArrayOf(9, 2, 1, 7, 6, 1, 4, 3, 8, 6, 5, 5, 3, 9, 7, 3),
                intArrayOf(0, 6, 0, 2, 4, 3, 7, 6, 1, 3, 8, 6, 9, 0, 0, 8),
                intArrayOf(4, 3, 7, 2, 4, 3, 6, 4, 0, 3, 9, 5, 3, 6, 9, 3),
                intArrayOf(2, 1, 8, 8, 4, 5, 6, 5, 8, 7, 3, 7, 7, 5, 8, 3),
                intArrayOf(0, 7, 6, 6, 1, 2, 0, 3, 5, 0, 8, 0, 8, 7, 4, 3),
                intArrayOf(0, 4, 3, 4, 9, 0, 1, 9, 7, 7, 8, 6, 4, 6, 9, 5),
                intArrayOf(6, 5, 1, 9, 9, 2, 2, 7, 4, 2, 7, 2, 2, 3, 7, 2),
                intArrayOf(7, 1, 9, 6, 1, 2, 7, 0, 9, 6, 6, 4, 4, 5, 1, 0),
                intArrayOf(3, 4, 9, 2, 8, 3, 1, 2, 6, 9, 7, 0, 2, 4, 2, 0),
                intArrayOf(5, 1, 8, 8, 4, 6, 8, 5, 2, 4, 1, 6, 2, 2, 9, 7)
            ),
            output = 83
        )
    )

    override fun solve(testCaseInput: Array<IntArray>): Int {
        return minPathSum(testCaseInput)
    }

    private fun minPathSum(grid: Array<IntArray>): Int {
        val m = grid.size
        val n = grid[0].size

        val curr = IntArray(n)
        var prev = IntArray(n)
        prev[0] = grid[0][0]

        for (i in 1 until n) prev[i] = grid[0][i] + prev[i - 1]

        for (i in 1 until m) {
            curr[0] = grid[i][0] + prev[0]
            for (j in 1 until n) {
                curr[j] = grid[i][j] + min(prev[j], curr[j - 1])
            }
            prev = curr
        }

        return prev[n - 1]
    }

    private fun minPathSumDp(grid: Array<IntArray>): Int {
        val m = grid.size
        val n = grid[0].size

        val dp = Array(m) { IntArray(n) }
        dp[0][0] = grid[0][0]

        for (i in 1 until n) dp[0][i] = grid[0][i] + dp[0][i - 1]
        for (i in 1 until m) dp[i][0] = grid[i][0] + dp[i - 1][0]

        for (i in 1 until m) {
            for (j in 1 until n) {
                val top = grid[i][j] + dp[i - 1][j]
                val left = grid[i][j] + dp[i][j - 1]
                dp[i][j] = min(top, left)
            }
        }

        return dp[m - 1][n - 1]
    }

    private fun minPathSumRecursive(grid: Array<IntArray>): Int {
        val m = grid.size
        val n = grid[0].size

        fun findMinPathSum(x: Int, y: Int): Int {
            if (x < 0 || x >= m || y < 0 || y >= n) return Int.MAX_VALUE
            if (x == m - 1 && y == n - 1) return grid[x][y]

            return grid[x][y] + min(findMinPathSum(x + 1, y), findMinPathSum(x, y + 1))
        }

        return findMinPathSum(0, 0)
    }
}
