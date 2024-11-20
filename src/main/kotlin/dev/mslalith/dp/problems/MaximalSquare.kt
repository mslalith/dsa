package dev.mslalith.dp.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import kotlin.math.max
import kotlin.math.min

class MaximalSquare : TestCaseProblem<Array<CharArray>, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = MaximalSquare().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<Array<CharArray>, Int>> = arrayOf(
        TestCase(
            input = arrayOf(
                charArrayOf('1', '0', '1', '0', '0'),
                charArrayOf('1', '0', '1', '1', '1'),
                charArrayOf('1', '1', '1', '1', '1'),
                charArrayOf('1', '0', '0', '1', '0')
            ),
            output = 4
        ),
        TestCase(
            input = arrayOf(
                charArrayOf('0', '1'),
                charArrayOf('1', '0')
            ),
            output = 1
        ),
        TestCase(
            input = arrayOf(
                charArrayOf('0')
            ),
            output = 0
        ),
        TestCase(
            input = arrayOf(
                charArrayOf('1', '1', '1', '1', '0'),
                charArrayOf('1', '1', '1', '1', '0'),
                charArrayOf('1', '1', '1', '1', '1'),
                charArrayOf('1', '1', '1', '1', '1'),
                charArrayOf('0', '0', '1', '1', '1')
            ),
            output = 16
        ),
        TestCase(
            input = arrayOf(
                charArrayOf('0', '0', '0', '0', '0'),
                charArrayOf('0', '0', '0', '0', '0'),
                charArrayOf('0', '0', '0', '0', '1'),
                charArrayOf('0', '0', '0', '0', '0'),
            ),
            output = 1
        )
    )

    override fun solve(testCaseInput: Array<CharArray>): Int {
        return maximalSquare(testCaseInput)
    }

    private fun maximalSquare(matrix: Array<CharArray>): Int {
        val m = matrix.size
        val n = matrix[0].size

        val dp = Array(m + 1) { IntArray(n + 1) }

        for (i in (m - 1) downTo  0) {
            for (j in (n - 1) downTo  0) {
                val right = dp[i][j + 1]
                val diag = dp[i + 1][j + 1]
                val bottom = dp[i + 1][j]

                dp[i][j] = if (matrix[i][j] != '1') 0 else {
                    1 + min(diag, min(right, bottom))
                }
            }
        }

        var maxi = 0
        for (row in dp) maxi = max(maxi, row.max())
        return maxi * maxi
    }

    private fun maximalSquareRecursive(matrix: Array<CharArray>): Int {
        val m = matrix.size
        val n = matrix[0].size

        val dp = Array(m) { IntArray(n) { -1 } }

        fun findMaximalSquare(i: Int, j: Int): Int {
            if (i >= m || j >= n) return 0
            if (dp[i][j] != -1) return dp[i][j]

            val right = findMaximalSquare(i, j + 1)
            val diag = findMaximalSquare(i + 1, j + 1)
            val bottom = findMaximalSquare(i + 1, j)

            dp[i][j] = if (matrix[i][j] != '1') 0 else {
                1 + min(diag, min(right, bottom))
            }

            return dp[i][j]
        }

        findMaximalSquare(0, 0)

        var maxi = 0
        for (row in dp) maxi = max(maxi, row.max())
        return maxi * maxi
    }

    private fun maximalSquareNaive(matrix: Array<CharArray>): Int {
        val m = matrix.size
        val n = matrix[0].size

        var maxi = 0

        val dp = Array(m) { IntArray(n) }
        for (i in 0 until m) {
            dp[i][0] = if (matrix[i][0] == '1') {
                if (maxi == 0) maxi = 1
                1
            } else 0
        }
        for (j in 0 until n) {
            dp[0][j] = if (matrix[0][j] == '1') {
                if (maxi == 0) maxi = 1
                1
            } else 0
        }


        for (i in 1 until m) {
            for (j in 1 until n) {
                if (matrix[i][j] == '1') {
                    if (maxi == 0) maxi = 1
                    if (matrix[i - 1][j - 1] == '1' && matrix[i - 1][j] == '1' && matrix[i][j - 1] == '1') {
                        val dpMax = min(dp[i - 1][j - 1], min(dp[i - 1][j], dp[i][j - 1]))
                        dp[i][j] = if (dpMax == 0) 2 else dpMax + 1

                        val side = dp[i][j]
                        val area = side * side
                        maxi = max(maxi, area)
                    }
                }
            }
        }

        return maxi
    }
}
