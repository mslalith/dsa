package dev.mslalith.array.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import kotlin.math.min

class MinimumDominoRotationsForEqualRow : TestCaseProblem<Pair<IntArray, IntArray>, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = MinimumDominoRotationsForEqualRow().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<Pair<IntArray, IntArray>, Int>> = arrayOf(
        TestCase(
            input = intArrayOf(2, 1, 2, 4, 2, 2) to intArrayOf(5, 2, 6, 2, 3, 2),
            output = 2
        ),
        TestCase(
            input = intArrayOf(3, 5, 1, 2, 3) to intArrayOf(3, 6, 3, 3, 4),
            output = -1
        ),
        TestCase(
            input = intArrayOf(1, 2, 1, 1, 1, 2, 2, 2) to intArrayOf(2, 1, 2, 2, 2, 2, 2, 2),
            output = 1
        )
    )

    override fun solve(testCaseInput: Pair<IntArray, IntArray>): Int {
        return minDominoRotations(testCaseInput.first, testCaseInput.second)
    }

    private fun minDominoRotations(tops: IntArray, bottoms: IntArray): Int {
        val n = tops.size

        for (domino in 1..6) {
            var top = 0
            var bottom = 0
            var count = 0

            for (i in 0 until n) {
                if (tops[i] != domino && bottoms[i] != domino) break

                count++
                if (tops[i] == domino) top++
                if (bottoms[i] == domino) bottom++
            }

            if (count >= n) return min(n - top, n - bottom)
        }

        return -1
    }
}
