package dev.mslalith.graph.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.graph.impl.disjointset.DisjointSet
import dev.mslalith.graph.impl.disjointset.DisjointSetBySize
import kotlin.math.max

class CountServersThatCommunicate : TestCaseProblem<Array<IntArray>, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = CountServersThatCommunicate().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<Array<IntArray>, Int>> = arrayOf(
        TestCase(
            input = arrayOf(
                intArrayOf(1, 0),
                intArrayOf(0, 1)
            ),
            output = 0
        ),
        TestCase(
            input = arrayOf(
                intArrayOf(1, 0),
                intArrayOf(1, 1)
            ),
            output = 3
        ),
        TestCase(
            input = arrayOf(
                intArrayOf(1, 1, 0, 0),
                intArrayOf(0, 0, 1, 0),
                intArrayOf(0, 0, 1, 0),
                intArrayOf(0, 0, 0, 1)
            ),
            output = 4
        )
    )

    override fun solve(testCaseInput: Array<IntArray>): Int {
        return countServers(testCaseInput)
    }

    private fun countServers(grid: Array<IntArray>): Int {
        val m = grid.size
        val n = grid[0].size

        val rows = IntArray(m)
        val cols = IntArray(n)

        for (i in 0 until m) {
            for (j in 0 until n) {
                rows[i] += grid[i][j]
                cols[j] += grid[i][j]
            }
        }

        var count = 0

        for (i in 0 until m) {
            for (j in 0 until n) {
                if (grid[i][j] == 1 && (rows[i] > 1 || cols[j] > 1)) count++
            }
        }

        return count
    }
}
