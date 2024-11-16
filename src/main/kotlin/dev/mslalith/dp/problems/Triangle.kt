package dev.mslalith.dp.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import kotlin.math.min

class Triangle : TestCaseProblem<List<List<Int>>, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = Triangle().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<List<List<Int>>, Int>> = arrayOf(
        TestCase(
            input = listOf(
                listOf(2),
                listOf(3, 4),
                listOf(6, 5, 7),
                listOf(4, 1, 8, 3)
            ),
            output = 11
        ),
        TestCase(
            input = listOf(
                listOf(-10)
            ),
            output = -10
        )
    )

    override fun solve(testCaseInput: List<List<Int>>): Int {
        return minimumTotal(testCaseInput)
    }

    private fun minimumTotal(triangle: List<List<Int>>): Int {
        val n = triangle.size
        var front = IntArray(n)

        for (j in 0 until n) front[j] = triangle[n - 1][j]

        for (i in n - 2 downTo 0) {
            val curr = IntArray(n)
            for (j in i downTo 0) {
                val down = triangle[i][j] + front[j]
                val diag = triangle[i][j] + front[j + 1]
                curr[j] = min(down, diag)
            }
            front = curr
        }

        return front[0]
    }

    private fun minimumTotalDp(triangle: List<List<Int>>): Int {
        val n = triangle.size
        val dp = Array(n) { IntArray(n + 1) }

        for (j in 0 until n) dp[n - 1][j] = triangle[n - 1][j]

        for (i in n - 2 downTo 0) {
            for (j in i downTo 0) {
                val down = triangle[i][j] + dp[i + 1][j]
                val diag = triangle[i][j] + dp[i + 1][j + 1]
                dp[i][j] = min(down, diag)
            }
        }

        return dp[0][0]
    }

    private fun minimumTotalRecursive(triangle: List<List<Int>>): Int {
        val n = triangle.size
        val dp = Array(n) { IntArray(n + 1) { -1 } }

        fun minTotal(i: Int, j: Int): Int {
            if (i == n - 1) return triangle[i][j]
            if (dp[i][j] != -1) return dp[i][j]

            val down = triangle[i][j] + minTotal(i + 1, j)
            val diag = triangle[i][j] + minTotal(i + 1, j + 1)

            dp[i][j] = min(down, diag)
            return dp[i][j]
        }

        return minTotal(0, 0)
    }
}
