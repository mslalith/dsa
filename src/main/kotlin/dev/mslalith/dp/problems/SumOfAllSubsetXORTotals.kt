package dev.mslalith.dp.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem

class SumOfAllSubsetXORTotals : TestCaseProblem<IntArray, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = SumOfAllSubsetXORTotals().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<IntArray, Int>> = arrayOf(
        TestCase(
            input = intArrayOf(1, 3),
            output = 6
        ),
        TestCase(
            input = intArrayOf(5, 1, 6),
            output = 28
        ),
        TestCase(
            input = intArrayOf(3, 4, 5, 6, 7, 8),
            output = 480
        )
    )

    override fun solve(testCaseInput: IntArray): Int {
        return subsetXORSum(testCaseInput)
    }

    private fun subsetXORSum(nums: IntArray): Int {
        val n = nums.size

        fun backtrack(i: Int, xorSum: Int): Int {
            if (i == n) return xorSum

            val notTake = backtrack(i + 1, xorSum)
            val take = backtrack(i + 1, xorSum xor nums[i])

            return notTake + take
        }

        return backtrack(0, 0)
    }
}
