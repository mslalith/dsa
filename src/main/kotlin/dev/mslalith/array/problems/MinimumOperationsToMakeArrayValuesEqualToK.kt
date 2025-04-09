package dev.mslalith.array.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem

class MinimumOperationsToMakeArrayValuesEqualToK : TestCaseProblem<Pair<IntArray, Int>, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = MinimumOperationsToMakeArrayValuesEqualToK().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<Pair<IntArray, Int>, Int>> = arrayOf(
        TestCase(
            input = intArrayOf(5, 2, 5, 4, 5) to 2,
            output = 2
        ),
        TestCase(
            input = intArrayOf(2, 1, 2) to 2,
            output = -1
        ),
        TestCase(
            input = intArrayOf(9, 7, 5, 3) to 1,
            output = 4
        )
    )

    override fun solve(testCaseInput: Pair<IntArray, Int>): Int {
        return minOperations(testCaseInput.first, testCaseInput.second)
    }

    private fun minOperations(nums: IntArray, k: Int): Int {
        val set = hashSetOf<Int>()

        for (num in nums) {
            if (num < k) return -1
            if (num != k) set += num
        }

        return set.size
    }
}
