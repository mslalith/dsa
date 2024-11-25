package dev.mslalith.dp.problems

import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.core.TestCase
import kotlin.math.max

class HouseRobber : TestCaseProblem<IntArray, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = HouseRobber().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<IntArray, Int>> = arrayOf(
        TestCase(
            input = intArrayOf(1, 2, 3, 1),
            output = 4
        ),
        TestCase(
            input = intArrayOf(2, 7, 9, 3, 1),
            output = 12
        ),
        TestCase(
            input = intArrayOf(2, 1, 1, 2),
            output = 4
        ),
        TestCase(
            input = intArrayOf(1, 3, 1),
            output = 3
        ),
        TestCase(
            input = intArrayOf(4, 1, 2, 7, 5, 3, 1),
            output = 14
        )
    )

    override fun solve(testCaseInput: IntArray): Int {
        return rob(testCaseInput)
    }

    private fun rob(nums: IntArray): Int {
        if (nums.isEmpty()) return 0

        var rob2 = 0
        var rob1 = nums[0]

        for (i in 1 until nums.size) {
            val rob = nums[i] + rob2
            rob2 = rob1
            rob1 = max(rob1, rob)
        }

        return max(rob1, rob2)
    }
}
