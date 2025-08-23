package dev.mslalith.array.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem

class FindMinimumAreaToCoverAllOnesII : TestCaseProblem<Array<IntArray>, Int>() {
    
    companion object {
        @JvmStatic
        fun main(args: Array<String>) = FindMinimumAreaToCoverAllOnesII().runForConsole()
    }
    
    override fun getTestCases(): Array<TestCase<Array<IntArray>, Int>> = arrayOf(
        TestCase(
            input = arrayOf(
                intArrayOf(1, 0, 1),
                intArrayOf(1, 1, 1)
            ),
            output = 5
        ),
        TestCase(
            input = arrayOf(
                intArrayOf(1, 0, 1, 0),
                intArrayOf(0, 1, 0, 1)
            ),
            output = 5
        )
    )
    
    override fun solve(testCaseInput: Array<IntArray>): Int {
        return minimumSum(testCaseInput)
    }

    private fun minimumSum(grid: Array<IntArray>): Int {
        val rotated = rotate90(grid)
        return minOf(solveProblem(grid), solveProblem(rotated))
    }

    private fun solveProblem(grid: Array<IntArray>): Int {
        val n = grid.size
        val m = grid[0].size
        var best = n * m

        var row = 0
        while (row + 1 < n) {
            var col = 0
            while (col + 1 < m) {
                val area1 = (boundingBoxArea(grid, 0, row, 0, m - 1) + boundingBoxArea(grid, row + 1, n - 1, 0, col)
                        + boundingBoxArea(grid, row + 1, n - 1, col + 1, m - 1))

                val area2 = (boundingBoxArea(grid, 0, row, 0, col) + boundingBoxArea(grid, 0, row, col + 1, m - 1)
                        + boundingBoxArea(grid, row + 1, n - 1, 0, m - 1))

                best = minOf(best, minOf(area1, area2))
                col++
            }
            row++
        }

        var r1 = 0
        while (r1 + 2 < n) {
            var r2 = r1 + 1
            while (r2 + 1 < n) {
                val area = (boundingBoxArea(grid, 0, r1, 0, m - 1) + boundingBoxArea(grid, r1 + 1, r2, 0, m - 1)
                        + boundingBoxArea(grid, r2 + 1, n - 1, 0, m - 1))
                best = minOf(best, area)
                r2++
            }
            r1++
        }
        return best
    }

    private fun boundingBoxArea(grid: Array<IntArray>, u: Int, d: Int, l: Int, r: Int): Int {
        var minRow = grid.size
        var maxRow = -1
        var minCol = grid[0].size
        var maxCol = -1

        for (i in u..d) {
            for (j in l..r) {
                if (grid[i][j] == 1) {
                    minRow = minOf(minRow, i)
                    maxRow = maxOf(maxRow, i)
                    minCol = minOf(minCol, j)
                    maxCol = maxOf(maxCol, j)
                }
            }
        }

        if (maxRow == -1) return Int.MAX_VALUE / 3

        return (maxRow - minRow + 1) * (maxCol - minCol + 1)
    }

    private fun rotate90(grid: Array<IntArray>): Array<IntArray> {
        val n = grid.size
        val m = grid[0].size
        val rotated = Array(m) { IntArray(n) }

        for (i in 0..<n) {
            for (j in 0..<m) {
                rotated[j][n - 1 - i] = grid[i][j]
            }
        }

        return rotated
    }
}
