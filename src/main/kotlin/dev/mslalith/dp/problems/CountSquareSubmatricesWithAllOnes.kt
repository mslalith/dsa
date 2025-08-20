package dev.mslalith.dp.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem

class CountSquareSubmatricesWithAllOnes : TestCaseProblem<Array<IntArray>, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = CountSquareSubmatricesWithAllOnes().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<Array<IntArray>, Int>> = arrayOf(
        TestCase(
            input = arrayOf(
                intArrayOf(0, 1, 1, 1),
                intArrayOf(1, 1, 1, 1),
                intArrayOf(0, 1, 1, 1)
            ),
            output = 15
        ),
        TestCase(
            input = arrayOf(
                intArrayOf(1, 0, 1),
                intArrayOf(1, 1, 0),
                intArrayOf(1, 1, 0)
            ),
            output = 7
        )
    )

    override fun solve(testCaseInput: Array<IntArray>): Int {
        return countSquares(testCaseInput)
    }

    private fun countSquares(matrix: Array<IntArray>): Int {
        val row = matrix.size
        val col = matrix[0].size
        val dp = Array(row) { IntArray(col) }

        var count = 0

        for (i in 0..<row) {
            for (j in 0..<col) {
                if (matrix[i][j] == 1) {
                    if (i == 0 || j == 0) dp[i][j] = 1 else {
                        dp[i][j] = 1 + minOf(
                            dp[i - 1][j],
                            minOf(dp[i][j - 1], dp[i - 1][j - 1])
                        )
                    }

                    count += dp[i][j]
                }
            }
        }

        return count
    }
}
