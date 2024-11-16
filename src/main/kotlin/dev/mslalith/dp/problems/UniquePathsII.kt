package dev.mslalith.dp.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem

class UniquePathsII : TestCaseProblem<Array<IntArray>, Int>() {
    
    companion object {
        @JvmStatic
        fun main(args: Array<String>) = UniquePathsII().runForConsole()
    }
    
    override fun getTestCases(): Array<TestCase<Array<IntArray>, Int>> = arrayOf(
        TestCase(
            input = arrayOf(
                intArrayOf(0, 0, 0),
                intArrayOf(0, 1, 0),
                intArrayOf(0, 0, 0)
            ),
            output = 2
        ),
        TestCase(
            input = arrayOf(
                intArrayOf(0, 1),
                intArrayOf(0, 0)
            ),
            output = 1
        ),
        TestCase(
            input = arrayOf(
                intArrayOf(0, 0),
                intArrayOf(0, 1)
            ),
            output = 0
        )
    )
    
    override fun solve(testCaseInput: Array<IntArray>): Int {
        return uniquePathsWithObstacles(testCaseInput)
    }

    private fun uniquePathsWithObstacles(obstacleGrid: Array<IntArray>): Int {
        val m = obstacleGrid.size
        val n = obstacleGrid[0].size

        if (obstacleGrid[m - 1][n - 1] == 1) return 0

        var prev = IntArray(n)
        val curr = IntArray(n)

        for (i in 0 until m) {
            for (j in 0 until n) {
                when {
                    obstacleGrid[i][j] == 1 -> curr[j] = 0
                    i == 0 && j == 0 -> curr[j] = 1
                    else -> {
                        val top = if (i > 0) prev[j] else 0
                        val left = if (j > 0) curr[j - 1] else 0
                        curr[j] = top + left
                    }
                }
            }
            prev = curr
        }

        return prev[n - 1]
    }

    private fun uniquePathsWithObstaclesDp(obstacleGrid: Array<IntArray>): Int {
        val m = obstacleGrid.size
        val n = obstacleGrid[0].size

        if (obstacleGrid[m - 1][n - 1] == 1) return 0

        val dp = Array(m) { IntArray(n) }

        for (i in 0 until m) {
            for (j in 0 until n) {
                when {
                    obstacleGrid[i][j] == 1 -> dp[i][j] = 0
                    i == 0 && j == 0 -> dp[i][j] = 1
                    else -> {
                        val top = if (i > 0) dp[i - 1][j] else 0
                        val left = if (j > 0) dp[i][j - 1] else 0
                        dp[i][j] = top + left
                    }
                }
            }
        }

        return dp[m - 1][n - 1]
    }

    private fun uniquePathsWithObstaclesRecursive(obstacleGrid: Array<IntArray>): Int {
        val m = obstacleGrid.size
        val n = obstacleGrid[0].size

        if (obstacleGrid[m - 1][n - 1] == 1) return 0

        val dp = Array(m) { IntArray(n) { -1 } }

        fun findUniquePaths(i: Int, j: Int): Int {
            if (i < 0 || j < 0) return 0
            if (obstacleGrid[i][j] == 1) return 0
            if (i == 0 && j == 0) return 1
            if (dp[i][j] != -1) return dp[i][j]

            val top = findUniquePaths(i, j - 1)
            val left = findUniquePaths(i - 1, j)

            dp[i][j] = top + left
            return dp[i][j]
        }

        return findUniquePaths(m - 1, n - 1)
    }
}
