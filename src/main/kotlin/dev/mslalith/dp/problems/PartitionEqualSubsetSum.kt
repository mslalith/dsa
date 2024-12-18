package dev.mslalith.dp.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem


class PartitionEqualSubsetSum : TestCaseProblem<IntArray, Boolean>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = PartitionEqualSubsetSum().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<IntArray, Boolean>> = arrayOf(
        TestCase(
            input = intArrayOf(1, 5, 11, 5),
            output = true
        ),
        TestCase(
            input = intArrayOf(1, 2, 3, 5),
            output = false
        ),
        TestCase(
            input = intArrayOf(100, 100, 100, 100, 100, 100, 100, 100),
            output = true
        ),
        TestCase(
            input = intArrayOf(1, 2, 3, 5, 17, 6, 14, 12, 6),
            output = true
        ),
        TestCase(
            input = intArrayOf(1, 5, 11, 3),
            output = false
        )
    )

    override fun solve(testCaseInput: IntArray): Boolean {
        return canPartition(testCaseInput)
    }

    private fun canPartition(nums: IntArray): Boolean {
        val totalSum = nums.sum()
        if (totalSum % 2 != 0) return false

        return subsetSumToK(nums, totalSum / 2)
    }

    private fun subsetSumToK(nums: IntArray, k: Int): Boolean {
        val n = nums.size
        val curr = BooleanArray(k + 1)
        var prev = BooleanArray(k + 1)

        for (i in 0 until n) prev[0] = true
        if (nums[0] <= k) prev[nums[0]] = true

        for (i in 1 until n) {
            for (target in 1..k) {
                val notTake = prev[target]
                val take = if (nums[i] > target) false else {
                    prev[target - nums[i]]
                }

                curr[target] = notTake || take
            }
            prev = curr.clone()
        }

        return prev[k]
    }

    private fun subsetSumToKDp(nums: IntArray, k: Int): Boolean {
        val n = nums.size
        val dp = Array(n) { BooleanArray(k + 1) }

        for (i in 0 until n) dp[i][0] = true
        if (nums[0] <= k) dp[0][nums[0]] = true

        for (i in 1 until n) {
            for (target in 1..k) {
                val notTake = dp[i - 1][target]
                val take = if (nums[i] > target) false else {
                    dp[i - 1][target - nums[i]]
                }

                dp[i][target] = notTake || take
            }
        }

        return dp[n - 1][k]
    }

    private fun subsetSumToKRecursive(nums: IntArray, k: Int): Boolean {
        val n = nums.size
        val dp = Array(n) { IntArray(k + 1) { -1 } }

        fun findCanPartitionKSubsets(i: Int, target: Int): Boolean {
            if (target == 0) return true
            if (i == 0) return nums[0] == target
            if (dp[i][target] != -1) return dp[i][target] == 1

            val notTake = findCanPartitionKSubsets(i - 1, target)
            val take = if (nums[i] > target) false else {
                findCanPartitionKSubsets(i - 1, target - nums[i])
            }

            dp[i][target] = if (notTake || take) 1 else 0
            return dp[i][target] == 1
        }

        return findCanPartitionKSubsets(n - 1, k)
    }
}
