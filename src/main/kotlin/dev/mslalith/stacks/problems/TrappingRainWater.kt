package dev.mslalith.stacks.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import kotlin.math.max
import kotlin.math.min

class TrappingRainWater : TestCaseProblem<IntArray, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = TrappingRainWater().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<IntArray, Int>> = arrayOf(
        TestCase(
            input = intArrayOf(0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1),
            output = 6
        ),
        TestCase(
            input = intArrayOf(4, 2, 0, 3, 2, 5),
            output = 9
        )
    )

    override fun solve(testCaseInput: IntArray): Int {
        return trap(testCaseInput)
    }

    private fun trap(height: IntArray): Int {
        val n = height.size

        val prefix = IntArray(n)
        prefix[0] = height[0]
        for (i in 1 until n) {
            prefix[i] = max(prefix[i - 1], height[i])
        }

        val suffix = IntArray(n)
        suffix[n - 1] = height[n - 1]
        for (i in (n - 2) downTo 0) {
            suffix[i] = max(suffix[i + 1], height[i])
        }

        var trappedWater = 0

        for (i in 1 until (n - 1)) {
            val water = min(suffix[i], prefix[i]) - height[i]
            trappedWater += water
        }

        return trappedWater
    }
}
