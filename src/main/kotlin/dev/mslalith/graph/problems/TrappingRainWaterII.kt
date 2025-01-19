package dev.mslalith.graph.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import java.util.*
import kotlin.math.max

class TrappingRainWaterII : TestCaseProblem<Array<IntArray>, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = TrappingRainWaterII().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<Array<IntArray>, Int>> = arrayOf(
        TestCase(
            input = arrayOf(
                intArrayOf(1, 4, 3, 1, 3, 2),
                intArrayOf(3, 2, 1, 3, 2, 4),
                intArrayOf(2, 3, 3, 2, 3, 1)
            ),
            output = 4
        ),
        TestCase(
            input = arrayOf(
                intArrayOf(3, 3, 3, 3, 3),
                intArrayOf(3, 2, 2, 2, 3),
                intArrayOf(3, 2, 1, 2, 3),
                intArrayOf(3, 2, 2, 2, 3),
                intArrayOf(3, 3, 3, 3, 3)
            ),
            output = 10
        )
    )

    override fun solve(testCaseInput: Array<IntArray>): Int {
        return trapRainWater(testCaseInput)
    }

    private fun trapRainWater(heightMap: Array<IntArray>): Int {
        val m = heightMap.size
        val n = heightMap[0].size
        if (m < 3 || n < 3) return 0

        val visited = Array(m) { BooleanArray(n) }
        val pq = PriorityQueue<Cell> { a, b -> a.height - b.height }

        // mark first & last col as visited
        for (i in 0 until m) {
            visited[i][0] = true
            pq.add(Cell(heightMap[i][0], i, 0))

            visited[i][n - 1] = true
            pq.add(Cell(heightMap[i][n - 1], i, n - 1))
        }

        // mark first & last row as visited
        for (j in 0 until n) {
            visited[0][j] = true
            pq.add(Cell(heightMap[0][j], 0, j))

            visited[m - 1][j] = true
            pq.add(Cell(heightMap[m - 1][j], m - 1, j))
        }

        val directions = arrayOf(1 to 0, -1 to 0, 0 to 1, 0 to -1)
        var trappedWater = 0

        while (pq.isNotEmpty()) {
            val cell = pq.poll()

            for ((dx, dy) in directions) {
                val row = cell.row + dx
                val col = cell.col + dy
                if (row < 0 || row >= m || col < 0 || col >= n) continue
                if (visited[row][col]) continue

                visited[row][col] = true
                trappedWater += max(0, cell.height - heightMap[row][col])
                pq.add(Cell(max(cell.height, heightMap[row][col]), row, col))
            }
        }

        return trappedWater
    }

    private data class Cell(
        val height: Int,
        val row: Int,
        val col: Int
    )
}
