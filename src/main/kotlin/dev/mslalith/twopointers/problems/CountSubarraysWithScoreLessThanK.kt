package dev.mslalith.twopointers.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem

class CountSubarraysWithScoreLessThanK : TestCaseProblem<Pair<IntArray, Long>, Long>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = CountSubarraysWithScoreLessThanK().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<Pair<IntArray, Long>, Long>> = arrayOf(
        TestCase(
            input = intArrayOf(2, 1, 4, 3, 5) to 10,
            output = 6
        ),
        TestCase(
            input = intArrayOf(1, 1, 1) to 5,
            output = 5
        )
    )

    override fun solve(testCaseInput: Pair<IntArray, Long>): Long {
        return countSubarrays(testCaseInput.first, testCaseInput.second)
    }

    private fun countSubarrays(nums: IntArray, k: Long): Long {
        var count = 0L
        var sum = 0L
        var left = 0

        for (right in nums.indices) {
            sum += nums[right]
            while (sum * (right - left + 1) >= k) sum -= nums[left++]
            count += right - left + 1
        }

        return count
    }

    private fun countSubarraysNaive(nums: IntArray, k: Long): Long {
        val n = nums.size
        var count = 0L

        for (i in 0 until n) {
            var sum = 0L
            for (j in i until n) {
                sum += nums[j]
                if (sum * (j - i + 1) >= k) break
                count++
            }
        }

        return count
    }
}