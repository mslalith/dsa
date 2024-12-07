package dev.mslalith.dp.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem


class OutOfBoundaryPaths : TestCaseProblem<OutOfBoundaryPathsParams, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = OutOfBoundaryPaths().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<OutOfBoundaryPathsParams, Int>> = arrayOf(
        TestCase(
            input = OutOfBoundaryPathsParams(
                m = 2,
                n = 2,
                maxMove = 2,
                startRow = 0,
                startCol = 0
            ),
            output = 6
        ),
        TestCase(
            input = OutOfBoundaryPathsParams(
                m = 1,
                n = 3,
                maxMove = 3,
                startRow = 0,
                startCol = 1
            ),
            output = 12
        )
    )

    override fun solve(testCaseInput: OutOfBoundaryPathsParams): Int {
        return findPaths(testCaseInput.m, testCaseInput.n, testCaseInput.maxMove, testCaseInput.startRow, testCaseInput.startCol)
    }

    private fun findPaths(m: Int, n: Int, maxMove: Int, startRow: Int, startColumn: Int): Int {
        val mod = 1_000_000_000 + 7
        val directions = arrayOf(1 to 0, -1 to 0, 0 to 1, 0 to -1)
        val dp = Array(m) { Array(n) { IntArray(maxMove + 1) } }

        for (movesLeft in 1..maxMove) {
            for (i in 0 until m) {
                for (j in 0 until n) {
                    for ((dx, dy) in directions) {
                        val x = i + dx
                        val y = j + dy
                        val isOutOfBounds = x < 0 || y < 0 || x == m || y == n

                        dp[i][j][movesLeft] = when {
                            isOutOfBounds -> dp[i][j][movesLeft] + 1
                            else -> (dp[i][j][movesLeft] + dp[x][y][movesLeft - 1]) % mod
                        }
                    }
                }
            }
        }

        return dp[startRow][startColumn][maxMove]
    }

    private fun findPathsRecursive(m: Int, n: Int, maxMove: Int, startRow: Int, startColumn: Int): Int {
        val mod = 1_000_000_000 + 7
        val directions = arrayOf(1 to 0, -1 to 0, 0 to 1, 0 to -1)
        val dp = Array(m + 1) { Array(n + 1) { IntArray(maxMove + 1) { -1 } } }

        fun findPath(i: Int, j: Int, movesLeft: Int): Int {
            if (movesLeft < 0) return 0
            if (i < 0 || j < 0 || i == m || j == n) return 1
            if (dp[i][j][movesLeft] != -1) return dp[i][j][movesLeft]

            var count = 0

            for ((dx, dy) in directions) {
                count += findPath(i + dx, j + dy, movesLeft - 1) // up
                count %= mod
            }

            dp[i][j][movesLeft] = count
            return dp[i][j][movesLeft]
        }

        return findPath(startRow, startColumn, maxMove)
    }
}

data class OutOfBoundaryPathsParams(
    val m: Int,
    val n: Int,
    val maxMove: Int,
    val startRow: Int,
    val startCol: Int
) {

    override fun toString(): String {
        return """
            
            m: $m
            n: $n
            maxMove: $maxMove
            startRow: $startRow
            startCol: $startCol
        """.trimIndent()
    }
}
