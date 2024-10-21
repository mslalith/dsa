package dev.mslalith.array.problems

import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.core.TestCase

class ValidSudoku : TestCaseProblem<Array<CharArray>, Boolean>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = ValidSudoku().runAll()
    }

    override fun getTestCases(): Array<TestCase<Array<CharArray>, Boolean>> = arrayOf(
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
            output = true
        ),
        TestCase(
            input = arrayOf(
                charArrayOf('8', '3', '.', '.', '7', '.', '.', '.', '.'),
                charArrayOf('6', '.', '.', '1', '9', '5', '.', '.', '.'),
                charArrayOf('.', '9', '8', '.', '.', '.', '.', '6', '.'),
                charArrayOf('8', '.', '.', '.', '6', '.', '.', '.', '3'),
                charArrayOf('4', '.', '.', '8', '.', '3', '.', '.', '1'),
                charArrayOf('7', '.', '.', '.', '2', '.', '.', '.', '6'),
                charArrayOf('.', '6', '.', '.', '.', '.', '2', '8', '.'),
                charArrayOf('.', '.', '.', '4', '1', '9', '.', '.', '5'),
                charArrayOf('.', '.', '.', '.', '8', '.', '.', '7', '9')
            ),
            output = false
        )
    )

    override fun solve(testCaseInput: Array<CharArray>): Boolean {
        return isValidSudoku(testCaseInput)
    }

    private fun isValidSudoku(board: Array<CharArray>): Boolean {
        val rowMap = Array(9) { hashSetOf<Char>() }
        val columnMap = Array(9) { hashSetOf<Char>() }
        val boxMap = Array(3) { Array(3) { hashSetOf<Char>() } }

        for (i in board.indices) {
            for (j in board[i].indices) {
                val char = board[i][j]
                if (char == '.') continue

                if (rowMap[i].contains(char)) return false
                rowMap[i].add(char)

                if (columnMap[j].contains(char)) return false
                columnMap[j].add(char)

                val boxX = i / 3
                val boxY = j / 3
                if (boxMap[boxX][boxY].contains(char)) return false
                boxMap[boxX][boxY].add(char)
            }
        }

        return true
    }

    private fun isValidSudokuNaive(board: Array<CharArray>): Boolean {
        fun checkCharArray(charArray: CharArray): Boolean = charArray.filterNot { it == '.' }.let { it.size == it.toHashSet().size }
        fun checkRow(board: Array<CharArray>, index: Int): Boolean = checkCharArray(board[index])
        fun checkColumn(board: Array<CharArray>, index: Int): Boolean = checkCharArray(board.map { it[index] }.toCharArray())

        fun checkBox(board: Array<CharArray>, index: Int): Boolean {
            val row = (index / 3) * 3
            val col = (index % 3) * 3
            val hashSet = hashSetOf<Char>()
            for (i in row until row + 3) {
                for (j in col until col + 3) {
                    if (board[i][j] != '.' && !hashSet.add(board[i][j])) return false
                }
            }
            return true
        }

        for (index in board.indices) {
            if (!checkRow(board, index)) return false
            if (!checkColumn(board, index)) return false
            if (!checkBox(board, index)) return false
        }

        return true
    }
}
