package dev.mslalith.array.problems

import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.core.TestCase

class MinimumNumberOfArrowsToBurstBalloons : TestCaseProblem<Array<IntArray>, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = MinimumNumberOfArrowsToBurstBalloons().runAll()
    }

    override fun getTestCases(): Array<TestCase<Array<IntArray>, Int>> = arrayOf(
        TestCase(
            input = arrayOf(
                intArrayOf(10, 16),
                intArrayOf(2, 8),
                intArrayOf(1, 6),
                intArrayOf(7, 12)
            ),
            output = 2
        ),
        TestCase(
            input = arrayOf(
                intArrayOf(1, 2),
                intArrayOf(3, 4),
                intArrayOf(5, 6),
                intArrayOf(7, 8)
            ),
            output = 4
        ),
        TestCase(
            input = arrayOf(
                intArrayOf(1, 2),
                intArrayOf(2, 3),
                intArrayOf(3, 4),
                intArrayOf(4, 5)
            ),
            output = 2
        )
    )

    override fun solve(testCaseInput: Array<IntArray>): Int {
        return findMinArrowShots(testCaseInput)
    }

    private fun findMinArrowShots(points: Array<IntArray>): Int {
        if (points.isEmpty()) return 0

        points.sortWith { one, two ->
            one[1].compareTo(two[1])
        }

        val n = points.size
        var prevEnd = points.first()[1]
        var count = n

        for (i in 1 until n) {
            val curr = points[i]
            if (curr[0] <= prevEnd) count--
            else prevEnd = curr[1]
        }

        return count
    }
}
