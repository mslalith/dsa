package dev.mslalith.array.problems

import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.core.TestCase
import kotlin.math.max

class MaximumAverageSubarrayI : TestCaseProblem<Pair<IntArray, Int>, Double>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = MaximumAverageSubarrayI().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<Pair<IntArray, Int>, Double>> = arrayOf(
        TestCase(
            input = intArrayOf(1, 12, -5, -6, 50, 3) to 4,
            output = 12.75
        ),
        TestCase(
            input = intArrayOf(5) to 1,
            output = 5.0
        ),
        TestCase(
            input = intArrayOf(-1) to 1,
            output = -1.0
        )
    )

    override fun solve(testCaseInput: Pair<IntArray, Int>): Double {
        return findMaxAverage(testCaseInput.first, testCaseInput.second)
    }

    private fun findMaxAverage(nums: IntArray, k: Int): Double {
        val n = nums.size
        var left = 0
        var right = 0

        var sum = 0.0
        var maxAvg = Int.MIN_VALUE.toDouble()

        while ((right + 1) <= k) {
            sum += nums[right]
            right++
        }
        right--

        while (right < n) {
            maxAvg = max(maxAvg, sum / k)
            sum -= nums[left++]
            right++
            if (right < n) sum += nums[right]
        }

        return maxAvg
    }

    private fun findMaxAverageNaive(nums: IntArray, k: Int): Double {
        return nums
            .toList()
            .windowed(k)
            .maxOf { it.sum().toDouble() / it.size }
    }
}
