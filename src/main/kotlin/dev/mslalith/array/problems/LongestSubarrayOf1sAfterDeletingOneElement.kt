package dev.mslalith.array.problems

import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.core.TestCase
import kotlin.math.max

class LongestSubarrayOf1sAfterDeletingOneElement : TestCaseProblem<IntArray, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = LongestSubarrayOf1sAfterDeletingOneElement().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<IntArray, Int>> = arrayOf(
        TestCase(
            input = intArrayOf(1, 1, 0, 1),
            output = 3
        ),
        TestCase(
            input = intArrayOf(0, 1, 1, 1, 0, 1, 1, 0, 1),
            output = 5
        ),
        TestCase(
            input = intArrayOf(1, 1, 1),
            output = 2
        )
    )

    override fun solve(testCaseInput: IntArray): Int {
        return longestSubarray(testCaseInput)
    }

    private fun longestSubarray(nums: IntArray): Int {
        if (nums.isEmpty()) return 0

        var beforeCount = 0
        var afterCount = 0
        var maximum = 0
        var deleteZero = false

        for (num in nums) {
            if (num == 1) {
                if (!deleteZero) beforeCount++ else afterCount++
                maximum = max(maximum, beforeCount + afterCount)
            } else {
                if (!deleteZero) {
                    deleteZero = true
                } else {
                    maximum = max(maximum, beforeCount + afterCount)
                    beforeCount = afterCount
                    afterCount = 0
                }
            }
        }

        return if (maximum == nums.size) maximum - 1 else maximum
    }
}
