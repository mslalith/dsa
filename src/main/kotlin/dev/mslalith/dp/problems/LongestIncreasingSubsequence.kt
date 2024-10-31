package dev.mslalith.dp.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import kotlin.math.max


class LongestIncreasingSubsequence : TestCaseProblem<IntArray, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = LongestIncreasingSubsequence().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<IntArray, Int>> = arrayOf(
        TestCase(
            input = intArrayOf(10, 9, 2, 5, 3, 7, 101, 18),
            output = 4
        ),
        TestCase(
            input = intArrayOf(0, 1, 0, 3, 2, 3),
            output = 4
        ),
        TestCase(
            input = intArrayOf(7, 7, 7, 7, 7, 7, 7),
            output = 1
        )
    )

    override fun solve(testCaseInput: IntArray): Int {
        return lengthOfLIS(testCaseInput)
    }

    private fun lengthOfLIS(nums: IntArray): Int {
        val n = nums.size
        val dp = IntArray(n) { 1 }

        for (j in 1 until n) {
            for (i in 0 until j) {
                if (nums[i] < nums[j]) dp[j] = max(dp[j], dp[i] + 1)
            }
        }

        return dp.max()
    }
}
