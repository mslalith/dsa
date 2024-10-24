package dev.mslalith.dp.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import kotlin.math.max

class MaximumSubarray : TestCaseProblem<IntArray, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = MaximumSubarray().runAll()
    }

    override fun getTestCases(): Array<TestCase<IntArray, Int>> = arrayOf(
        TestCase(
            input = intArrayOf(-2, 1, -3, 4, -1, 2, 1, -5, 4),
            output = 6
        ),
        TestCase(
            input = intArrayOf(1),
            output = 1
        ),
        TestCase(
            input = intArrayOf(5, 4, -1, 7, 8),
            output = 23
        ),
        TestCase(
            input = intArrayOf(-1),
            output = -1
        )
    )

    override fun solve(testCaseInput: IntArray): Int {
        return maxSubArray(testCaseInput)
    }

    private fun maxSubArray(nums: IntArray): Int {
        var maxSum = Int.MIN_VALUE
        var currSum = 0

        for (num in nums) {
            currSum += num
            maxSum = max(maxSum, currSum)
            if (currSum < 0) currSum = 0
        }

        return maxSum
    }
}
