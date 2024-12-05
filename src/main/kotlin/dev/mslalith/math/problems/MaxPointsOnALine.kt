package dev.mslalith.math.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import kotlin.math.max

class MaxPointsOnALine : TestCaseProblem<Array<IntArray>, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = MaxPointsOnALine().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<Array<IntArray>, Int>> = arrayOf(
        TestCase(
            input = arrayOf(
                intArrayOf(1, 1),
                intArrayOf(2, 2),
                intArrayOf(3, 3)
            ),
            output = 3
        ),
        TestCase(
            input = arrayOf(
                intArrayOf(1, 1),
                intArrayOf(3, 2),
                intArrayOf(5, 3),
                intArrayOf(4, 1),
                intArrayOf(2, 3),
                intArrayOf(1, 4)
            ),
            output = 4
        ),
        TestCase(
            input = arrayOf(
                intArrayOf(2, 3),
                intArrayOf(3, 3),
                intArrayOf(-5, 3)
            ),
            output = 3
        ),
        TestCase(
            input = arrayOf(
                intArrayOf(0, 1),
                intArrayOf(0, 0),
                intArrayOf(0, 4),
                intArrayOf(0, -2),
                intArrayOf(0, -1),
                intArrayOf(0, 3),
                intArrayOf(0, -4)
            ),
            output = 7
        )
    )

    override fun solve(testCaseInput: Array<IntArray>): Int {
        return maxPoints(testCaseInput)
    }

    private fun maxPoints(points: Array<IntArray>): Int {
        val n = points.size
        if (n == 1) return 1

        val map = hashMapOf<Double, Int>()
        var maxPoints = 0

        for (i in 0 until n) {
            val (x1, y1) = points[i]

            map.clear()
            var currentMax = 0

            for (j in (i + 1) until n) {
                val (x2, y2) = points[j]

                val dx = x2 - x1
                val dy = y2 - y1

                val slope = when {
                    dy == 0 -> 0.0
                    dx == 0 -> Int.MAX_VALUE.toDouble()
                    else -> dy / dx.toDouble()
                }

                map[slope] = map.getOrDefault(slope, 0) + 1
                currentMax = max(currentMax, map.getValue(slope) + 1)
            }

            maxPoints = max(maxPoints, currentMax)
        }

        return maxPoints
    }
}
