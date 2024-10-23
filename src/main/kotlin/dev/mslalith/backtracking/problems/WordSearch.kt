package dev.mslalith.backtracking.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem

class WordSearch : TestCaseProblem<Pair<Array<CharArray>, String>, Boolean>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = WordSearch().runAll()
    }

    override fun getTestCases(): Array<TestCase<Pair<Array<CharArray>, String>, Boolean>> = arrayOf(
        TestCase(
            input = arrayOf(
                charArrayOf('A', 'B', 'C', 'E'),
                charArrayOf('S', 'F', 'C', 'S'),
                charArrayOf('A', 'D', 'E', 'E')
            ) to "ABCCED",
            output = true
        ),
        TestCase(
            input = arrayOf(
                charArrayOf('A', 'B', 'C', 'E'),
                charArrayOf('S', 'F', 'C', 'S'),
                charArrayOf('A', 'D', 'E', 'E')
            ) to "SEE",
            output = true
        ),
        TestCase(
            input = arrayOf(
                charArrayOf('A', 'B', 'C', 'E'),
                charArrayOf('S', 'F', 'C', 'S'),
                charArrayOf('A', 'D', 'E', 'E')
            ) to "ABCB",
            output = false
        ),
        TestCase(
            input = arrayOf(
                charArrayOf('a', 'a')
            ) to "aaa",
            output = false
        )
    )
//        .let { arrayOf(it.last()) }

    override fun solve(testCaseInput: Pair<Array<CharArray>, String>): Boolean {
        return exist(testCaseInput.first, testCaseInput.second)
    }

    private fun exist(board: Array<CharArray>, word: String): Boolean {
        val m = board.size
        val n = board[0].size
        val directions = arrayOf(1 to 0, -1 to 0, 0 to 1, 0 to -1)

        fun hasPath(i: Int, j: Int, wordIndex: Int): Boolean {
            if (wordIndex == word.length) return true

            if (i < 0 || i >= m || j < 0 || j >= n) return false
            if (board[i][j] != word[wordIndex]) return false

            val hold = board[i][j]
            board[i][j] = '-'

            val found = directions.any { (dx, dy) -> hasPath(i + dx, j + dy, wordIndex + 1) }
            board[i][j] = hold

            return found
        }

        for (i in board.indices) {
            for (j in board[i].indices) {
                if (hasPath(i, j, 0)) return true
            }
        }

        return false
    }
}
