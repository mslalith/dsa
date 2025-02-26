package dev.mslalith.array.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

class MaximumAbsoluteSumOfAnySubarray : TestCaseProblem<IntArray, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = MaximumAbsoluteSumOfAnySubarray().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<IntArray, Int>> = arrayOf(
        TestCase(
            input = intArrayOf(1, -3, 2, 3, -4),
            output = 5
        ),
        TestCase(
            input = intArrayOf(2, -5, 1, -4, 3, -2),
            output = 8
        )
    )

    override fun solve(testCaseInput: IntArray): Int {
        return maxAbsoluteSum(testCaseInput)
    }

    private fun maxAbsoluteSum(nums: IntArray): Int {
        val n = nums.size

        var maxSum = 0
        var minSum = Int.MAX_VALUE
        var curr = 0

        for (i in 0 until n) {
            curr += nums[i]
            maxSum = max(maxSum, curr)
            if (curr < 0) curr = 0
        }

        curr = 0

        for (i in 0 until n) {
            curr += nums[i]
            minSum = min(minSum, curr)
            if (curr > 0) curr = 0
        }

        return max(maxSum, abs(minSum))
    }

    private fun maxAbsoluteSumNaive(nums: IntArray): Int {
        val n = nums.size
        var maxAbsSum = 0

        for (i in 0 until n) {
            var sum = 0
            for (j in i until n) {
                sum += nums[j]
                maxAbsSum = max(maxAbsSum, abs(sum))
            }
        }

        return maxAbsSum
    }
}
