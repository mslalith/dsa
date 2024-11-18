package dev.mslalith.dp.problems

import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.core.TestCase
import kotlin.math.max

class BestTimeToBuyAndSellStockII : TestCaseProblem<IntArray, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = BestTimeToBuyAndSellStockII().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<IntArray, Int>> = arrayOf(
        TestCase(
            input = intArrayOf(7, 1, 5, 3, 6, 4),
            output = 7
        ),
        TestCase(
            input = intArrayOf(1, 2, 3, 4, 5),
            output = 4
        ),
        TestCase(
            input = intArrayOf(7, 6, 4, 3, 1),
            output = 0
        )
    )

    override fun solve(testCaseInput: IntArray): Int {
        return maxProfit(testCaseInput)
    }

    private fun maxProfit(prices: IntArray): Int {
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
                        prices[day] + next[1],
                        0 + next[0]
                    )
                }
            }
            next = curr
        }

        return next[1]
    }

    private fun maxProfitDp(prices: IntArray): Int {
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
                        prices[day] + dp[day + 1][1],
                        0 + dp[day + 1][0]
                    )
                }
            }
        }

        return dp[0][1]
    }

    private fun maxProfitRecursive(prices: IntArray): Int {
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
                    prices[day] + findMaxProfit(day + 1, 1),
                    0 + findMaxProfit(day + 1, 0)
                )
            }
            return dp[day][buy]
        }

        return findMaxProfit(0, 1)
    }
}
