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

        for (i in 1 until n) {
            for (j in 0 until i) {
                if (nums[j] < nums[i]) dp[i] = max(dp[i], dp[j] + 1)
            }
        }

        return dp.max()
    }

    private fun lengthOfLISRecursive(nums: IntArray): Int {
        val n = nums.size
        val dp = Array(n) { IntArray(n + 1) { -1 } }

        fun findLengthOfLIS(i: Int, prevI: Int): Int {
            if (i == n) return 0
            if (dp[i][prevI + 1] != -1) return dp[i][prevI + 1]

            val notTake = findLengthOfLIS(i + 1, prevI)
            val take = if (prevI == -1 || nums[prevI] < nums[i]) {
                1 + findLengthOfLIS(i + 1, i)
            } else 0

            dp[i][prevI + 1] = max(notTake, take)
            return dp[i][prevI + 1]
        }

        return findLengthOfLIS(0, -1)
    }
}
