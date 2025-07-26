package dev.mslalith.array.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem

class MaximizeSubarraysAfterRemovingOneConflictingPair : TestCaseProblem<Pair<Array<IntArray>, Int>, Long>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = MaximizeSubarraysAfterRemovingOneConflictingPair().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<Pair<Array<IntArray>, Int>, Long>> = arrayOf(
        TestCase(
            input = arrayOf(
                intArrayOf(2, 3),
                intArrayOf(1, 4)
            ) to 4,
            output = 9
        ),
        TestCase(
            input = arrayOf(
                intArrayOf(1, 2),
                intArrayOf(2, 5),
                intArrayOf(3, 5)
            ) to 5,
            output = 12
        )
    )

    override fun solve(testCaseInput: Pair<Array<IntArray>, Int>): Long {
        return maxSubarrays(testCaseInput.second, testCaseInput.first)
    }

    private fun maxSubarrays(n: Int, conflictingPairs: Array<IntArray>): Long {
        val right = Array(n + 1) { mutableListOf<Int>() }
        for ((a, b) in conflictingPairs) right[maxOf(a, b)] += minOf(a, b)

        var ans = 0L
        val left = longArrayOf(0, 0)
        val bonus = LongArray(n + 1)

        for (r in 1..n) {
            for (leftValue in right[r]) {
                if (leftValue > left[0]) {
                    left[1] = left[0]
                    left[0] = leftValue.toLong()
                } else if (leftValue > left[1]) {
                    left[1] = leftValue.toLong()
                }
            }

            ans += r - left[0]

            if (left[0] > 0) {
                bonus[left[0].toInt()] += left[0] - left[1]
            }
        }

        return ans + bonus.max()
    }
}
