package dev.mslalith.graph.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import java.util.*

class MinimumCostToMakeAtLeastOneValidPathInGrid : TestCaseProblem<Array<IntArray>, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = MinimumCostToMakeAtLeastOneValidPathInGrid().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<Array<IntArray>, Int>> = arrayOf(
        TestCase(
            input = arrayOf(
                intArrayOf(1, 1, 1, 1),
                intArrayOf(2, 2, 2, 2),
                intArrayOf(1, 1, 1, 1),
                intArrayOf(2, 2, 2, 2)
            ),
            output = 3
        ),
        TestCase(
            input = arrayOf(
                intArrayOf(1, 1, 3),
                intArrayOf(3, 2, 2),
                intArrayOf(1, 1, 4)
            ),
            output = 0
        ),
        TestCase(
            input = arrayOf(
                intArrayOf(1, 2),
                intArrayOf(4, 3)
            ),
            output = 1
        )
    )

    override fun solve(testCaseInput: Array<IntArray>): Int {
        return minCost(testCaseInput)
    }

    private fun minCost(grid: Array<IntArray>): Int {
        val m = grid.size
        val n = grid[0].size

        val directions = arrayOf(0 to 1, 0 to -1, 1 to 0, -1 to 0)
        val minCost = Array(m) { IntArray(n) { Int.MAX_VALUE } }
        minCost[0][0] = 0

        // (cost, index)
        val pq = PriorityQueue<Pair<Int, Int>> { a, b -> a.first - b.first }
        pq.add(0 to 0)

        while (pq.isNotEmpty()) {
            val (cost, index) = pq.poll()
            val row = index / n
            val col = index % n

            if (cost > minCost[row][col]) continue

            for (i in directions.indices) {
                val r = row + directions[i].first
                val c = col + directions[i].second
                if (r < 0 || r >= m || c < 0 || c >= n) continue
                val newCost = cost + if (grid[row][col] - 1 == i) 0 else 1
                if (newCost < minCost[r][c]) {
                    minCost[r][c] = newCost
                    pq.add(newCost to (r * n) + c)
                }
            }
        }

        return minCost[m - 1][n - 1]
    }
}
