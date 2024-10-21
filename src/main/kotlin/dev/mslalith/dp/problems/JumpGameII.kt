package dev.mslalith.dp.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import kotlin.math.max


class JumpGameII : TestCaseProblem<IntArray, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = JumpGameII().runAll()
    }

    override fun getTestCases(): Array<TestCase<IntArray, Int>> = arrayOf(
        TestCase(
            input = intArrayOf(2, 3, 1, 1, 4),
            output = 2
        ),
        TestCase(
            input = intArrayOf(2, 3, 0, 1, 4),
            output = 2
        ),
        TestCase(
            input = intArrayOf(3, 4, 3, 2, 5, 4, 3),
            output = 3
        ),
        TestCase(
            input = intArrayOf(4, 1, 1, 3, 1, 1, 1),
            output = 2
        ),
        TestCase(
            input = intArrayOf(10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 1, 0),
            output = 2
        )
    )

    override fun solve(testCaseInput: IntArray): Int {
        return jump(testCaseInput)
    }

    private fun jump(nums: IntArray): Int {
        val n = nums.size
        if (n == 1) return 0

        var left = 0
        var right = 0
        var jumps = 0

        while (right < n - 1) {
            var maxNum = 0
            for (i in left..right) {
                maxNum = max(maxNum, i + nums[i])
            }
            left = right + 1
            right = maxNum
            jumps++
        }

        return jumps
    }
}
