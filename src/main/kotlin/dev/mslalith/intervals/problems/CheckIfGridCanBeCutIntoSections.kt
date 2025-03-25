package dev.mslalith.intervals.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.utils.createClone
import kotlin.math.max

class CheckIfGridCanBeCutIntoSections : TestCaseProblem<Pair<Int, Array<IntArray>>, Boolean>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = CheckIfGridCanBeCutIntoSections().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<Pair<Int, Array<IntArray>>, Boolean>> = arrayOf(
        TestCase(
            input = 5 to arrayOf(
                intArrayOf(1, 0, 5, 2),
                intArrayOf(0, 2, 2, 4),
                intArrayOf(3, 2, 5, 3),
                intArrayOf(0, 4, 4, 5)
            ),
            output = true
        ),
        TestCase(
            input = 4 to arrayOf(
                intArrayOf(0, 0, 1, 1),
                intArrayOf(2, 0, 3, 4),
                intArrayOf(0, 2, 2, 3),
                intArrayOf(3, 0, 4, 3)
            ),
            output = true
        ),
        TestCase(
            input = 4 to arrayOf(
                intArrayOf(0, 2, 2, 4),
                intArrayOf(1, 0, 3, 2),
                intArrayOf(2, 2, 3, 4),
                intArrayOf(3, 0, 4, 2),
                intArrayOf(3, 2, 4, 4)
            ),
            output = false
        ),
        TestCase(
            input = 3 to arrayOf(
                intArrayOf(0, 0, 1, 3),
                intArrayOf(1, 0, 2, 2),
                intArrayOf(2, 0, 3, 2),
                intArrayOf(1, 2, 3, 3)
            ),
            output = false
        )
    )

    override fun solve(testCaseInput: Pair<Int, Array<IntArray>>): Boolean {
        return checkValidCuts(testCaseInput.first, testCaseInput.second.createClone())
    }

    private fun checkValidCuts(n: Int, rectangles: Array<IntArray>): Boolean {

        fun canCut(intervals: MutableList<IntArray>): Boolean {
            intervals.sortBy { it[0] }

            val mergedIntervals = mutableListOf(intervals[0])

            for (i in 1 until intervals.size) {
                val interval = intervals[i]
                val last = mergedIntervals.last()
                when {
                    last[1] <= interval[0] -> mergedIntervals += interval
                    else -> last[1] = max(last[1], interval[1])
                }
            }

            return mergedIntervals.size > 2
        }

        val horizontalIntervals = mutableListOf<IntArray>()
        val verticalIntervals = mutableListOf<IntArray>()
        for ((startX, startY, endX, endY) in rectangles) {
            horizontalIntervals += intArrayOf(startX, endX)
            verticalIntervals += intArrayOf(startY, endY)
        }

        return canCut(horizontalIntervals) || canCut(verticalIntervals)
    }
}
