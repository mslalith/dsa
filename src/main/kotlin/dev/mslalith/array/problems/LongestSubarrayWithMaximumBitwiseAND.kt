package dev.mslalith.array.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem

class LongestSubarrayWithMaximumBitwiseAND : TestCaseProblem<IntArray, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = LongestSubarrayWithMaximumBitwiseAND().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<IntArray, Int>> = arrayOf(
        TestCase(
            input = intArrayOf(1, 2, 3, 3, 2, 2),
            output = 2
        ),
        TestCase(
            input = intArrayOf(1, 2, 3, 4),
            output = 1
        )
    )

    override fun solve(testCaseInput: IntArray): Int {
        return longestSubarray(testCaseInput)
    }

    private fun longestSubarray(nums: IntArray): Int {
        if (nums.isEmpty()) return 0

        val maxVal = nums.max()
        var maxLen = 0
        var currentLen = 0

        for (num in nums) {
            if (num == maxVal) {
                currentLen++
            } else {
                maxLen = maxOf(maxLen, currentLen)
                currentLen = 0
            }
        }

        return maxOf(maxLen, currentLen)
    }
}
