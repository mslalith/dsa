package dev.mslalith.array.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem

class NumberOfWaysToSplitArray : TestCaseProblem<IntArray, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = NumberOfWaysToSplitArray().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<IntArray, Int>> = arrayOf(
        TestCase(
            input = intArrayOf(10, 4, -8, 7),
            output = 2
        ),
        TestCase(
            input = intArrayOf(2, 3, 1, 0),
            output = 2
        )
    )

    override fun solve(testCaseInput: IntArray): Int {
        return waysToSplitArray(testCaseInput)
    }

    private fun waysToSplitArray(nums: IntArray): Int {
        val n = nums.size

        var totalSum = 0L
        for (num in nums) totalSum += num

        var leftSum = 0L
        var count = 0

        for (i in 0 until (n - 1)) {
            leftSum += nums[i]
            val rightSum = totalSum - leftSum
            if (leftSum >= rightSum) count++
        }

        return count
    }
}
