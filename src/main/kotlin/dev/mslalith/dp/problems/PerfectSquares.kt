package dev.mslalith.dp.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import kotlin.math.min


class PerfectSquares : TestCaseProblem<Int, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = PerfectSquares().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<Int, Int>> = arrayOf(
        TestCase(
            input = 12,
            output = 3
        ),
        TestCase(
            input = 13,
            output = 2
        )
    )

    override fun solve(testCaseInput: Int): Int {
        return numSquares(testCaseInput)
    }

    private fun numSquares(n: Int): Int {
        val dp = IntArray(n + 1) { it }

        for (target in 4..n) {
            var i = 1

            while (i * i <= target) {
                val square = i * i
                dp[target] = min(dp[target], 1 + dp[target - square])
                i++
            }
        }

        return dp[n]
    }

    private fun numSquaresRecursive(n: Int): Int {
        val dp = IntArray(n + 1) { -1 }

        fun findNumSquares(target: Int): Int {
            if (target < 4) return target
            if (dp[target] != -1) return dp[target]

            dp[target] = target
            var i = 1

            while (i * i <= target) {
                val square = i * i
                dp[target] = min(dp[target], 1 + findNumSquares(target - square))
                i++
            }

            return dp[target]
        }

        return findNumSquares(n)
    }
}
