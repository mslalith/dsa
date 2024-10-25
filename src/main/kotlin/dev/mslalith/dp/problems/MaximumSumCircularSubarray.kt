package dev.mslalith.dp.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import kotlin.math.max
import kotlin.math.min

class MaximumSumCircularSubarray : TestCaseProblem<IntArray, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = MaximumSumCircularSubarray().runAll()
    }

    override fun getTestCases(): Array<TestCase<IntArray, Int>> = arrayOf(
        TestCase(
            input = intArrayOf(1, -2, 3, -2),
            output = 3
        ),
        TestCase(
            input = intArrayOf(5, -3, 5),
            output = 10
        ),
        TestCase(
            input = intArrayOf(-3, -2, -3),
            output = -2
        )
    )

    override fun solve(testCaseInput: IntArray): Int {
        return maxSubarraySumCircular(testCaseInput)
    }

    private fun maxSubarraySumCircular(nums: IntArray): Int {
        var totalSum = 0
        var minSum = Int.MAX_VALUE
        var maxSum = Int.MIN_VALUE
        var currMinSum = 0
        var currMaxSum = 0

        for (num in nums) {
            totalSum += num
            currMinSum += num
            currMaxSum += num
            minSum = min(minSum, currMinSum)
            maxSum = max(maxSum, currMaxSum)
            if (currMinSum > 0) currMinSum = 0
            if (currMaxSum < 0) currMaxSum = 0
        }

        return if (maxSum < 0) maxSum else max(maxSum, totalSum - minSum)
    }
}
