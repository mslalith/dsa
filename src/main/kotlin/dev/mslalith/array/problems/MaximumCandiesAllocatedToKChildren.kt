package dev.mslalith.array.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem

class MaximumCandiesAllocatedToKChildren : TestCaseProblem<Pair<IntArray, Long>, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = MaximumCandiesAllocatedToKChildren().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<Pair<IntArray, Long>, Int>> = arrayOf(
        TestCase(
            input = intArrayOf(5, 8, 6) to 3,
            output = 5
        ),
        TestCase(
            input = intArrayOf(2, 5) to 11,
            output = 0
        ),
        TestCase(
            input = intArrayOf(4, 7, 5) to 4,
            output = 3
        )
    )

    override fun solve(testCaseInput: Pair<IntArray, Long>): Int {
        return maximumCandies(testCaseInput.first, testCaseInput.second)
    }

    private fun maximumCandies(candies: IntArray, k: Long): Int {
        var result = 0
        var left = 1
        var right = candies.max()

        while (left <= right) {
            val mid = left + (right - left) / 2

            var count = 0L
            for (candy in candies) count += candy / mid

            if (count >= k) {
                result = mid
                left = mid + 1
            } else {
                right = mid - 1
            }
        }

        return result
    }
}
