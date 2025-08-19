package dev.mslalith.array.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem

class NumberOfZeroFilledSubarrays : TestCaseProblem<IntArray, Long>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = NumberOfZeroFilledSubarrays().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<IntArray, Long>> = arrayOf(
        TestCase(
            input = intArrayOf(1, 3, 0, 0, 2, 0, 0, 4),
            output = 6
        ),
        TestCase(
            input = intArrayOf(0, 0, 0, 2, 0, 0),
            output = 9
        ),
        TestCase(
            input = intArrayOf(2, 10, 2019),
            output = 0
        )
    )

    override fun solve(testCaseInput: IntArray): Long {
        return zeroFilledSubarray(testCaseInput)
    }

    private fun zeroFilledSubarray(nums: IntArray): Long {
        var count = 0L
        var streak = 0L

        for (num in nums) {
            streak = if (num == 0) streak + 1 else 0
            count += streak
        }

        return count
    }
}
