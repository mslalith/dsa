package dev.mslalith.array.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem

class GasStation : TestCaseProblem<Pair<IntArray, IntArray>, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = GasStation().runAll()
    }

    override fun getTestCases(): Array<TestCase<Pair<IntArray, IntArray>, Int>> = arrayOf(
        TestCase(
            input = intArrayOf(1, 2, 3, 4, 5) to intArrayOf(3, 4, 5, 1, 2),
            output = 3
        ),
        TestCase(
            input = intArrayOf(2, 3, 4) to intArrayOf(3, 4, 3),
            output = -1
        ),
        TestCase(
            input = intArrayOf(5, 1, 2, 3, 4) to intArrayOf(4, 4, 1, 5, 1),
            output = 4
        ),
        TestCase(
            input = intArrayOf(2) to intArrayOf(2),
            output = 0
        ),
        TestCase(
            input = intArrayOf(3, 1, 1) to intArrayOf(1, 2, 2),
            output = 0
        )
    )

    override fun solve(testCaseInput: Pair<IntArray, IntArray>): Int {
        return canCompleteCircuit(testCaseInput.first, testCaseInput.second)
    }

    private fun canCompleteCircuit(gas: IntArray, cost: IntArray): Int {
        var totalGasSum = 0
        var gasSum = 0
        var start = 0

        for (i in gas.indices) {
            val consume = gas[i] - cost[i]
            totalGasSum += consume
            gasSum += consume

            if (gasSum < 0) {
                gasSum = 0
                start = i + 1
            }
        }

        return if (totalGasSum < 0) -1 else start
    }
}
