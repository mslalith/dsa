package dev.mslalith.array.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.utils.createClone

class NonOverlappingIntervals : TestCaseProblem<Array<IntArray>, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = NonOverlappingIntervals().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<Array<IntArray>, Int>> = arrayOf(
        TestCase(
            input = arrayOf(
                intArrayOf(1, 2),
                intArrayOf(2, 3),
                intArrayOf(3, 4),
                intArrayOf(1, 3)
            ),
            output = 1
        ),
        TestCase(
            input = arrayOf(
                intArrayOf(1, 2),
                intArrayOf(1, 2),
                intArrayOf(1, 2)
            ),
            output = 2
        ),
        TestCase(
            input = arrayOf(
                intArrayOf(1, 2),
                intArrayOf(2, 3)
            ),
            output = 0
        ),
        TestCase(
            input = arrayOf(
                intArrayOf(-52, 31),
                intArrayOf(-73, -26),
                intArrayOf(82, 97),
                intArrayOf(-65, -11),
                intArrayOf(-62, -49),
                intArrayOf(95, 99),
                intArrayOf(58, 95),
                intArrayOf(-31, 49),
                intArrayOf(66, 98),
                intArrayOf(-63, 2),
                intArrayOf(30, 47),
                intArrayOf(-40, -26)
            ),
            output = 7
        )
    )

    override fun solve(testCaseInput: Array<IntArray>): Int {
        return eraseOverlapIntervals(testCaseInput.createClone())
    }

    private fun eraseOverlapIntervals(intervals: Array<IntArray>): Int {
        if (intervals.isEmpty()) return 0

        intervals.sortWith { one, two ->
            one[1] - two[1]
        }

        var count = 0
        var prevEnd = intervals.first()[1]

        for (i in 1 until intervals.size) {
            val (start, end) = intervals[i]
            if (start >= prevEnd) prevEnd = end else count++
        }

        return count
    }
}
