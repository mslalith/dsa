package dev.mslalith.graph.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import kotlin.math.max

class MaximumNumberOfFishInGrid : TestCaseProblem<Array<IntArray>, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = MaximumNumberOfFishInGrid().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<Array<IntArray>, Int>> = arrayOf(
        TestCase(
            input = arrayOf(
                intArrayOf(0, 2, 1, 0),
                intArrayOf(4, 0, 0, 3),
                intArrayOf(1, 0, 0, 4),
                intArrayOf(0, 3, 2, 0)
            ),
            output = 7
        ),
        TestCase(
            input = arrayOf(
                intArrayOf(1, 0, 0, 0),
                intArrayOf(0, 0, 0, 0),
                intArrayOf(0, 0, 0, 0),
                intArrayOf(0, 0, 0, 1)
            ),
            output = 1
        ),
        TestCase(
            input = arrayOf(
                intArrayOf(8, 6),
                intArrayOf(2, 6)
            ),
            output = 22
        )
    )

    override fun solve(testCaseInput: Array<IntArray>): Int {
        return findMaxFish(testCaseInput)
    }

    private fun findMaxFish(grid: Array<IntArray>): Int {
        val m = grid.size
        val n = grid[0].size
        val directions = arrayOf(1 to 0, -1 to 0, 0 to 1, 0 to -1)

        fun dfs(i: Int, j: Int, count: Int): Int {
            if (i < 0 || i >= m || j < 0 || j >= n) return 0
            if (grid[i][j] == 0) return 0

            var fishCaught = grid[i][j]
            grid[i][j] = 0

            for ((dx, dy) in directions) {
                val r = i + dx
                val c = j + dy
                fishCaught += dfs(r, c, count)
            }

            return fishCaught
        }

        var maxFishCaught = 0

        for (i in 0 until m) {
            for (j in 0 until n) {
                if (grid[i][j] != 0) maxFishCaught = max(maxFishCaught, dfs(i, j, 0))
            }
        }

        return maxFishCaught
    }
}
