package dev.mslalith.dp.problems

import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.core.TestCase
import kotlin.math.max


class BestTimeToBuyAndSellStockWithTransactionFee : TestCaseProblem<Pair<IntArray, Int>, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = BestTimeToBuyAndSellStockWithTransactionFee().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<Pair<IntArray, Int>, Int>> = arrayOf(
        TestCase(
            input = intArrayOf(1, 3, 2, 8, 4, 9) to 2,
            output = 8
        ),
        TestCase(
            input = intArrayOf(1, 3, 7, 5, 10, 3) to 3,
            output = 6
        )
    )

    override fun solve(testCaseInput: Pair<IntArray, Int>): Int {
        return maxProfit(testCaseInput.first, testCaseInput.second)
    }

    private fun maxProfit(prices: IntArray, fee: Int): Int {
        val n = prices.size
        val curr = IntArray(2)
        var next = IntArray(2)

        for (day in n - 1 downTo 0) {
            for (buy in 0..1) {
                curr[buy] = if (buy == 1) {
                    max(
                        -prices[day] + next[0],
                        0 + next[1]
                    )
                } else {
                    max(
                        prices[day] - fee + next[1],
                        0 + next[0]
                    )
                }
            }
            next = curr
        }

        return next[1]
    }

    private fun maxProfitDp(prices: IntArray, fee: Int): Int {
        val n = prices.size
        val dp = Array(n + 1) { IntArray(2) { -1 } }
        dp[n][0] = 0
        dp[n][1] = 0

        for (day in n - 1 downTo 0) {
            for (buy in 0..1) {
                dp[day][buy] = if (buy == 1) {
                    max(
                        -prices[day] + dp[day + 1][0],
                        0 + dp[day + 1][1]
                    )
                } else {
                    max(
                        prices[day] - fee + dp[day + 1][1],
                        0 + dp[day + 1][0]
                    )
                }
            }
        }

        return dp[0][1]
    }

    private fun maxProfitRecursive(prices: IntArray, fee: Int): Int {
        val n = prices.size
        val dp = Array(n) { IntArray(2) { -1 } }

        fun findMaxProfit(day: Int, buy: Int): Int {
            if (day == prices.size) return 0
            if (dp[day][buy] != -1) return dp[day][buy]

            dp[day][buy] = if (buy == 1) {
                max(
                    -prices[day] + findMaxProfit(day + 1, 0),
                    0 + findMaxProfit(day + 1, 1)
                )
            } else {
                max(
                    prices[day] - fee + findMaxProfit(day + 1, 1),
                    0 + findMaxProfit(day + 1, 0)
                )
            }
            return dp[day][buy]
        }

        return findMaxProfit(0, 1)
    }
}
