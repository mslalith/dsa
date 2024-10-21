package dev.mslalith.array.problems

import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.core.TestCase

class NearestExitFromEntranceInMaze : TestCaseProblem<Pair<Array<CharArray>, IntArray>, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = NearestExitFromEntranceInMaze().runAll()
    }

    override fun getTestCases(): Array<TestCase<Pair<Array<CharArray>, IntArray>, Int>> = arrayOf(
        TestCase(
            input = arrayOf(
                charArrayOf('+', '+', '.', '+'),
                charArrayOf('.', '.', '.', '+'),
                charArrayOf('+', '+', '+', '.')
            ) to intArrayOf(1, 2),
            output = 1
        ),
        TestCase(
            input = arrayOf(
                charArrayOf('+', '+', '+'),
                charArrayOf('.', '.', '.'),
                charArrayOf('+', '+', '+')
            ) to intArrayOf(1, 0),
            output = 2
        ),
        TestCase(
            input = arrayOf(
                charArrayOf('.', '+')
            ) to intArrayOf(0, 0),
            output = -1
        ),
        TestCase(
            input = arrayOf(
                charArrayOf('+', '.', '+', '+', '+', '+', '+'),
                charArrayOf('+', '.', '+', '.', '.', '.', '+'),
                charArrayOf('+', '.', '+', '.', '+', '.', '+'),
                charArrayOf('+', '.', '.', '.', '.', '.', '+'),
                charArrayOf('+', '+', '+', '+', '.', '+', '.')
            ) to intArrayOf(0, 1),
            output = 7
        )
    )

    override fun solve(testCaseInput: Pair<Array<CharArray>, IntArray>): Int {
        return nearestExit(testCaseInput.first, testCaseInput.second)
    }

    private fun nearestExit(maze: Array<CharArray>, entrance: IntArray): Int {
        val m = maze.size
        val n = maze[0].size
        val directions = arrayOf(1 to 0, -1 to 0, 0 to 1, 0 to -1)

        var (x, y) = entrance
        val queue = ArrayDeque<Pair<Int, Int>>()
        queue.add(x to y)
        maze[x][y] = '+'

        var steps = 0

        while (queue.isNotEmpty()) {
            val size = queue.size
            steps++

            for (i in 0 until size) {
                val (r, c) = queue.removeFirst()

                for ((dx, dy) in directions) {
                    x = r + dx
                    y = c + dy

                    if (x < 0 || x >= m || y < 0 || y >= n) continue
                    if (maze[x][y] == '+') continue
                    if (x == 0 || x == m - 1 || y == 0 || y == n - 1) return steps

                    queue.add(x to y)
                    maze[x][y] = '+'
                }
            }
        }

        return -1
    }
}
