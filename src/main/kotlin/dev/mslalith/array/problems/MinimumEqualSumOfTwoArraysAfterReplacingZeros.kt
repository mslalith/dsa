package dev.mslalith.array.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem

class MinimumEqualSumOfTwoArraysAfterReplacingZeros : TestCaseProblem<Pair<IntArray, IntArray>, Long>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = MinimumEqualSumOfTwoArraysAfterReplacingZeros().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<Pair<IntArray, IntArray>, Long>> = arrayOf(
        TestCase(
            input = intArrayOf(3, 2, 0, 1, 0) to intArrayOf(6, 5, 0),
            output = 12
        ),
        TestCase(
            input = intArrayOf(2, 0, 2, 0) to intArrayOf(1, 4),
            output = -1
        )
    )

    override fun solve(testCaseInput: Pair<IntArray, IntArray>): Long {
        return minSum(testCaseInput.first, testCaseInput.second)
    }

    private fun minSum(nums1: IntArray, nums2: IntArray): Long {
        var sum1 = 0L
        var zero1 = 0
        for (num in nums1) {
            if (num == 0) zero1++
            sum1 += num
        }

        var sum2 = 0L
        var zero2 = 0
        for (num in nums2) {
            if (num == 0) zero2++
            sum2 += num
        }

        val min1 = sum1 + zero1
        val min2 = sum2 + zero2

        return when {
            zero1 == 0 && zero2 == 0 -> if (sum1 == sum2) sum1 else -1
            zero1 == 0 -> if (min2 <= sum1) sum1 else -1
            zero2 == 0 -> if (min1 <= sum2) sum2 else -1
            else -> maxOf(min1, min2)
        }
    }
}
