package dev.mslalith.twopointers.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem

class PartitionArrayAccordingToGivenPivot : TestCaseProblem<Pair<IntArray, Int>, IntArray>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = PartitionArrayAccordingToGivenPivot().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<Pair<IntArray, Int>, IntArray>> = arrayOf(
        TestCase(
            input = intArrayOf(9, 12, 5, 10, 14, 3, 10) to 10,
            output = intArrayOf(9, 5, 3, 10, 10, 12, 14)
        ),
        TestCase(
            input = intArrayOf(-3, 4, 3, 2) to 2,
            output = intArrayOf(-3, 2, 4, 3)
        )
    )

    override fun solve(testCaseInput: Pair<IntArray, Int>): IntArray {
        return pivotArray(testCaseInput.first, testCaseInput.second)
    }

    private fun pivotArray(nums: IntArray, pivot: Int): IntArray {
        val n = nums.size
        val result = IntArray(n)

        var idx = 0

        for (num in nums) if (num < pivot) result[idx++] = num
        while (idx < n) result[idx++] = pivot

        idx = n - 1
        for (i in (n - 1) downTo 0) if (nums[i] > pivot) result[idx--] = nums[i]

        return result
    }
}
