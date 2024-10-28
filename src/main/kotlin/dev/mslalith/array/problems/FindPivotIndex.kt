package dev.mslalith.array.problems

import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.core.TestCase

class FindPivotIndex : TestCaseProblem<IntArray, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = FindPivotIndex().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<IntArray, Int>> = arrayOf(
        TestCase(
            input = intArrayOf(1, 7, 3, 6, 5, 6),
            output = 3
        ),
        TestCase(
            input = intArrayOf(1, 2, 3),
            output = -1
        ),
        TestCase(
            input = intArrayOf(2, 1, -1),
            output = 0
        )
    )

    override fun solve(testCaseInput: IntArray): Int {
        return pivotIndex(testCaseInput)
    }

    private fun pivotIndex(nums: IntArray): Int {
        if (nums.isEmpty()) return -1

        var sum = 0
        val prefixSum = nums.map { num -> sum += num; sum }
        sum = 0
        val suffixSum = nums.reversed().map { num -> sum += num; sum }.reversed()

        return prefixSum.indices.firstOrNull { prefixSum[it] == suffixSum[it] } ?: -1
    }
}