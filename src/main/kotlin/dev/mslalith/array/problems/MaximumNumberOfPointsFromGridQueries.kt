package dev.mslalith.array.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import java.util.PriorityQueue

class MaximumNumberOfPointsFromGridQueries : TestCaseProblem<Pair<Array<IntArray>, IntArray>, IntArray>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = MaximumNumberOfPointsFromGridQueries().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<Pair<Array<IntArray>, IntArray>, IntArray>> = arrayOf(
        TestCase(
            input = arrayOf(
                intArrayOf(1, 2, 3),
                intArrayOf(2, 5, 7),
                intArrayOf(3, 5, 1)
            ) to intArrayOf(5, 6, 2),
            output = intArrayOf(5, 8, 1)
        ),
        TestCase(
            input = arrayOf(
                intArrayOf(5, 2, 1),
                intArrayOf(1, 1, 2)
            ) to intArrayOf(3),
            output = intArrayOf(0)
        )
    )

    override fun solve(testCaseInput: Pair<Array<IntArray>, IntArray>): IntArray {
        return maxPointsBfs(testCaseInput.first, testCaseInput.second)
    }

    private fun maxPointsBfs(grid: Array<IntArray>, queries: IntArray): IntArray {
        val m = grid.size
        val n = grid[0].size
        val directions = arrayOf(1 to 0, -1 to 0, 0 to 1, 0 to -1)

        val sortedQueries = queries
            .mapIndexed { index, query -> index to query }
            .sortedBy { it.second }

        // query to (row, col)
        val pq = PriorityQueue(compareBy<Pair<Int, Pair<Int, Int>>> { it.first })
        val visited = Array(m) { BooleanArray(n) }

        pq.add(grid[0][0] to (0 to 0))
        visited[0][0] = true

        val result = IntArray(queries.size)
        var points = 0

        for ((index, query) in sortedQueries) {
            while (pq.isNotEmpty() && pq.peek().first < query) {
                val (_, pair) = pq.poll()
                val (row, col) = pair

                points++

                for ((dx, dy) in directions) {
                    val r = row + dx
                    val c = col + dy
                    if (r in 0 until m && c in 0 until n && !visited[r][c]) {
                        visited[r][c] = true
                        pq.add(grid[r][c] to (r to c))
                    }
                }
            }

            result[index] = points
        }

        return result
    }

    private fun maxPointsDfs(grid: Array<IntArray>, queries: IntArray): IntArray {
        val m = grid.size
        val n = grid[0].size
        val directions = arrayOf(1 to 0, -1 to 0, 0 to 1, 0 to -1)
        val visited = hashSetOf<String>()

        fun dfs(query: Int, row: Int, col: Int): Int {
            if (row < 0 || row >= m) return 0
            if (col < 0 || col >= n) return 0
            if (grid[row][col] >= query) return 0

            val key = "$row:$col"
            if (key in visited) return 0
            visited += key

            var count = 1

            for ((dx, dy) in directions) {
                val r = row + dx
                val c = col + dy
                count += dfs(query, r, c)
            }

            return count
        }

        return IntArray(queries.size) {
            visited.clear()
            dfs(queries[it], 0, 0)
        }
    }
}
