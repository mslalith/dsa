package dev.mslalith.array.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.utils.createClone
import kotlin.math.max
import kotlin.math.min

class InsertInterval : TestCaseProblem<Pair<Array<IntArray>, IntArray>, Array<IntArray>>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = InsertInterval().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<Pair<Array<IntArray>, IntArray>, Array<IntArray>>> = arrayOf(
        TestCase(
            input = arrayOf(
                intArrayOf(1, 3),
                intArrayOf(6, 9)
            ) to intArrayOf(2, 5),
            output = arrayOf(
                intArrayOf(1, 5),
                intArrayOf(6, 9)
            )
        ),
        TestCase(
            input = arrayOf(
                intArrayOf(1, 2),
                intArrayOf(3, 5),
                intArrayOf(6, 7),
                intArrayOf(8, 10),
                intArrayOf(12, 16)
            ) to intArrayOf(4, 8),
            output = arrayOf(
                intArrayOf(1, 2),
                intArrayOf(3, 10),
                intArrayOf(12, 16)
            )
        ),
        TestCase(
            input = arrayOf(
                intArrayOf(1, 3),
                intArrayOf(6, 9)
            ) to intArrayOf(10, 12),
            output = arrayOf(
                intArrayOf(1, 3),
                intArrayOf(6, 9),
                intArrayOf(10, 12)
            )
        ),
        TestCase(
            input = arrayOf(
                intArrayOf(1, 5)
            ) to intArrayOf(0, 0),
            output = arrayOf(
                intArrayOf(0, 0),
                intArrayOf(1, 5)
            )
        ),
        TestCase(
            input = arrayOf(
                intArrayOf(1, 5)
            ) to intArrayOf(2, 3),
            output = arrayOf(
                intArrayOf(1, 5)
            )
        ),
        TestCase(
            input = arrayOf(
                intArrayOf(2, 5),
                intArrayOf(6, 7),
                intArrayOf(8, 9)
            ) to intArrayOf(0, 1),
            output = arrayOf(
                intArrayOf(0, 1),
                intArrayOf(2, 5),
                intArrayOf(6, 7),
                intArrayOf(8, 9)
            )
        ),
        TestCase(
            input = emptyArray<IntArray>() to intArrayOf(5, 7),
            output = arrayOf(
                intArrayOf(5, 7)
            )
        ),
        TestCase(
            input = arrayOf(
                intArrayOf(0,10),
                intArrayOf(14,14),
                intArrayOf(15,20)
            ) to intArrayOf(11, 11),
            output = arrayOf(
                intArrayOf(0,10),
                intArrayOf(11,11),
                intArrayOf(14,14),
                intArrayOf(15,20)
            )
        )
    )

    override fun solve(testCaseInput: Pair<Array<IntArray>, IntArray>): Array<IntArray> {
        return insert(testCaseInput.first.createClone(), testCaseInput.second)
    }

    private fun insert(intervals: Array<IntArray>, newInterval: IntArray): Array<IntArray> {
        val n = intervals.size
        if (n == 0) return arrayOf(newInterval)

        val result = mutableListOf<IntArray>()

        var i = 0
        while (i < n) {
            val interval = intervals[i]
            if (newInterval[0] <= interval[1]) break
            result.add(interval)
            i++
        }

        result.add(newInterval)

        while (i < n) {
            val interval = intervals[i]
            if (interval[0] > newInterval[1]) break
            newInterval[0] = min(newInterval[0], interval[0])
            newInterval[1] = max(newInterval[1], interval[1])
            i++
        }

        while (i < n) {
            result.add(intervals[i])
            i++
        }

        return result.toTypedArray()
    }

    private fun insertNaive(intervals: Array<IntArray>, newInterval: IntArray): Array<IntArray> {
        val n = intervals.size
        if (n == 0) return arrayOf(newInterval)

        val result = mutableListOf<IntArray>()

        if (newInterval[1] < intervals[0][0]) {
            result.add(newInterval)
            result.addAll(intervals)
            return result.toTypedArray()
        }

        var i = 0
        while (i < n && newInterval[0] > intervals[i][1]) {
            result.add(intervals[i])
            i++
        }

        if (i < n && newInterval[1] < intervals[i][0]) {
            result.add(newInterval)
            for (j in i until n) result.add(intervals[j])
            return result.toTypedArray()
        }

        if (i == n) {
            result.add(newInterval)
            return result.toTypedArray()
        }

        intervals[i][0] = min(newInterval[0], intervals[i][0])
        intervals[i][1] = max(newInterval[1], intervals[i][1])

        while (i < n) {
            val curr = intervals[i]
            if (result.isEmpty()) {
                result.add(curr)
                continue
            }
            val last = result.last()
            if (curr[0] > last[1]) result.add(curr) else {
                last[0] = min(last[0], curr[0])
                last[1] = max(last[1], curr[1])
            }
            i++
        }

        return result.toTypedArray()
    }
}
