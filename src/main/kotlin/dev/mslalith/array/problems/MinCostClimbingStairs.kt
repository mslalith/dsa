package dev.mslalith.array.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import kotlin.math.min

class MinCostClimbingStairs : TestCaseProblem<IntArray, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = MinCostClimbingStairs().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<IntArray, Int>> = arrayOf(
        TestCase(
            input = intArrayOf(10, 15, 20),
            output = 15
        ),
        TestCase(
            input = intArrayOf(1, 100, 1, 1, 1, 100, 1, 1, 100, 1),
            output = 6
        )
    )

    override fun solve(testCaseInput: IntArray): Int {
        return minCostClimbingStairs(testCaseInput)
    }

    private fun minCostClimbingStairs(cost: IntArray): Int {
        val n = cost.size
        var first = cost[0]
        var second = cost[1]
        if (n == 2) return min(first, second)

        for (i in 2 until n) {
            val min = cost[i] + min(first, second)
            first = second
            second = min
        }

        return min(first, second)
    }
}