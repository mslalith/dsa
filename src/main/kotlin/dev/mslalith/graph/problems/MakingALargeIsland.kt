package dev.mslalith.graph.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.graph.impl.disjointset.DisjointSetBySize
import kotlin.math.max

class MakingALargeIsland : TestCaseProblem<Array<IntArray>, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = MakingALargeIsland().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<Array<IntArray>, Int>> = arrayOf(
        TestCase(
            input = arrayOf(
                intArrayOf(1, 0),
                intArrayOf(0, 1)
            ),
            output = 3
        ),
        TestCase(
            input = arrayOf(
                intArrayOf(1, 1),
                intArrayOf(1, 0)
            ),
            output = 4
        ),
        TestCase(
            input = arrayOf(
                intArrayOf(1, 1),
                intArrayOf(1, 1)
            ),
            output = 4
        ),
        TestCase(
            input = arrayOf(
                intArrayOf(0, 0),
                intArrayOf(0, 0)
            ),
            output = 1
        ),
        TestCase(
            input = arrayOf(
                intArrayOf(0, 0),
                intArrayOf(0, 1)
            ),
            output = 2
        )
    )

    override fun solve(testCaseInput: Array<IntArray>): Int {
        return largestIsland(testCaseInput)
    }

    private fun largestIsland(grid: Array<IntArray>): Int {
        val n = grid.size
        val ds = DisjointSetBySize(n * n)
        val directions = arrayOf(0 to 1, 1 to 0, 0 to -1, -1 to 0)

        var largeIsland = 0

        for (i in 0 until n) {
            for (j in 0 until n) {
                if (grid[i][j] != 1) continue

                for ((dx, dy) in directions.take(2)) {
                    val r = i + dx
                    val c = j + dy
                    if (r < 0 || r >= n || c < 0 || c >= n) continue
                    if (grid[r][c] != 1) continue

                    val idx = (i * n) + j
                    if (ds.union(idx, (r * n) + c)) {
                        // updates largest connected island
                        largeIsland = max(largeIsland, ds.nodesCountFor(idx))
                    }
                }

                // as this is an island and no connected islands
                // set 1 as largest
                largeIsland = max(largeIsland, 1)
            }
        }

        // there are no islands
        if (largeIsland == 0) return 1

        val individualComponentIndexes = hashSetOf<Int>()

        for (i in 0 until n) {
            for (j in 0 until n) {
                if (grid[i][j] != 0) continue

                var adjIslandCount = 0

                for ((dx, dy) in directions) {
                    val r = i + dx
                    val c = j + dy
                    if (r < 0 || r >= n || c < 0 || c >= n) continue

                    val parent = ds.findParent((r * n) + c)
                    if (grid[r][c] == 1 && individualComponentIndexes.add(parent)) {
                        adjIslandCount += ds.nodesCountFor(parent)
                    }
                }

                largeIsland = max(largeIsland, adjIslandCount + 1)
                individualComponentIndexes.clear()
            }
        }

        return largeIsland
    }
}
