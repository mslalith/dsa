package dev.mslalith.array.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem

class FindSubsequenceOfLengthKWithLargestSum : TestCaseProblem<Pair<IntArray, Int>, IntArray>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = FindSubsequenceOfLengthKWithLargestSum().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<Pair<IntArray, Int>, IntArray>> = arrayOf(
        TestCase(
            input = intArrayOf(2, 1, 3, 3) to 2,
            output = intArrayOf(3, 3)
        ),
        TestCase(
            input = intArrayOf(-1, -2, 3, 4) to 3,
            output = intArrayOf(-1, 3, 4)
        ),
        TestCase(
            input = intArrayOf(3, 4, 3, 3) to 2,
            output = intArrayOf(3, 4)
        )
    )

    override fun solve(testCaseInput: Pair<IntArray, Int>): IntArray {
        return maxSubsequence(testCaseInput.first, testCaseInput.second)
    }

    private fun maxSubsequence(nums: IntArray, k: Int): IntArray {
        return nums
            .withIndex()
            .sortedByDescending { it.value }
            .take(k)
            .sortedBy { it.index }
            .map { it.value }
            .toIntArray()
    }
}
