package dev.mslalith.backtracking.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.utils.unOrderEquals

class NQueens : TestCaseProblem<Int, List<List<String>>>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = NQueens().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<Int, List<List<String>>>> = arrayOf(
        TestCase(
            input = 4,
            output = listOf(
                listOf(".Q..", "...Q", "Q...", "..Q."),
                listOf("..Q.", "Q...", "...Q", ".Q..")
            )
        ),
        TestCase(
            input = 1,
            output = listOf(
                listOf("Q")
            )
        )
    )

    override fun isTestPassed(actual: List<List<String>>, expected: List<List<String>>): Boolean {
        return actual.unOrderEquals(expected)
    }

    override fun solve(testCaseInput: Int): List<List<String>> {
        return solveNQueens(testCaseInput)
    }

    private fun solveNQueens(n: Int): List<List<String>> {
        val result = mutableListOf<List<String>>()
        val board = Array(n) { CharArray(n) { '.' } }

        fun isValid(row: Int, col: Int): Boolean {
            var r = row
            var c = col

            while (c >= 0) {
                if (board[r][c] == 'Q') return false
                c--
            }

            r = row
            c = col
            while (r >= 0 && c >= 0) {
                if (board[r][c] == 'Q') return false
                r--
                c--
            }

            r = row
            c = col
            while (c >= 0 && r < n) {
                if (board[r][c] == 'Q') return false
                r++
                c--
            }

            return true
        }

        fun findNQueens(col: Int) {
            if (col == n) {
                val clone = board.map { String(it) }
                result.add(clone)
                return
            }

            for (row in 0 until n) {
                if (isValid(row, col)) {
                    board[row][col] = 'Q'
                    findNQueens(col + 1)
                    board[row][col] = '.'
                }
            }
        }

        findNQueens(0)

        return result
    }
}
