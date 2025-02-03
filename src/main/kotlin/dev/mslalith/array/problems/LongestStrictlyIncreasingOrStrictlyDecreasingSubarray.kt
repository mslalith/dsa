package dev.mslalith.array.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import kotlin.math.max

class LongestStrictlyIncreasingOrStrictlyDecreasingSubarray : TestCaseProblem<IntArray, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = LongestStrictlyIncreasingOrStrictlyDecreasingSubarray().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<IntArray, Int>> = arrayOf(
        TestCase(
            input = intArrayOf(1, 4, 3, 3, 2),
            output = 2
        ),
        TestCase(
            input = intArrayOf(3, 3, 3, 3),
            output = 1
        ),
        TestCase(
            input = intArrayOf(3, 2, 1),
            output = 3
        ),
        TestCase(
            input = intArrayOf(6, 6, 3),
            output = 2
        )
    )

    override fun solve(testCaseInput: IntArray): Int {
        return longestMonotonicSubarray(testCaseInput)
    }

    private fun longestMonotonicSubarray(nums: IntArray): Int {
        val n = nums.size
        if (n == 1) return 1

        var increasingCount = 1
        var decreasingCount = 1

        var currIncreasingIndex = 1
        var currDecreasingIndex = 1

        for (i in 1 until n) {
            if (nums[i - 1] < nums[i]) currIncreasingIndex++ else {
                increasingCount = max(increasingCount, currIncreasingIndex)
                currIncreasingIndex = 1
            }

            if (nums[i - 1] > nums[i]) currDecreasingIndex++ else {
                decreasingCount = max(decreasingCount, currDecreasingIndex)
                currDecreasingIndex = 1
            }
        }

        increasingCount = max(increasingCount, currIncreasingIndex)
        decreasingCount = max(decreasingCount, currDecreasingIndex)
        return max(increasingCount, decreasingCount)
    }
}
