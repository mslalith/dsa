package dev.mslalith.array.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import kotlin.math.max

class MaximumAscendingSubarraySum : TestCaseProblem<IntArray, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = MaximumAscendingSubarraySum().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<IntArray, Int>> = arrayOf(
        TestCase(
            input = intArrayOf(10,20,30,5,10,50),
            output = 65
        ),
        TestCase(
            input = intArrayOf(10,20,30,40,50),
            output = 150
        ),
        TestCase(
            input = intArrayOf(12,17,15,13,10,11,12),
            output = 33
        )
    )

    override fun solve(testCaseInput: IntArray): Int {
        return maxAscendingSum(testCaseInput)
    }

    private fun maxAscendingSum(nums: IntArray): Int {
        val n = nums.size
        if (n == 1) return nums[0]

        var maxSum = nums[0]
        var currSum = nums[0]

        for (i in 1 until n) {
            if (nums[i - 1] < nums[i]) {
                currSum += nums[i]
            } else {
                maxSum = max(maxSum, currSum)
                currSum = nums[i]
            }
        }

        return max(currSum, maxSum)
    }
}
