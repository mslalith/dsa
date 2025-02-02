package dev.mslalith.array.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem

class CheckIfArrayIsSortedAndRotated : TestCaseProblem<IntArray, Boolean>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = CheckIfArrayIsSortedAndRotated().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<IntArray, Boolean>> = arrayOf(
        TestCase(
            input = intArrayOf(3, 4, 5, 1, 2),
            output = true
        ),
        TestCase(
            input = intArrayOf(2, 1, 3, 4),
            output = false
        ),
        TestCase(
            input = intArrayOf(1, 2, 3),
            output = true
        ),
        TestCase(
            input = intArrayOf(1, 1, 1),
            output = true
        ),
        TestCase(
            input = intArrayOf(6, 10, 6),
            output = true
        ),
        TestCase(
            input = intArrayOf(7, 9, 1, 1, 1, 3),
            output = true
        ),
        TestCase(
            input = intArrayOf(3, 1, 2, 2, 4),
            output = false
        )
    )

    override fun solve(testCaseInput: IntArray): Boolean {
        return check(testCaseInput)
    }

    private fun check(nums: IntArray): Boolean {
        val n = nums.size

        var i = 0
        while (i < n - 1 && nums[i] <= nums[i + 1]) i++

        if (i == n - 1) return true

        i++
        while (i < n - 1 && nums[i] <= nums[i + 1] && nums[i] <= nums[0]) i++

        return i == n - 1 && nums[i] <= nums[0]
    }
}