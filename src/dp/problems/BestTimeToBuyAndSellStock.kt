package src.dp.problems

import src.core.Problem
import src.core.TestCase

class BestTimeToBuyAndSellStock : Problem<IntArray, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = BestTimeToBuyAndSellStock().run()
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
        if (prices.isEmpty() || prices.size == 1) return 0
        var min = prices[0]
        var max = min
        var profit = 0
        for (i in 1 until prices.size) {
            if (prices[i] < min) {
                min = prices[i]
                max = min
            } else max = Math.max(prices[i], max)
            profit = Math.max(profit, max - min)
        }
        return profit
    }
}