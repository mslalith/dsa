package dev.mslalith.trie.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.trie.TrieNode

class WordSearch : TestCaseProblem<Pair<Array<CharArray>, String>, Boolean>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = WordSearch().runForConsole()
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
            ) to "ABCD",
            output = false
        ),
        TestCase(
            input = arrayOf(
                charArrayOf('b', 'b', 'a', 'a', 'b', 'a'),
                charArrayOf('b', 'b', 'a', 'b', 'a', 'a'),
                charArrayOf('b', 'b', 'b', 'b', 'b', 'b'),
                charArrayOf('a', 'a', 'a', 'b', 'a', 'a'),
                charArrayOf('a', 'b', 'a', 'a', 'b', 'b')
            ) to "abbbababaa",
            output = true
        )
    )

    override fun solve(testCaseInput: Pair<Array<CharArray>, String>): Boolean {
        return existViaTrie(testCaseInput.first, testCaseInput.second)
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
                if (board[i][j] == word[0] && hasPath(i, j, 0)) return true
            }
        }

        return false
    }

    private fun existViaTrie(board: Array<CharArray>, word: String): Boolean {
        val m = board.size
        val n = board[0].size
        val directions = arrayOf(1 to 0, -1 to 0, 0 to 1, 0 to -1)

        fun hasPath(i: Int, j: Int, node: TrieNode<Char>?): Boolean {
            if (i < 0 || i >= m || j < 0 || j >= n) return false

            val char = board[i][j]
            if (char == '-') return false

            val nextNode = node?.get(char) ?: return false
            if (nextNode.isEnd) return true


            val originalValue = board[i][j]
            board[i][j] = '-'

            val found = directions.any { (dx, dy) -> hasPath(i + dx, j + dy, nextNode) }
            board[i][j] = originalValue

            return found
        }

        fun buildTrie(): TrieNode<Char> {
            val root = TrieNode<Char>()

            var curr = root
            for (ch in word) {
                if (ch !in curr) curr.add(ch)
                curr = curr.getValue(ch)
            }
            curr.markAsEnd()

            return root
        }

        val root = buildTrie()

        for (i in board.indices) {
            for (j in board[i].indices) {
                if (board[i][j] == word[0] && hasPath(i, j, root)) return true
            }
        }

        return false
    }
}
