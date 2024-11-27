package dev.mslalith.dp.problems

import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.core.TestCase
import kotlin.math.max

class HouseRobber : TestCaseProblem<IntArray, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = HouseRobber().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<IntArray, Int>> = arrayOf(
        TestCase(
            input = intArrayOf(1, 2, 3, 1),
            output = 4
        ),
        TestCase(
            input = intArrayOf(2, 7, 9, 3, 1),
            output = 12
        ),
        TestCase(
            input = intArrayOf(2, 1, 1, 2),
            output = 4
        ),
        TestCase(
            input = intArrayOf(1, 3, 1),
            output = 3
        ),
        TestCase(
            input = intArrayOf(4, 1, 2, 7, 5, 3, 1),
            output = 14
        )
    )

    override fun solve(testCaseInput: IntArray): Int {
        return rob(testCaseInput)
    }

    private fun rob(nums: IntArray): Int {
        val n = nums.size

        var next = 0
        var later = 0

        for (i in (n - 1) downTo 0) {
            val notTake = next
            val take = nums[i] + later

            val curr = max(notTake, take)
            later = next
            next = curr
        }

        return next
    }

    private fun robDp(nums: IntArray): Int {
        val n = nums.size
        val dp = IntArray(n + 2)

        for (i in (n - 1) downTo 0) {
            val notTake = dp[i + 1]
            val take = nums[i] + dp[i + 2]

            dp[i] = max(notTake, take)
        }

        return dp[0]
    }

    private fun robRecursive(nums: IntArray): Int {
        val n = nums.size
        val dp = IntArray(n) { -1 }

        fun findRob(i: Int): Int {
            if (i >= n) return 0
            if (dp[i] != -1) return dp[i]

            val notTake = findRob(i + 1)
            val take = nums[i] + findRob(i + 2)

            dp[i] = max(notTake, take)
            return dp[i]
        }

        return findRob(0)
    }
}
