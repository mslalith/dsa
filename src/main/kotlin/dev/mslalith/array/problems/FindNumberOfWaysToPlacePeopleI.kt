package dev.mslalith.array.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.utils.createClone
import java.util.*


class FindNumberOfWaysToPlacePeopleI : TestCaseProblem<Array<IntArray>, Int>() {
    
    companion object {
        @JvmStatic
        fun main(args: Array<String>) = FindNumberOfWaysToPlacePeopleI().runForConsole()
    }
    
    override fun getTestCases(): Array<TestCase<Array<IntArray>, Int>> = arrayOf(
        TestCase(
            input = arrayOf(
                intArrayOf(1, 1),
                intArrayOf(2, 2),
                intArrayOf(3, 3)
            ),
            output = 0
        ),
        TestCase(
            input = arrayOf(
                intArrayOf(6, 2),
                intArrayOf(4, 4),
                intArrayOf(2, 6)
            ),
            output = 2
        ),
        TestCase(
            input = arrayOf(
                intArrayOf(3, 1),
                intArrayOf(1, 3),
                intArrayOf(1, 1)
            ),
            output = 2
        )
    )
    
    override fun solve(testCaseInput: Array<IntArray>): Int {
        return numberOfPairs(testCaseInput.createClone())
    }

    private fun numberOfPairs(points: Array<IntArray>): Int {
        points.sortWith { a, b -> if (a[0] == b[0]) b[1] - a[1] else a[0] - b[0] }

        val n = points.size
        var result = 0

        for (i in 0..<n) {
            val top = points[i][1]
            var bot = Int.MIN_VALUE

            for (j in (i + 1)..<n) {
                val y = points[j][1]
                if (y in (bot + 1)..top) {
                    result++
                    bot = y
                    if (bot == top) break
                }
            }
        }

        return result
    }
}
