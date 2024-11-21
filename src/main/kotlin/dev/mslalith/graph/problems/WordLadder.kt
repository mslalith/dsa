package dev.mslalith.graph.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import java.util.*

class WordLadder : TestCaseProblem<WordLadderParams, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = WordLadder().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<WordLadderParams, Int>> = arrayOf(
        TestCase(
            input = WordLadderParams(
                beginWord = "hit",
                endWord = "cog",
                wordList = listOf("hot", "dot", "dog", "lot", "log", "cog")
            ),
            output = 5
        ),
        TestCase(
            input = WordLadderParams(
                beginWord = "hit",
                endWord = "cog",
                wordList = listOf("hot", "dot", "dog", "lot", "log")
            ),
            output = 0
        )
    )

    override fun solve(testCaseInput: WordLadderParams): Int {
        return ladderLength(testCaseInput.beginWord, testCaseInput.endWord, testCaseInput.wordList)
    }

    private fun ladderLength(beginWord: String, endWord: String, wordList: List<String>): Int {
        val n = beginWord.length
        val wordSet = wordList.toHashSet()

        if (endWord !in wordSet) return 0

        val queue: Queue<String> = LinkedList()
        queue.add(beginWord)

        var steps = 0

        while (queue.isNotEmpty()) {
            val size = queue.size

            for (x in 0 until size) {
                val word = queue.poll()
                if (word == endWord) return steps + 1

                val chars = word.toCharArray()
                for (i in 0 until n) {
                    val originalChar = chars[i]
                    for (ch in 'a'..'z') {
                        chars[i] = ch
                        val newWord = String(chars)
                        if (newWord in wordSet) {
                            queue.add(newWord)
                            wordSet.remove(newWord)
                        }
                    }
                    chars[i] = originalChar
                }
            }

            steps++
        }

        return 0
    }
}

data class WordLadderParams(
    val beginWord: String,
    val endWord: String,
    val wordList: List<String>
) {
    override fun toString(): String {
        return """
            
            beginWord: $beginWord
            endWord: $endWord
            wordList: $wordList
        """.trimIndent()
    }
}
