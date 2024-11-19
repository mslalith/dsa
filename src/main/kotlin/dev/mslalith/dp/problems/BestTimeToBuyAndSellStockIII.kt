package dev.mslalith.dp.problems

import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.core.TestCase
import kotlin.math.max

class BestTimeToBuyAndSellStockIII : TestCaseProblem<IntArray, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = BestTimeToBuyAndSellStockIII().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<IntArray, Int>> = arrayOf(
        TestCase(
            input = intArrayOf(3, 3, 5, 0, 0, 3, 1, 4),
            output = 6
        ),
        TestCase(
            input = intArrayOf(1, 2, 3, 4, 5),
            output = 4
        ),
        TestCase(
            input = intArrayOf(7, 6, 4, 3, 1),
            output = 0
        ),
        TestCase(
            input = intArrayOf(2, 1, 4, 5, 2, 9, 7),
            output = 11
        )
    )

    override fun solve(testCaseInput: IntArray): Int {
        return maxProfit(testCaseInput)
    }

    private fun maxProfit(prices: IntArray): Int {
        val k = 2
        val n = prices.size
        val curr = Array(2) { IntArray(k + 1) { -1 } }
        var next = Array(2) { IntArray(k + 1) { -1 } }
        for (buy in 0..1) for (ki in 0..k) next[buy][ki] = 0
        for (buy in 0..1) for (ki in 0..k) next[buy][ki] = 0

        for (day in n - 1 downTo 0) {
            for (buy in 0..1) {
                curr[buy][0] = 0
                for (ki in 1..k) {
                    curr[buy][ki] = if (buy == 1) {
                        max(
                            -prices[day] + next[0][ki],
                            0 + next[1][ki]
                        )
                    } else {
                        max(
                            prices[day] + next[1][ki - 1],
                            0 + next[0][ki]
                        )
                    }
                }
            }
            next = curr
        }

        return next[1][k]
    }

    private fun maxProfitDp(prices: IntArray): Int {
        val k = 2
        val n = prices.size
        val dp = Array(n + 1) { Array(2) { IntArray(k + 1) { -1 } } }
        for (buy in 0..1) for (ki in 0..k) dp[n][buy][ki] = 0
        for (i in 0..n) for (buy in 0..1) dp[i][buy][0] = 0

        for (day in n - 1 downTo 0) {
            for (buy in 0..1) {
                for (ki in 1..k) {
                    dp[day][buy][ki] = if (buy == 1) {
                        max(
                            -prices[day] + dp[day + 1][0][ki],
                            0 + dp[day + 1][1][ki]
                        )
                    } else {
                        max(
                            prices[day] + dp[day + 1][1][ki - 1],
                            0 + dp[day + 1][0][ki]
                        )
                    }
                }
            }
        }

        return dp[0][1][k]
    }

    private fun maxProfitRecursive(prices: IntArray): Int {
        val maxAllowed = 2
        val n = prices.size
        val dp = Array(n) { Array(2) { IntArray(maxAllowed + 1) { -1 } } }

        fun findMaxProfit(day: Int, buy: Int, k: Int): Int {
            if (day == prices.size || k == 0) return 0
            if (dp[day][buy][k] != -1) return dp[day][buy][k]

            dp[day][buy][k] = if (buy == 1) {
                max(
                    -prices[day] + findMaxProfit(day + 1, 0, k),
                    0 + findMaxProfit(day + 1, 1, k)
                )
            } else {
                max(
                    prices[day] + findMaxProfit(day + 1, 1, k - 1),
                    0 + findMaxProfit(day + 1, 0, k)
                )
            }
            return dp[day][buy][k]
        }

        return findMaxProfit(0, 1, maxAllowed)
    }
}
