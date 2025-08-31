package dev.mslalith.backtracking.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.utils.createClone

class SudokuSolver : TestCaseProblem<Array<CharArray>, Array<CharArray>>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = SudokuSolver().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<Array<CharArray>, Array<CharArray>>> = arrayOf(
        TestCase(
            input = arrayOf(
                charArrayOf('5', '3', '.', '.', '7', '.', '.', '.', '.'),
                charArrayOf('6', '.', '.', '1', '9', '5', '.', '.', '.'),
                charArrayOf('.', '9', '8', '.', '.', '.', '.', '6', '.'),
                charArrayOf('8', '.', '.', '.', '6', '.', '.', '.', '3'),
                charArrayOf('4', '.', '.', '8', '.', '3', '.', '.', '1'),
                charArrayOf('7', '.', '.', '.', '2', '.', '.', '.', '6'),
                charArrayOf('.', '6', '.', '.', '.', '.', '2', '8', '.'),
                charArrayOf('.', '.', '.', '4', '1', '9', '.', '.', '5'),
                charArrayOf('.', '.', '.', '.', '8', '.', '.', '7', '9')
            ),
            output = arrayOf(
                charArrayOf('5', '3', '4', '6', '7', '8', '9', '1', '2'),
                charArrayOf('6', '7', '2', '1', '9', '5', '3', '4', '8'),
                charArrayOf('1', '9', '8', '3', '4', '2', '5', '6', '7'),
                charArrayOf('8', '5', '9', '7', '6', '1', '4', '2', '3'),
                charArrayOf('4', '2', '6', '8', '5', '3', '7', '9', '1'),
                charArrayOf('7', '1', '3', '9', '2', '4', '8', '5', '6'),
                charArrayOf('9', '6', '1', '5', '3', '7', '2', '8', '4'),
                charArrayOf('2', '8', '7', '4', '1', '9', '6', '3', '5'),
                charArrayOf('3', '4', '5', '2', '8', '6', '1', '7', '9')
            )
        )
    )

    override fun solve(testCaseInput: Array<CharArray>): Array<CharArray> {
        val solved = testCaseInput.createClone()
        solveSudoku(solved)
        return solved
    }

    private fun solveSudoku(board: Array<CharArray>) {

        fun isValid(row: Int, col: Int, num: Char): Boolean {
            for (i in 0..8) {
                if (board[row][i] == num || board[i][col] == num) return false
            }

            val startRow = (row / 3) * 3
            val startCol = (col / 3) * 3

            for (i in startRow..<startRow + 3) {
                for (j in startCol..<startCol + 3) {
                    if (board[i][j] == num) return false
                }
            }

            return true
        }

        fun solve(): Boolean {
            for (row in 0..8) {
                for (col in 0..8) {
                    if (board[row][col] == '.') {
                        for (num in '1'..'9') {
                            if (isValid(row, col, num)) {
                                board[row][col] = num
                                if (solve()) return true
                                board[row][col] = '.'
                            }
                        }

                        return false
                    }
                }
            }

            return true
        }

        solve()
    }
}
