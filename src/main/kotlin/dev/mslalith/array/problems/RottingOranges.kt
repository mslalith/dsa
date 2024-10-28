package dev.mslalith.array.problems

import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.core.TestCase

class RottingOranges : TestCaseProblem<Array<IntArray>, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = RottingOranges().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<Array<IntArray>, Int>> = arrayOf(
        TestCase(
            input = arrayOf(
                intArrayOf(2, 1, 1),
                intArrayOf(1, 1, 0),
                intArrayOf(0, 1, 1)
            ),
            output = 4
        ),
        TestCase(
            input = arrayOf(
                intArrayOf(2, 1, 1),
                intArrayOf(0, 1, 1),
                intArrayOf(1, 0, 1)
            ),
            output = -1
        ),
        TestCase(
            input = arrayOf(
                intArrayOf(0, 2)
            ),
            output = 0
        ),
        TestCase(
            input = arrayOf(
                intArrayOf(0)
            ),
            output = 0
        )
    )

    override fun solve(testCaseInput: Array<IntArray>): Int {
        return orangesRotting(testCaseInput)
    }

    private fun orangesRotting(grid: Array<IntArray>): Int {
        val m = grid.size
        val n = grid[0].size

        val queue = ArrayDeque<Pair<Int, Int>>()
        var freshCount = 0

        for (i in 0 until m) {
            for (j in 0 until n) {
                when (grid[i][j]) {
                    1 -> freshCount++
                    2 -> queue.add(i to j)
                    else -> Unit
                }
            }
        }

        if (freshCount == 0) return 0

        var minutes = 0
        val directions = arrayOf(1 to 0, -1 to 0, 0 to 1, 0 to -1)

        while (queue.isNotEmpty()) {
            val size = queue.size

            for (i in 0 until size) {
                val (r, c) = queue.removeFirst()

                for ((dx, dy) in directions) {
                    val x = r + dx
                    val y = c + dy

                    if (x < 0 || x >= m || y < 0 || y >= n) continue

                    if (grid[x][y] == 1) {
                        freshCount--
                        grid[x][y] = 2
                        queue.add(x to y)
                    }
                }
            }

            if (queue.size > 0) minutes++
        }

        return if (freshCount > 0) -1 else minutes
    }
}
