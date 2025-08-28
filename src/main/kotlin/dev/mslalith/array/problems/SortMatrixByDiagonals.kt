package dev.mslalith.array.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem

class SortMatrixByDiagonals : TestCaseProblem<Array<IntArray>, Array<IntArray>>() {
    
    companion object {
        @JvmStatic
        fun main(args: Array<String>) = SortMatrixByDiagonals().runForConsole()
    }
    
    override fun getTestCases(): Array<TestCase<Array<IntArray>, Array<IntArray>>> = arrayOf(
        TestCase(
            input = arrayOf(
                intArrayOf(1, 7, 3),
                intArrayOf(9, 8, 2),
                intArrayOf(4, 5, 6)
            ),
            output = arrayOf(
                intArrayOf(8, 2, 3),
                intArrayOf(9, 6, 7),
                intArrayOf(4, 5, 1)
            )
        ),
        TestCase(
            input = arrayOf(
                intArrayOf(0, 1),
                intArrayOf(1, 2)
            ),
            output = arrayOf(
                intArrayOf(2, 1),
                intArrayOf(1, 0)
            )
        ),
        TestCase(
            input = arrayOf(
                intArrayOf(1)
            ),
            output = arrayOf(
                intArrayOf(1)
            )
        )
    )
    
    override fun solve(testCaseInput: Array<IntArray>): Array<IntArray> {
        return sortMatrix(testCaseInput)
    }

    private fun sortMatrix(grid: Array<IntArray>): Array<IntArray> {
        val m = grid.size
        val n = grid[0].size
        val result = Array(m) { IntArray(n) }

        // bottom left triangle
        for (row in 0..<m) {
            val elements = mutableListOf<Int>()
            var i = row
            var j = 0

            while (i < m && j < n) elements += grid[i++][j++]
            elements.sortDescending()

            i = row
            j = 0
            for (k in elements) result[i++][j++] = k
        }

        // top right triangle
        for (col in 1..<n) {
            val elements = mutableListOf<Int>()
            var i = 0
            var j = col

            while (i < m && j < n) elements += grid[i++][j++]
            elements.sort()

            i = 0
            j = col
            for (k in elements) result[i++][j++] = k
        }

        return result
    }
}
