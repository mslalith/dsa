package dev.mslalith.dp.problems

import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.core.TestCase
import kotlin.math.max


class BestTimeToBuyAndSellStockWithTransactionFee : TestCaseProblem<Pair<IntArray, Int>, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = BestTimeToBuyAndSellStockWithTransactionFee().runAll()
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
        var cash = 0
        var hold = -prices[0]

        for (i in 1 until prices.size) {
            cash = max(cash, hold + prices[i] - fee)
            hold = max(hold, cash - prices[i])
        }

        return cash
    }

    private fun maxProfitRecursive(prices: IntArray, fee: Int): Int {

        fun takeACall(day: Int, buy: Boolean): Int {
            if (day == prices.size) return 0

            return if (buy) {
                max(
                    -prices[day] + takeACall(day + 1, false),
                    0 + takeACall(day + 1, true)
                )
            } else {
                max(
                    prices[day] - fee + takeACall(day + 1, true),
                    0 + takeACall(day + 1, false)
                )
            }
        }

        return takeACall(0, true)
    }
}
