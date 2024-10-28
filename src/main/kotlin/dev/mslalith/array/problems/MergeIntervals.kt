package dev.mslalith.array.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.utils.createClone
import kotlin.math.max
import kotlin.math.min

class MergeIntervals : TestCaseProblem<Array<IntArray>, Array<IntArray>>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = MergeIntervals().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<Array<IntArray>, Array<IntArray>>> = arrayOf(
        TestCase(
            input = arrayOf(
                intArrayOf(1, 3),
                intArrayOf(2, 6),
                intArrayOf(8, 10),
                intArrayOf(15, 18)
            ),
            output = arrayOf(
                intArrayOf(1, 6),
                intArrayOf(8, 10),
                intArrayOf(15, 18)
            )
        ),
        TestCase(
            input = arrayOf(
                intArrayOf(1, 4),
                intArrayOf(4, 5)
            ),
            output = arrayOf(
                intArrayOf(1, 5)
            )
        ),
        TestCase(
            input = arrayOf(
                intArrayOf(1, 4),
                intArrayOf(0, 4)
            ),
            output = arrayOf(
                intArrayOf(0, 4)
            )
        ),
        TestCase(
            input = arrayOf(
                intArrayOf(2, 3),
                intArrayOf(4, 5),
                intArrayOf(6, 7),
                intArrayOf(8, 9),
                intArrayOf(1, 10)
            ),
            output = arrayOf(
                intArrayOf(1, 10)
            )
        )
    )

    override fun solve(testCaseInput: Array<IntArray>): Array<IntArray> {
        return merge(testCaseInput.createClone())
    }

    private fun merge(intervals: Array<IntArray>): Array<IntArray> {
        intervals.sortWith { a, b -> a[0].compareTo(b[0]) }
        val result = mutableListOf(intervals[0])

        for (i in 1 until intervals.size) {
            val last = result.last()
            val curr = intervals[i]
            if (curr[0] > last[1]) result.add(curr) else {
                last[0] = min(last[0], curr[0])
                last[1] = max(last[1], curr[1])
            }
        }

        return result.toTypedArray()
    }
}
