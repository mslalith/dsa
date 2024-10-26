package dev.mslalith.graph.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.utils.createClone

class SurroundedRegions : TestCaseProblem<Array<CharArray>, Array<CharArray>>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = SurroundedRegions().runAll()
    }

    override fun displayInput(input: Array<CharArray>): String = stringFromType(input, true)
    override fun displayOutput(output: Array<CharArray>): String = stringFromType(output, true)
    override fun displayExpected(expected: Array<CharArray>): String = stringFromType(expected, true)

    override fun getTestCases(): Array<TestCase<Array<CharArray>, Array<CharArray>>> = arrayOf(
        TestCase(
            input = arrayOf(
                charArrayOf('X', 'X', 'X', 'X'),
                charArrayOf('X', 'O', 'O', 'X'),
                charArrayOf('X', 'X', 'O', 'X'),
                charArrayOf('X', 'O', 'X', 'X')
            ),
            output = arrayOf(
                charArrayOf('X', 'X', 'X', 'X'),
                charArrayOf('X', 'X', 'X', 'X'),
                charArrayOf('X', 'X', 'X', 'X'),
                charArrayOf('X', 'O', 'X', 'X')
            )
        ),
        TestCase(
            input = arrayOf(
                charArrayOf('X')
            ),
            output = arrayOf(
                charArrayOf('X')
            )
        ),
        TestCase(
            input = arrayOf(
                charArrayOf('X', 'X', 'X', 'X'),
                charArrayOf('X', 'O', 'O', 'X'),
                charArrayOf('X', 'X', 'O', 'X'),
                charArrayOf('X', 'O', 'O', 'X')
            ),
            output = arrayOf(
                charArrayOf('X', 'X', 'X', 'X'),
                charArrayOf('X', 'O', 'O', 'X'),
                charArrayOf('X', 'X', 'O', 'X'),
                charArrayOf('X', 'O', 'O', 'X')
            )
        ),
        TestCase(
            input = arrayOf(
                charArrayOf('O', 'O', 'O', 'O', 'X', 'X'),
                charArrayOf('O', 'O', 'O', 'O', 'O', 'O'),
                charArrayOf('O', 'X', 'O', 'X', 'O', 'O'),
                charArrayOf('O', 'X', 'O', 'O', 'X', 'O'),
                charArrayOf('O', 'X', 'O', 'X', 'O', 'O'),
                charArrayOf('O', 'X', 'O', 'O', 'O', 'O')
            ),
            output = arrayOf(
                charArrayOf('O', 'O', 'O', 'O', 'X', 'X'),
                charArrayOf('O', 'O', 'O', 'O', 'O', 'O'),
                charArrayOf('O', 'X', 'O', 'X', 'O', 'O'),
                charArrayOf('O', 'X', 'O', 'O', 'X', 'O'),
                charArrayOf('O', 'X', 'O', 'X', 'O', 'O'),
                charArrayOf('O', 'X', 'O', 'O', 'O', 'O')
            )
        ),
        TestCase(
            input = arrayOf(
                charArrayOf('O', 'O', 'X', 'X', 'X', 'O', 'X', 'X', 'O'),
                charArrayOf('O', 'X', 'O', 'O', 'X', 'X', 'O', 'X', 'O'),
                charArrayOf('X', 'O', 'X', 'X', 'O', 'X', 'O', 'O', 'X'),
                charArrayOf('O', 'O', 'O', 'X', 'O', 'X', 'O', 'X', 'O'),
                charArrayOf('X', 'O', 'X', 'X', 'X', 'X', 'O', 'O', 'O')
            ),
            output = arrayOf(
                charArrayOf('O', 'O', 'X', 'X', 'X', 'O', 'X', 'X', 'O'),
                charArrayOf('O', 'X', 'X', 'X', 'X', 'X', 'O', 'X', 'O'),
                charArrayOf('X', 'O', 'X', 'X', 'X', 'X', 'O', 'O', 'X'),
                charArrayOf('O', 'O', 'O', 'X', 'X', 'X', 'O', 'X', 'O'),
                charArrayOf('X', 'O', 'X', 'X', 'X', 'X', 'O', 'O', 'O')
            )
        )
    )

    override fun solve(testCaseInput: Array<CharArray>): Array<CharArray> {
        val clone = testCaseInput.createClone()
        solveProblem(clone)
        return clone
    }

    private fun solveProblem(board: Array<CharArray>) {
        val m = board.size
        val n = board[0].size
        val directions = arrayOf(1 to 0, -1 to 0, 0 to 1, 0 to -1)

        fun markBoundaries(i: Int, j: Int) {
            if (i < 0 || i >= m || j < 0 || j >= n) return
            if (board[i][j] == 'X') return
            if (board[i][j] == 'W') return

            board[i][j] = 'W'

            directions.forEach { markBoundaries(i + it.first, j + it.second) }
        }

        for (i in listOf(0, m - 1)) {
            for (j in 0 until n) {
                markBoundaries(i, j)
            }
        }

        for (i in 0 until m) {
            for (j in listOf(0, n - 1)) {
                markBoundaries(i, j)
            }
        }

        for (i in 0 until m) {
            for (j in 0 until n) {
                board[i][j] = when (val ch = board[i][j]) {
                    'W' -> 'O'
                    'O' -> 'X'
                    else -> ch
                }
            }
        }
    }
}
