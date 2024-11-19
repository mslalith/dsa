package dev.mslalith.dp.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import kotlin.math.min


class CoinChange : TestCaseProblem<Pair<IntArray, Int>, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = CoinChange().runForConsole()
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
        val inf = Int.MAX_VALUE / 2
        val n = coins.size
        val curr = IntArray(amount + 1)
        var next = IntArray(amount + 1)
        for (amt in 1..amount) next[amt] = inf

        for (i in n - 1 downTo  0) {
            curr[0] = 0
            for (amt in 1..amount) {
                curr[amt] = if (coins[i] <= amt) {
                    val notTake = next[amt]
                    val take = 1 + curr[amt - coins[i]]
                    min(notTake, take)
                } else {
                    next[amt]
                }
            }
            next = curr
        }

        val result = next[amount]
        return if (result == Int.MAX_VALUE / 2) -1 else result
    }

    private fun coinChangeDp(coins: IntArray, amount: Int): Int {
        val inf = Int.MAX_VALUE / 2
        val n = coins.size
        val dp = Array(n + 1) { IntArray(amount + 1) }
        for (i in 0..n) dp[i][0] = 0
        for (amt in 1..amount) dp[n][amt] = inf

        for (i in n - 1 downTo  0) {
            for (amt in 1..amount) {
                dp[i][amt] = if (coins[i] <= amt) {
                    val notTake = dp[i + 1][amt]
                    val take = 1 + dp[i][amt - coins[i]]
                    min(notTake, take)
                } else {
                    dp[i + 1][amt]
                }
            }
        }

        val result = dp[0][amount]
        return if (result == Int.MAX_VALUE / 2) -1 else result
    }

    private fun coinChangeRecursive(coins: IntArray, amount: Int): Int {
        val n = coins.size
        val dp = Array(n + 1) { IntArray(amount + 1) { -1 } }

        fun findCoinChange(i: Int, remaining: Int): Int {
            if (remaining == 0) return 0
            if (i >= n) return Int.MAX_VALUE / 2
            if (dp[i][remaining] != -1) return dp[i][remaining]

            dp[i][remaining] = if (coins[i] <= remaining) {
                val notTake = findCoinChange(i + 1, remaining)
                val take = 1 + findCoinChange(i, remaining - coins[i])
                min(notTake, take)
            } else {
                findCoinChange(i + 1, remaining)
            }

            return dp[i][remaining]
        }

        val result = findCoinChange(0, amount)
        return if (result == Int.MAX_VALUE / 2) -1 else result
    }
}
