package dev.mslalith.backtracking.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem

class CountNumberOfMaximumBitwiseORSubsets : TestCaseProblem<IntArray, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = CountNumberOfMaximumBitwiseORSubsets().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<IntArray, Int>> = arrayOf(
        TestCase(
            input = intArrayOf(3, 1),
            output = 2
        ),
        TestCase(
            input = intArrayOf(2, 2, 2),
            output = 7
        ),
        TestCase(
            input = intArrayOf(3, 2, 1, 5),
            output = 6
        )
    )

    override fun solve(testCaseInput: IntArray): Int {
        return countMaxOrSubsets(testCaseInput)
    }

    private fun countMaxOrSubsets(nums: IntArray): Int {

        fun backtrack(maxOR: Int, index: Int, currentOR: Int): Int {
            if (index == nums.size) return if (currentOR == maxOR) 1 else 0
            if (currentOR == maxOR) return 1 shl (nums.size - index)

            return backtrack(maxOR, index + 1, currentOR or nums[index]) +
                    backtrack(maxOR, index + 1, currentOR)
        }

        var maxOR = 0
        for (num in nums) maxOR = maxOR or num

        return backtrack(maxOR, 0, 0)
    }
}
