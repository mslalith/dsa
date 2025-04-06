package dev.mslalith.dp.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.utils.createClone

class LargestDivisibleSubset : TestCaseProblem<IntArray, List<Int>>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = LargestDivisibleSubset().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<IntArray, List<Int>>> = arrayOf(
        TestCase(
            input = intArrayOf(1, 2, 3),
            output = listOf(1, 2)
        ),
        TestCase(
            input = intArrayOf(1, 2, 4, 8),
            output = listOf(1, 2, 4, 8)
        )
    )

    override fun solve(testCaseInput: IntArray): List<Int> {
        return largestDivisibleSubset(testCaseInput.createClone())
    }

    private fun largestDivisibleSubset(nums: IntArray): List<Int> {
        nums.sort()

        val n = nums.size
        val dp = Array(n) { mutableListOf(nums[it]) }

        for (i in 1 until n) {
            for (j in 0 until i) {
                if (nums[i] % nums[j] == 0 && dp[i].size < dp[j].size + 1) {
                    dp[i] = (dp[j] + nums[i]).toMutableList()
                }
            }
        }

        return dp.maxBy { it.size }
    }
}
