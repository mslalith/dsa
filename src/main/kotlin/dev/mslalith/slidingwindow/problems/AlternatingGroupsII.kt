package dev.mslalith.slidingwindow.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem

class AlternatingGroupsII : TestCaseProblem<Pair<IntArray, Int>, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = AlternatingGroupsII().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<Pair<IntArray, Int>, Int>> = arrayOf(
        TestCase(
            input = intArrayOf(0, 1, 0, 1, 0) to 3,
            output = 3
        ),
        TestCase(
            input = intArrayOf(0, 1, 0, 0, 1, 0, 1) to 6,
            output = 2
        ),
        TestCase(
            input = intArrayOf(1, 1, 0, 1) to 4,
            output = 0
        )
    )

    override fun solve(testCaseInput: Pair<IntArray, Int>): Int {
        return numberOfAlternatingGroups(testCaseInput.first, testCaseInput.second)
    }

    private fun numberOfAlternatingGroups(colors: IntArray, k: Int): Int {
        val n = colors.size

        var groupCount = 0
        var groupLength = 1

        // subtracting 2 because group should consist at-least min 3 values
        for (i in 1..(n + k - 2)) {
            when {
                // different colors, so valid group
                colors[i % n] != colors[((i - 1) + n) % n] -> groupLength++
                // same colors, so group ends
                else -> groupLength = 1
            }
            if (groupLength >= k) groupCount++
        }

        return groupCount
    }
}
