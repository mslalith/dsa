package dev.mslalith.trie.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.utils.unOrderEquals

class WordSearchII : TestCaseProblem<Pair<Array<CharArray>, Array<String>>, List<String>>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = WordSearchII().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<Pair<Array<CharArray>, Array<String>>, List<String>>> = arrayOf(
        TestCase(
            input = arrayOf(
                charArrayOf('o', 'a', 'a', 'n'),
                charArrayOf('e', 't', 'a', 'e'),
                charArrayOf('i', 'h', 'k', 'r'),
                charArrayOf('i', 'f', 'l', 'v')
            ) to arrayOf("oath", "pea", "eat", "rain"),
            output = listOf("eat", "oath")
        ),
        TestCase(
            input = arrayOf(
                charArrayOf('a', 'b'),
                charArrayOf('c', 'd')
            ) to arrayOf("abcd"),
            output = emptyList()
        ),
        TestCase(
            input = arrayOf(
                charArrayOf('o', 'a', 'b', 'n'),
                charArrayOf('o', 't', 'a', 'e'),
                charArrayOf('a', 'h', 'k', 'r'),
                charArrayOf('a', 'f', 'l', 'v')
            ) to arrayOf("oa", "oaa"),
            output = listOf("oa", "oaa")
        ),
        TestCase(
            input = arrayOf(
                charArrayOf('o', 'a', 'a', 'n'),
                charArrayOf('e', 't', 'a', 'e'),
                charArrayOf('i', 'h', 'k', 'r'),
                charArrayOf('i', 'f', 'l', 'v')
            ) to arrayOf("oath", "pea", "eat", "rain", "oathi", "oathk", "oathf", "oate", "oathii", "oathfi", "oathfii"),
            output = listOf("oath", "oathk", "oathf", "oathfi", "oathfii", "oathi", "oathii", "oate", "eat")
        )
    )

    override fun isTestPassed(actual: List<String>, expected: List<String>): Boolean {
        return actual.unOrderEquals(expected)
    }

    override fun solve(testCaseInput: Pair<Array<CharArray>, Array<String>>): List<String> {
        return findWords(testCaseInput.first, testCaseInput.second)
    }

    private fun findWords(board: Array<CharArray>, words: Array<String>): List<String> {
        val m = board.size
        val n = board[0].size
        val directions = arrayOf(0 to 1, 0 to -1, 1 to 0, -1 to 0)

        fun dfs(i: Int, j: Int, node: TrieNode?, result: MutableList<String>) {
            if (i < 0 || i >= m || j < 0 || j >= n) return

            val char = board[i][j]
            if (char == '-') return
            if (node == null) return
            if (char !in node) return

            val nextNode = node.get(char)
            val word = nextNode?.word

            if (word != null) {
                result.add(word)
                nextNode.word = null
            }

            board[i][j] = '-'
            for ((dx, dy) in directions) dfs(i + dx, j + dy, nextNode, result)

            board[i][j] = char
        }

        fun buildTrie(): TrieNode {
            val root = TrieNode()

            for (word in words) {
                var curr = root
                for (ch in word) {
                    if (ch !in curr) curr.add(ch)
                    curr = curr.get(ch)!!
                }
                curr.word = word
            }

            return root
        }

        val root = buildTrie()
        val result = mutableListOf<String>()

        for (i in board.indices) {
            for (j in board[i].indices) {
                if (board[i][j] in root) dfs(i, j, root, result)
            }
        }

        return result
    }
}

private class TrieNode {
    private val children = Array<TrieNode?>(26) { null }
    var word: String? = null

    operator fun contains(char: Char): Boolean = get(char) != null
    fun get(char: Char): TrieNode? = children[char - 'a']
    fun add(char: Char) {
        children[char - 'a'] = TrieNode()
    }
}
