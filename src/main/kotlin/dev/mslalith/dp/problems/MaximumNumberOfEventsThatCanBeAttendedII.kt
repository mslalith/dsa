package dev.mslalith.dp.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.utils.createClone

class MaximumNumberOfEventsThatCanBeAttendedII : TestCaseProblem<Pair<Array<IntArray>, Int>, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = MaximumNumberOfEventsThatCanBeAttendedII().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<Pair<Array<IntArray>, Int>, Int>> = arrayOf(
        TestCase(
            input = arrayOf(
                intArrayOf(1, 2, 4),
                intArrayOf(3, 4, 3),
                intArrayOf(2, 3, 1)
            ) to 2,
            output = 7
        ),
        TestCase(
            input = arrayOf(
                intArrayOf(1, 2, 4),
                intArrayOf(3, 4, 3),
                intArrayOf(2, 3, 10)
            ) to 2,
            output = 10
        ),
        TestCase(
            input = arrayOf(
                intArrayOf(1, 1, 1),
                intArrayOf(2, 2, 2),
                intArrayOf(3, 3, 3),
                intArrayOf(4, 4, 4)
            ) to 3,
            output = 9
        )
    )

    override fun solve(testCaseInput: Pair<Array<IntArray>, Int>): Int {
        return maxValue(testCaseInput.first.createClone(), testCaseInput.second)
    }

    private fun maxValue(events: Array<IntArray>, k: Int): Int {
        events.sortBy { it[0] }

        val n = events.size
        val dp = Array(n + 1) { IntArray(k + 1) { -1 } }

        fun findMaxValue(i: Int, toAttend: Int, prevEnd: Int): Int {
            if (i >= n || toAttend == 0) return 0
            if (dp[i][toAttend] != -1) return dp[i][toAttend]

            val notPick = findMaxValue(i + 1, toAttend, prevEnd)

            val (start, end, value) = events[i]

            var x = i + 1
            while (x < n) {
                if (events[x][0] > end) break
                x++
            }

            val pick = value + findMaxValue(x, toAttend - 1, end)

            dp[i][toAttend] = maxOf(notPick, pick)
            return dp[i][toAttend]
        }

        return findMaxValue(0, k, Int.MIN_VALUE)
    }
}
