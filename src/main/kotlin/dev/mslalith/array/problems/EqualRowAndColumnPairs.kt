package dev.mslalith.array.problems

import dev.mslalith.core.Problem
import dev.mslalith.core.TestCase

class EqualRowAndColumnPairs : Problem<Array<IntArray>, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = EqualRowAndColumnPairs().run()
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
                intArrayOf(3,1,2,2),
                intArrayOf(1,4,4,4),
                intArrayOf(2,4,2,2),
                intArrayOf(2,5,2,2)
            ),
            output = 3
        )
    )

    override fun solve(testCaseInput: Array<IntArray>): Int {
        return equalPairs(testCaseInput)
    }

    private fun equalPairs(grid: Array<IntArray>): Int {
        val n = grid.size
        var count = 0

        val transposedGrid = Array(n) { IntArray(n) }
        for (x in 0 until n) {
            for (y in 0 until n)
                transposedGrid[y][x] = grid[x][y]
        }

        for (x in 0 until n) {
            for (y in 0 until n)
                if (grid[x] contentEquals transposedGrid[y]) count++
        }
        return count
    }
}
