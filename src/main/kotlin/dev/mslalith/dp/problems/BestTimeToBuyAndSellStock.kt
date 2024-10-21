package dev.mslalith.dp.problems

import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.core.TestCase
import kotlin.math.max

class BestTimeToBuyAndSellStock : TestCaseProblem<IntArray, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = BestTimeToBuyAndSellStock().runAll()
    }

    override fun getTestCases(): Array<TestCase<IntArray, Int>> = arrayOf(
        TestCase(
            input = intArrayOf(7, 1, 5, 3, 6, 4),
            output = 5
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
        if (n == 0 || n == 1) return 0

        var min = prices[0]
        var max = min
        var profit = 0

        for (i in 1 until n) {
            when {
                prices[i] >= min -> max = max(prices[i], max)
                else -> {
                    min = prices[i]
                    max = min
                }
            }
            profit = max(profit, max - min)
        }

        return profit
    }
}