package dev.mslalith.dp.problems

import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.core.TestCase

class DominoAndTrominoTiling : TestCaseProblem<Int, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = DominoAndTrominoTiling().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<Int, Int>> = arrayOf(
        TestCase(
            input = 1,
            output = 1
        ),
        TestCase(
            input = 2,
            output = 2
        ),
        TestCase(
            input = 3,
            output = 5
        ),
        TestCase(
            input = 4,
            output = 11
        ),
        TestCase(
            input = 5,
            output = 24
        ),
        TestCase(
            input = 30,
            output = 312342182
        )
    )

    override fun solve(testCaseInput: Int): Int {
        return numTilings(testCaseInput)
    }

    private fun numTilings(n: Int): Int {
        if (n <= 2) return n
        if (n == 3) return 5

        val mod = 1000000007
        var last3 = 1
        var last2 = 2
        var last1 = 5

        for (i in 4..n) {
            val one = (last1 * 2) % mod
            val three = last3 % mod
            val ways = one + three
            val curr = ways % mod
            last3 = last2
            last2 = last1
            last1 = curr
        }

        return last1
    }

    private fun numTilingsDp(n: Int): Int {
        val mod = 1000000007
        val dp = IntArray(n + 1)
        if (n >= 1) dp[1] = 1
        if (n >= 2) dp[2] = 2
        if (n >= 3) dp[3] = 5

        for (i in 4..n) {
            val one = (dp[i - 1] * 2) % mod
            val three = dp[i - 3] % mod
            val ways = one + three
            dp[i] = ways % mod
        }

        return dp[n]
    }

    private fun numTilingsRecursive(n: Int): Int {
        val mod = 1000000007
        val dp = IntArray(n + 1) { -1 }

        fun numTilings(i: Int): Int {
            if (i == 1) return 1
            if (i == 2) return 2
            if (i == 3) return 5
            if (dp[i] != -1) return dp[i]

            val one = (numTilings(i - 1) * 2) % mod
            val three = numTilings(i - 3) % mod
            val ways = one + three
            dp[i] = ways % mod
            return dp[i]
        }

        return numTilings(n)
    }
}
