package dev.mslalith.dp.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem

class UniquePaths : TestCaseProblem<Pair<Int, Int>, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = UniquePaths().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<Pair<Int, Int>, Int>> = arrayOf(
        TestCase(
            input = 3 to 2,
            output = 3
        ),
        TestCase(
            input = 3 to 7,
            output = 28
        )
    )

    override fun solve(testCaseInput: Pair<Int, Int>): Int {
        return uniquePaths(testCaseInput.first, testCaseInput.second)
    }

    private fun uniquePaths(m: Int, n: Int): Int {
        var prev = IntArray(n)
        val curr = IntArray(n)

        for (i in 0 until m) {
            for (j in 0 until n) {
                if (i == 0 && j == 0) curr[j] = 1
                else {
                    val top = if (i > 0) prev[j] else 0
                    val left = if (j > 0) curr[j - 1] else 0
                    curr[j] = top + left
                }
            }
            prev = curr
        }

        return prev[n - 1]
    }

    private fun uniquePathsDp(m: Int, n: Int): Int {
        val dp = Array(m) { IntArray(n) }

        for (i in 0 until m) {
            for (j in 0 until n) {
                if (i == 0 && j == 0) dp[i][j] = 1
                else {
                    val top = if (i > 0) dp[i - 1][j] else 0
                    val left = if (j > 0) dp[i][j - 1] else 0
                    dp[i][j] = top + left
                }
            }
        }

        return dp[m - 1][n - 1]
    }

    private fun uniquePathsRecursive(m: Int, n: Int): Int {
        val dp = Array(m) { IntArray(n) { -1 } }

        fun findUniquePaths(i: Int, j: Int): Int {
            if (i == 0 && j == 0) return 1
            if (i < 0 || j < 0) return 0
            if (dp[i][j] != -1) return dp[i][j]

            val top = findUniquePaths(i, j - 1)
            val left = findUniquePaths(i - 1, j)

            dp[i][j] = top + left
            return dp[i][j]
        }

        return findUniquePaths(m - 1, n - 1)
    }
}
