package dev.mslalith.string.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import kotlin.math.abs

class MaximumManhattanDistanceAfterKChanges : TestCaseProblem<Pair<String, Int>, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = MaximumManhattanDistanceAfterKChanges().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<Pair<String, Int>, Int>> = arrayOf(
        TestCase(
            input = "NWSE" to 1,
            output = 3
        ),
        TestCase(
            input = "NSWWEW" to 3,
            output = 6
        )
    )

    override fun solve(testCaseInput: Pair<String, Int>): Int {
        return maxDistance(testCaseInput.first, testCaseInput.second)
    }

    private fun maxDistance(s: String, k: Int): Int {
        var ans = 0
        var north = 0
        var south = 0
        var east = 0
        var west = 0

        for (i in 0..<s.length) {
            when (s[i]) {
                'N' -> north++
                'S' -> south++
                'E' -> east++
                'W' -> west++
            }

            val x = abs(north - south)
            val y = abs(east - west)

            val manhattanDistance = x + y
            val distance = manhattanDistance + minOf(2 * k, i + 1 - manhattanDistance)

            ans = maxOf(ans, distance)
        }

        return ans
    }
}
