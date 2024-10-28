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
        var cash = 0
        var hold = -prices[0]

        for (i in 1 until prices.size) {
            cash = max(cash, hold + prices[i])
            hold = max(hold, cash - prices[i])
        }

        return cash
    }

    private fun maxProfitRecursive(prices: IntArray): Int {

        fun takeACall(day: Int, buy: Boolean): Int {
            if (day == prices.size) return 0

            return if (buy) {
                max(
                    -prices[day] + takeACall(day + 1, false),
                    0 + takeACall(day + 1, true)
                )
            } else {
                max(
                    prices[day] + takeACall(day + 1, true),
                    0 + takeACall(day + 1, false)
                )
            }
        }

        return takeACall(0, true)
    }
}
