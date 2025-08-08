package dev.mslalith.dp.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem

class SoupServings : TestCaseProblem<Int, Double>() {
    
    companion object {
        @JvmStatic
        fun main(args: Array<String>) = SoupServings().runForConsole()
    }
    
    override fun getTestCases(): Array<TestCase<Int, Double>> = arrayOf(
        TestCase(
            input = 50,
            output = 0.6250
        ),
        TestCase(
            input = 100,
            output = 0.71875
        )
    )
    
    override fun solve(testCaseInput: Int): Double {
        return soupServings(testCaseInput)
    }

    private fun soupServings(n: Int): Double {
        if (n > 4800) return 1.0

        val dp = Array(n + 1) { DoubleArray(n + 1) { -1.0 } }

        fun getSoupServings(a: Int, b: Int): Double {
            if (a <= 0 && b > 0) return 1.0
            if (a == 0 && b == 0) return 0.5
            if (a > 0 && b <= 0) return 0.0
            if (a <= 0) return 0.5

            if (dp[a][b] != -1.0) return dp[a][b]

            val x = 0.25 * getSoupServings(a - 100, b)
            val y = 0.25 * getSoupServings(a - 75, b - 25)
            val z = 0.25 * getSoupServings(a - 50, b - 50)
            val w = 0.25 * getSoupServings(a - 25, b - 75)

            return (x + y + z + w).also { dp[a][b] = it }
        }

        return getSoupServings(n, n)
    }
}
