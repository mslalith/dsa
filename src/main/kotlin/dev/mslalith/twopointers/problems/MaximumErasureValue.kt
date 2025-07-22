package dev.mslalith.twopointers.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem

class MaximumErasureValue : TestCaseProblem<IntArray, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = MaximumErasureValue().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<IntArray, Int>> = arrayOf(
        TestCase(
            input = intArrayOf(4, 2, 4, 5, 6),
            output = 17
        ),
        TestCase(
            input = intArrayOf(5, 2, 1, 2, 5, 2, 1, 2, 5),
            output = 8
        )
    )

    override fun solve(testCaseInput: IntArray): Int {
        return maximumUniqueSubarray(testCaseInput)
    }

    private fun maximumUniqueSubarray(nums: IntArray): Int {
        val seen = hashSetOf<Int>()

        var left = 0
        var currentSum = 0
        var maxSum = 0

        for (right in nums.indices) {
            while (nums[right] in seen) {
                currentSum -= nums[left]
                seen -= nums[left]
                left++
            }

            currentSum += nums[right]
            seen += nums[right]

            if (currentSum > maxSum) maxSum = currentSum
        }

        return maxSum
    }
}
