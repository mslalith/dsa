package dev.mslalith.dp.problems

import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.core.TestCase
import kotlin.math.max

class BestTimeToBuyAndSellStockIV : TestCaseProblem<Pair<IntArray, Int>, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = BestTimeToBuyAndSellStockIV().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<Pair<IntArray, Int>, Int>> = arrayOf(
        TestCase(
            input = intArrayOf(2, 4, 1) to 2,
            output = 2
        ),
        TestCase(
            input = intArrayOf(3, 2, 6, 5, 0, 3) to 2,
            output = 7
        )
    )

    override fun solve(testCaseInput: Pair<IntArray, Int>): Int {
        return maxProfit(testCaseInput.first, testCaseInput.second)
    }

    private fun maxProfit(prices: IntArray, k: Int): Int {
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

    private fun maxProfitDp(prices: IntArray, k: Int): Int {
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

    private fun maxProfitRecursive(prices: IntArray, k: Int): Int {
        val n = prices.size
        val dp = Array(n) { Array(2) { IntArray(k + 1) { -1 } } }

        fun findMaxProfit(day: Int, buy: Int, cap: Int): Int {
            if (day == prices.size || cap == 0) return 0
            if (dp[day][buy][cap] != -1) return dp[day][buy][cap]

            dp[day][buy][cap] = if (buy == 1) {
                max(
                    -prices[day] + findMaxProfit(day + 1, 0, cap),
                    0 + findMaxProfit(day + 1, 1, cap)
                )
            } else {
                max(
                    prices[day] + findMaxProfit(day + 1, 1, cap - 1),
                    0 + findMaxProfit(day + 1, 0, cap)
                )
            }
            return dp[day][buy][cap]
        }

        return findMaxProfit(0, 1, k)
    }
}
