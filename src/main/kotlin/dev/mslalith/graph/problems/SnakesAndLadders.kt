package dev.mslalith.graph.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import java.util.*
import kotlin.math.absoluteValue


class SnakesAndLadders : TestCaseProblem<Array<IntArray>, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = SnakesAndLadders().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<Array<IntArray>, Int>> = arrayOf(
        TestCase(
            input = arrayOf(
                intArrayOf(-1, -1, -1, -1, -1, -1),
                intArrayOf(-1, -1, -1, -1, -1, -1),
                intArrayOf(-1, -1, -1, -1, -1, -1),
                intArrayOf(-1, 35, -1, -1, 13, -1),
                intArrayOf(-1, -1, -1, -1, -1, -1),
                intArrayOf(-1, 15, -1, -1, -1, -1)
            ),
            output = 4
        ),
        TestCase(
            input = arrayOf(
                intArrayOf(-1, -1),
                intArrayOf(-1, 3)
            ),
            output = 1
        ),
        TestCase(
            input = arrayOf(
                intArrayOf(-1, 7, -1),
                intArrayOf(-1, 6, 9),
                intArrayOf(-1, -1, 2)
            ),
            output = 1
        ),
        TestCase(
            input = arrayOf(
                intArrayOf(-1, -1, -1, -1),
                intArrayOf(-1, -1, -1, -1),
                intArrayOf(-1, 9, 14, -1),
                intArrayOf(-1, 6, -1, -1),
            ),
            output = 2
        ),
        TestCase(
            input = arrayOf(
                intArrayOf(1, 1, -1),
                intArrayOf(1, 1, 1),
                intArrayOf(-1, 1, 1)
            ),
            output = -1
        ),
        TestCase(
            input = arrayOf(
                intArrayOf(-1, -1, -1, 135, -1, -1, -1, -1, -1, 185, -1, -1, -1, -1, 105, -1),
                intArrayOf(-1, -1, 92, -1, -1, -1, -1, -1, -1, 201, -1, 118, -1, -1, 183, -1),
                intArrayOf(-1, -1, -1, -1, -1, -1, -1, -1, -1, 179, -1, -1, -1, -1, -1, -1),
                intArrayOf(-1, 248, -1, -1, -1, -1, -1, -1, -1, 119, -1, -1, -1, -1, -1, 192),
                intArrayOf(-1, -1, 104, -1, -1, -1, -1, -1, -1, -1, 165, -1, -1, 206, 104, -1),
                intArrayOf(145, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 229, -1),
                intArrayOf(-1, -1, 75, 140, -1, -1, -1, -1, -1, -1, -1, -1, 43, -1, 34, -1),
                intArrayOf(-1, -1, -1, -1, -1, -1, 169, -1, -1, -1, -1, -1, -1, 188, -1, -1),
                intArrayOf(-1, -1, -1, -1, -1, -1, 92, -1, 171, -1, -1, -1, -1, -1, -1, 66),
                intArrayOf(-1, -1, -1, 126, -1, -1, 68, -1, -1, -1, -1, -1, -1, -1, -1, -1),
                intArrayOf(-1, 109, -1, 86, 28, 228, -1, -1, 144, -1, -1, -1, -1, -1, -1, -1),
                intArrayOf(-1, -1, -1, -1, 59, -1, -1, -1, -1, -1, 51, -1, -1, -1, 62, -1),
                intArrayOf(-1, 71, -1, -1, -1, 63, -1, -1, -1, -1, -1, -1, 212, -1, -1, -1),
                intArrayOf(-1, -1, -1, -1, 174, -1, 59, -1, -1, -1, -1, -1, -1, 133, -1, -1),
                intArrayOf(-1, -1, 62, -1, 5, -1, 16, -1, -1, -1, -1, -1, -1, -1, -1, -1),
                intArrayOf(-1, -1, -1, 59, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1)
            ),
            output = 10
        )
    )

    override fun solve(testCaseInput: Array<IntArray>): Int {
        return snakesAndLadders(testCaseInput)
    }

    private fun snakesAndLadders(board: Array<IntArray>): Int {
        val n = board.size
        val destination = n * n

        val queue: Queue<Int> = LinkedList()
        queue.add(1)

        val visited = hashSetOf<Int>()
        visited += 1

        fun numToRowCol(num: Int): Pair<Int, Int> {
            val row = n - (((num - 1) / n) + 1)
            val start = if (row % 2 == n % 2) {
                destination - (row * n)
            } else {
                destination - (((row + 1) * n) - 1)
            }
            val col = (start - num).absoluteValue
            return row to col
        }

        var steps = 0

        while (queue.isNotEmpty()) {
            val size = queue.size

            for (x in 0 until size) {
                val currNode = queue.poll()
                if (currNode == destination) return steps

                for (offset in 1..6) {
                    val next = currNode + offset
                    if (next > destination) break

                    if (next in visited) continue
                    visited += next

                    val (row, col) = numToRowCol(next)
                    val nextNode = if (board[row][col] != -1) board[row][col] else next
                    queue.add(nextNode)
                }
            }

            steps++
        }

        return -1
    }
}
