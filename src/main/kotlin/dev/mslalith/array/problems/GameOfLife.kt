package dev.mslalith.array.problems

import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.core.TestCase
import dev.mslalith.utils.createClone

class GameOfLife : TestCaseProblem<Array<IntArray>, Array<IntArray>>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = GameOfLife().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<Array<IntArray>, Array<IntArray>>> = arrayOf(
        TestCase(
            input = arrayOf(
                intArrayOf(0, 1, 0),
                intArrayOf(0, 0, 1),
                intArrayOf(1, 1, 1),
                intArrayOf(0, 0, 0)
            ),
            output = arrayOf(
                intArrayOf(0, 0, 0),
                intArrayOf(1, 0, 1),
                intArrayOf(0, 1, 1),
                intArrayOf(0, 1, 0)
            )
        ),
        TestCase(
            input = arrayOf(
                intArrayOf(1, 1),
                intArrayOf(1, 0)
            ),
            output = arrayOf(
                intArrayOf(1, 1),
                intArrayOf(1, 1)
            )
        )
    )

    override fun solve(testCaseInput: Array<IntArray>): Array<IntArray> {
        val clone = testCaseInput.createClone()
        gameOfLife(clone)
        return clone
    }

    private fun gameOfLife(board: Array<IntArray>) {
        val m = board.size
        val n = board[0].size

        val result = Array(m) { IntArray(n) }
        val directions = arrayOf(
            intArrayOf(0, -1),
            intArrayOf(0, 1),
            intArrayOf(-1, 0),
            intArrayOf(1, 0),
            intArrayOf(-1, -1),
            intArrayOf(1, -1),
            intArrayOf(-1, 1),
            intArrayOf(1, 1)
        )

        fun neighboursAliveCount(i: Int, j: Int): Int {
            var count = 0
            for ((dx, dy) in directions) {
                val x = i + dx
                val y = j + dy
                if (x < 0 || x >= m || y < 0 || y >= n) continue

                if (board[x][y] == 1) count++
            }
            return count
        }

        for (i in 0 until m) {
            for (j in 0 until n) {
                val count = neighboursAliveCount(i, j)
                result[i][j] = when (val a = board[i][j]) {
                    0 -> if (count == 3) 1 else 0
                    1 -> if (count < 2 || count > 3) 0 else a
                    else -> a
                }
            }
        }

        for (i in 0 until m) {
            for (j in 0 until n) {
                board[i][j] = result[i][j]
            }
        }
    }
}
