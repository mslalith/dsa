package dev.mslalith.dp.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.dp.problems.New21Game.New21GameParams

class New21Game : TestCaseProblem<New21GameParams, Double>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = New21Game().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<New21GameParams, Double>> = arrayOf(
        TestCase(
            input = New21GameParams(
                n = 10,
                k = 1,
                maxPts = 10
            ),
            output = 1.0
        ),
        TestCase(
            input = New21GameParams(
                n = 6,
                k = 1,
                maxPts = 10
            ),
            output = 0.6
        ),
        TestCase(
            input = New21GameParams(
                n = 21,
                k = 17,
                maxPts = 10
            ),
            output = 0.73278
        )
    )

    override fun solve(testCaseInput: New21GameParams): Double {
        return new21Game(testCaseInput.n, testCaseInput.k, testCaseInput.maxPts)
    }

    private fun new21Game(n: Int, k: Int, maxPts: Int): Double {
        if (k == 0 || n >= k - 1 + maxPts) return 1.0

        val dp = DoubleArray(maxPts)
        dp[0] = 1.0

        var windowSum = 1.0
        var result = 0.0

        for (i in 1..n) {
            val prob = windowSum / maxPts

            if (i < k) windowSum += prob else result += prob
            if (i >= maxPts) windowSum -= dp[i % maxPts]

            dp[i % maxPts] = prob
        }

        return result
    }

    data class New21GameParams(
        val n: Int,
        val k: Int,
        val maxPts: Int
    )
}
