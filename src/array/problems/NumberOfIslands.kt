package src.array.problems

import src.core.Problem
import src.core.TestCase
import src.utils.stringFromArray

class NumberOfIslands : Problem<NumberOfIslandsParams, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = NumberOfIslands().run()
    }

    override fun getTestCases(): Array<TestCase<NumberOfIslandsParams, Int>> = arrayOf(
        TestCase(
            input = NumberOfIslandsParams(
                grid = arrayOf(
                    charArrayOf('1', '1', '1', '1', '0'),
                    charArrayOf('1', '1', '0', '1', '0'),
                    charArrayOf('1', '1', '0', '0', '0'),
                    charArrayOf('0', '0', '0', '0', '0'),
                )
            ),
            output = 1
        ),
        TestCase(
            input = NumberOfIslandsParams(
                grid = arrayOf(
                    charArrayOf('1', '1', '0', '0', '0'),
                    charArrayOf('1', '1', '0', '0', '0'),
                    charArrayOf('0', '0', '1', '0', '0'),
                    charArrayOf('0', '0', '0', '1', '1')
                )
            ),
            output = 3
        )
    )

    override fun solve(testCaseInput: NumberOfIslandsParams): Int {
        return numIslands(testCaseInput.grid)
    }

    private fun numIslands(grid: Array<CharArray>): Int {
        val m = grid.size
        val n = grid.first().size
        val visited = Array(m) { Array(n) { false } }
        return numIslands(grid, visited, m, n)
    }

    private fun numIslands(grid: Array<CharArray>, visited: Array<Array<Boolean>>, m: Int, n: Int): Int {
        var islandCount = 0
        for (i in 0 until m) {
            for (j in 0 until n) {
                if (!visited[i][j] && grid[i][j] != '0') {
                    islandCount++
                    markVisited(grid, visited, m, n, i, j)
                }
            }
        }
        return islandCount
    }

    private fun markVisited(
        grid: Array<CharArray>,
        visited: Array<Array<Boolean>>,
        m: Int,
        n: Int,
        row: Int,
        column: Int
    ) {
        if (row < 0 || row >= m) return
        if (column < 0 || column >= n) return
        if (visited[row][column]) return
        if (grid[row][column] == '0') return

        visited[row][column] = true
        markVisited(grid, visited, m, n, row + 1, column)
        markVisited(grid, visited, m, n, row - 1, column)
        markVisited(grid, visited, m, n, row, column + 1)
        markVisited(grid, visited, m, n, row, column - 1)
    }
}

data class NumberOfIslandsParams(
    val grid: Array<CharArray>
) {
    override fun toString(): String {
        return stringFromArray(grid)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as NumberOfIslandsParams

        return grid.contentDeepEquals(other.grid)
    }

    override fun hashCode(): Int {
        return grid.contentDeepHashCode()
    }
}