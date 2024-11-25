package dev.mslalith.array.problems

import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.core.TestCase
import kotlin.math.max
import kotlin.math.min

class ContainerWithMostWater : TestCaseProblem<IntArray, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = ContainerWithMostWater().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<IntArray, Int>> = arrayOf(
        TestCase(
            input = intArrayOf(1, 8, 6, 2, 5, 4, 8, 3, 7),
            output = 49
        ),
        TestCase(
            input = intArrayOf(1, 1),
            output = 1
        )
    )

    override fun solve(testCaseInput: IntArray): Int {
        return maxArea(testCaseInput)
    }

    private fun maxArea(height: IntArray): Int {
        var left = 0
        var right = height.size - 1
        var maxArea = 0

        while (left < right) {
            val area = (right - left) * (min(height[left], height[right]))
            maxArea = max(maxArea, area)

            if (height[left] < height[right]) left++ else right--
        }

        return maxArea
    }
}
