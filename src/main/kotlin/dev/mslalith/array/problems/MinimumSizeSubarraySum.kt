package dev.mslalith.array.problems

import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.core.TestCase
import kotlin.math.min


class MinimumSizeSubarraySum : TestCaseProblem<Pair<Int, IntArray>, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = MinimumSizeSubarraySum().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<Pair<Int, IntArray>, Int>> = arrayOf(
        TestCase(
            input = 7 to intArrayOf(2, 3, 1, 2, 4, 3),
            output = 2
        ),
        TestCase(
            input = 4 to intArrayOf(1, 4, 4),
            output = 1
        ),
        TestCase(
            input = 11 to intArrayOf(1, 1, 1, 1, 1, 1, 1, 1),
            output = 0
        ),
        TestCase(
            input = 213 to intArrayOf(12, 28, 83, 4, 25, 26, 25, 2, 25, 25, 25, 12),
            output = 8
        ),
        TestCase(
            input = 11 to intArrayOf(1, 2, 3, 4, 5),
            output = 3
        )
    )

    override fun solve(testCaseInput: Pair<Int, IntArray>): Int {
        return minSubArrayLen(testCaseInput.first, testCaseInput.second.clone())
    }

    private fun minSubArrayLen(target: Int, nums: IntArray): Int {
        var minimum = Int.MAX_VALUE
        var sum = 0
        var left = 0

        for (right in nums.indices) {
            sum += nums[right]

            while (sum >= target) {
                minimum = min(minimum, right - left + 1)
                sum -= nums[left++]
            }
        }

        return if (minimum != Int.MAX_VALUE) minimum else 0
    }
}
