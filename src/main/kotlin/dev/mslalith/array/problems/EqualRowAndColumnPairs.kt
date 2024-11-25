package dev.mslalith.array.problems

import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.core.TestCase

class EqualRowAndColumnPairs : TestCaseProblem<Array<IntArray>, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = EqualRowAndColumnPairs().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<Array<IntArray>, Int>> = arrayOf(
        TestCase(
            input = arrayOf(
                intArrayOf(3, 2, 1),
                intArrayOf(1, 7, 6),
                intArrayOf(2, 7, 7)
            ),
            output = 1
        ),
        TestCase(
            input = arrayOf(
                intArrayOf(3, 1, 2, 2),
                intArrayOf(1, 4, 4, 5),
                intArrayOf(2, 4, 2, 2),
                intArrayOf(2, 4, 2, 2)
            ),
            output = 3
        ),
        TestCase(
            input = arrayOf(
                intArrayOf(3, 1, 2, 2),
                intArrayOf(1, 4, 4, 4),
                intArrayOf(2, 4, 2, 2),
                intArrayOf(2, 5, 2, 2)
            ),
            output = 3
        ),
        TestCase(
            input = arrayOf(
                intArrayOf(11, 1),
                intArrayOf(1, 11)
            ),
            output = 2
        )
    )

    override fun solve(testCaseInput: Array<IntArray>): Int {
        return equalPairs(testCaseInput)
    }

    private fun equalPairs(grid: Array<IntArray>): Int {
        val n = grid.size
        val map = linkedMapOf<String, Int>()
        val transposedGrid = Array(n) { IntArray(n) }
        val sb = StringBuilder()

        for (x in 0 until n) {
            sb.clear()
            for (y in 0 until n) {
                sb.append(grid[x][y])
                sb.append("-")
                transposedGrid[y][x] = grid[x][y]
            }

            val key = sb.toString()
            map[key] = map.getOrDefault(key, 0) + 1
        }

        var count = 0

        for (x in 0 until n) {
            sb.clear()
            for (y in 0 until n) {
                sb.append(transposedGrid[x][y])
                sb.append("-")
            }

            val key = sb.toString()
            if (key in map) count += map.getValue(key)
        }

        return count
    }
}
