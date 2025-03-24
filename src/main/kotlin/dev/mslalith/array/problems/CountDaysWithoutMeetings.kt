package dev.mslalith.array.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.utils.createClone
import kotlin.math.max
import kotlin.math.min

class CountDaysWithoutMeetings : TestCaseProblem<Pair<Int, Array<IntArray>>, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = CountDaysWithoutMeetings().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<Pair<Int, Array<IntArray>>, Int>> = arrayOf(
        TestCase(
            input = 10 to arrayOf(
                intArrayOf(5, 7),
                intArrayOf(1, 3),
                intArrayOf(9, 10)
            ),
            output = 2
        ),
        TestCase(
            input = 5 to arrayOf(
                intArrayOf(2, 4),
                intArrayOf(1, 3)
            ),
            output = 1
        ),
        TestCase(
            input = 1 to arrayOf(
                intArrayOf(1, 6)
            ),
            output = 0
        )
    )

    override fun solve(testCaseInput: Pair<Int, Array<IntArray>>): Int {
        return countDays(testCaseInput.first, testCaseInput.second.createClone())
    }

    private fun countDays(days: Int, meetings: Array<IntArray>): Int {
        meetings.sortBy { it[0] }

        val mergedMeetings = mutableListOf(meetings[0])

        for (i in 1 until meetings.size) {
            val last = mergedMeetings.last()
            val curr = meetings[i]
            if (curr[0] > last[1]) mergedMeetings += curr else {
                last[0] = min(last[0], curr[0])
                last[1] = max(last[1], curr[1])
            }
        }

        val daysInMeetings = mergedMeetings.sumOf { it[1] - it[0] + 1 }
        return max(0, days - daysInMeetings)
    }
}
