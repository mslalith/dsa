package dev.mslalith.dp.problems

import dev.mslalith.core.Problem
import dev.mslalith.core.TestCase
import kotlin.math.min

class CoinChange : Problem<Pair<IntArray, Int>, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = CoinChange().run()
    }

    override fun getTestCases(): Array<TestCase<Pair<IntArray, Int>, Int>> = arrayOf(
        TestCase(
            input = intArrayOf(1, 2, 5) to 11,
            output = 3
        ),
        TestCase(
            input = intArrayOf(2) to 3,
            output = -1
        ),
        TestCase(
            input = intArrayOf(1) to 0,
            output = 0
        ),
        TestCase(
            input = intArrayOf(1, 2, 5) to 100,
            output = 20
        ),
    )

    override fun solve(testCaseInput: Pair<IntArray, Int>): Int {
        return coinChange(testCaseInput.first, testCaseInput.second)
    }

    private fun coinChange(coins: IntArray, amount: Int): Int {
        if (amount == 0) return 0

        val dp = IntArray(amount + 1) { 0 }

        for (am in 1..amount) {
            dp[am] = Int.MAX_VALUE
            for (coin in coins) {
                if (coin <= am && dp[am - coin] != Int.MAX_VALUE) {
                    dp[am] = min(dp[am], 1 + dp[am - coin])
                }
            }
        }

        return if (dp[amount] == Int.MAX_VALUE) -1 else dp[amount]
    }
}
