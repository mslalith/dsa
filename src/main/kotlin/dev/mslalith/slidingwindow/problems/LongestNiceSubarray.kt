package dev.mslalith.slidingwindow.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import kotlin.math.max

class LongestNiceSubarray : TestCaseProblem<IntArray, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = LongestNiceSubarray().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<IntArray, Int>> = arrayOf(
        TestCase(
            input = intArrayOf(1, 3, 8, 48, 10),
            output = 3
        ),
        TestCase(
            input = intArrayOf(3, 1, 5, 11, 13),
            output = 1
        )
    )

    override fun solve(testCaseInput: IntArray): Int {
        return longestNiceSubarray(testCaseInput)
    }

    private fun longestNiceSubarray(nums: IntArray): Int {
        var left = 0
        var usedBits = 0
        var maxCount = 1

        for (right in nums.indices) {
            while ((usedBits and nums[right]) != 0) {
                usedBits = usedBits xor nums[left]
                left++
            }

            usedBits = usedBits or nums[right]
            maxCount = max(maxCount, right - left + 1)
        }

        return maxCount
    }
}