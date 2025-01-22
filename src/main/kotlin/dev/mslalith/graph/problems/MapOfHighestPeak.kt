package dev.mslalith.graph.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import java.util.LinkedList
import java.util.Queue

class MapOfHighestPeak : TestCaseProblem<Array<IntArray>, Array<IntArray>>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = MapOfHighestPeak().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<Array<IntArray>, Array<IntArray>>> = arrayOf(
        TestCase(
            input = arrayOf(
                intArrayOf(0, 1),
                intArrayOf(0, 0)
            ),
            output = arrayOf(
                intArrayOf(1, 0),
                intArrayOf(2, 1)
            )
        ),
        TestCase(
            input = arrayOf(
                intArrayOf(0, 0, 1),
                intArrayOf(1, 0, 0),
                intArrayOf(0, 0, 0)
            ),
            output = arrayOf(
                intArrayOf(1, 1, 0),
                intArrayOf(0, 1, 1),
                intArrayOf(1, 2, 2)
            )
        )
    )

    override fun solve(testCaseInput: Array<IntArray>): Array<IntArray> {
        return highestPeak(testCaseInput)
    }
    
    private fun highestPeak(isWater: Array<IntArray>): Array<IntArray> {
        val m = isWater.size
        val n = isWater[0].size

        val directions = arrayOf(1 to 0, -1 to 0, 0 to 1, 0 to -1)
        val result = Array(m) { IntArray(n) }
        val visited = Array(m) { BooleanArray(n) }

        // (count, row, col)
        val queue: Queue<Triple<Int, Int, Int>> = LinkedList()
        for (i in 0 until m) {
            for (j in 0 until n) {
                if (isWater[i][j] == 1) {
                    queue.add(Triple(0, i, j))
                    visited[i][j] = true
                }
            }
        }

        while (queue.isNotEmpty()) {
            val (count, row, col) = queue.poll()

            for ((dx, dy) in directions) {
                val r = row + dx
                val c = col + dy
                if (r < 0 || r >= m || c < 0 || c >= n) continue
                if (visited[r][c]) continue

                visited[r][c] = true
                result[r][c] = count + 1
                queue.add(Triple(count + 1, r, c))
            }
        }
        
        return result
    }
}
