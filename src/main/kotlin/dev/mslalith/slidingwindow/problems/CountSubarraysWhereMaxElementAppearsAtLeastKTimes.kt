package dev.mslalith.slidingwindow.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem

class CountSubarraysWhereMaxElementAppearsAtLeastKTimes : TestCaseProblem<Pair<IntArray, Int>, Long>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = CountSubarraysWhereMaxElementAppearsAtLeastKTimes().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<Pair<IntArray, Int>, Long>> = arrayOf(
        TestCase(
            input = intArrayOf(1, 3, 2, 3, 3) to 2,
            output = 6
        ),
        TestCase(
            input = intArrayOf(1, 4, 2, 1) to 3,
            output = 0
        )
    )

    override fun solve(testCaseInput: Pair<IntArray, Int>): Long {
        return countSubarrays(testCaseInput.first, testCaseInput.second)
    }

    private fun countSubarrays(nums: IntArray, k: Int): Long {
        val maxi = nums.max()
        var maxiCount = 0
        var left = 0
        var count = 0L

        for (right in nums.indices) {
            if (nums[right] == maxi) maxiCount++

            while (maxiCount >= k) {
                if (nums[left] == maxi) maxiCount--
                left++
            }
            count += left
        }

        return count
    }
}
