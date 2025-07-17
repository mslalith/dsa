package dev.mslalith.dp.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem

class FindMaximumLengthOfValidSubsequenceII : TestCaseProblem<Pair<IntArray, Int>, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = FindMaximumLengthOfValidSubsequenceII().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<Pair<IntArray, Int>, Int>> = arrayOf(
        TestCase(
            input = intArrayOf(1, 2, 3, 4, 5) to 2,
            output = 5
        ),
        TestCase(
            input = intArrayOf(1, 4, 2, 3, 1, 4) to 3,
            output = 4
        ),
        TestCase(
            input = intArrayOf(2, 10) to 7,
            output = 2
        )
    )

    override fun solve(testCaseInput: Pair<IntArray, Int>): Int {
        return maximumLength(testCaseInput.first, testCaseInput.second)
    }

    fun maximumLength(nums: IntArray, k: Int): Int {
        val dp = Array(1001) { IntArray(1001) }
        var ans = 0

        for (i in nums.indices) {
            for (j in 0..<i) {
                val t = (nums[i] + nums[j]) % k
                dp[i][t] = dp[j][t] + 1
                ans = maxOf(ans, dp[i][t])
            }
        }

        return ans + 1
    }
}
