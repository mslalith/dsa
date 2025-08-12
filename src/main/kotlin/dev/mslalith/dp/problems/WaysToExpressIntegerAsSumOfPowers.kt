package dev.mslalith.dp.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import kotlin.math.pow

class WaysToExpressIntegerAsSumOfPowers : TestCaseProblem<Pair<Int, Int>, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = WaysToExpressIntegerAsSumOfPowers().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<Pair<Int, Int>, Int>> = arrayOf(
        TestCase(
            input = 10 to 2,
            output = 1
        ),
        TestCase(
            input = 4 to 1,
            output = 2
        )
    )

    override fun solve(testCaseInput: Pair<Int, Int>): Int {
        return numberOfWays(testCaseInput.first, testCaseInput.second)
    }

    private fun numberOfWays(n: Int, x: Int): Int {
        val mod = 1_000_000_000 + 7
        val dp = LongArray(n + 1)
        dp[0] = 1

        var i = 1.0
        var power = i.pow(x)
        while (power <= n) {
            val p = power.toInt()
            for (sum in n downTo p) {
                dp[sum] = (dp[sum] + dp[sum - p]) % mod
            }
            i++

            power = i.pow(x)
        }

        return dp[n].toInt()
    }
}
