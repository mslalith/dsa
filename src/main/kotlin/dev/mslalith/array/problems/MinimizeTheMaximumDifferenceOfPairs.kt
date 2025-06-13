package dev.mslalith.array.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.utils.createClone

class MinimizeTheMaximumDifferenceOfPairs : TestCaseProblem<Pair<IntArray, Int>, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = MinimizeTheMaximumDifferenceOfPairs().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<Pair<IntArray, Int>, Int>> = arrayOf(
        TestCase(
            input = intArrayOf(10, 1, 2, 7, 1, 3) to 2,
            output = 1
        ),
        TestCase(
            input = intArrayOf(4, 2, 1, 2) to 1,
            output = 0
        )
    )

    override fun solve(testCaseInput: Pair<IntArray, Int>): Int {
        return minimizeMax(testCaseInput.first.createClone(), testCaseInput.second)
    }

    private fun minimizeMax(nums: IntArray, p: Int): Int {
        if (p == 0) return 0

        nums.sort()
        val n  = nums.size

        var left = 0
        var right = nums[n - 1] - nums[0]

        while (left < right) {
            val mid = left + (right - left) / 2
            var pairs = 0
            var i = 1
            while (i < n) {
                if (nums[i] - nums[i - 1] <= mid) {
                    pairs++
                    i++
                }
                i++
            }
            if (pairs >= p) right = mid
            else left = mid + 1
        }

        return left
    }
}
