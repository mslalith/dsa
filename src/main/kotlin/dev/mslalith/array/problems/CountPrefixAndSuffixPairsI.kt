package dev.mslalith.array.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem

class CountPrefixAndSuffixPairsI : TestCaseProblem<Array<String>, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = CountPrefixAndSuffixPairsI().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<Array<String>, Int>> = arrayOf(
        TestCase(
            input = arrayOf("a", "aba", "ababa", "aa"),
            output = 4
        ),
        TestCase(
            input = arrayOf("pa", "papa", "ma", "mama"),
            output = 2
        ),
        TestCase(
            input = arrayOf("abab", "ab"),
            output = 0
        )
    )

    override fun solve(testCaseInput: Array<String>): Int {
        return countPrefixSuffixPairs(testCaseInput)
    }

    private fun countPrefixSuffixPairs(words: Array<String>): Int {
        val n = words.size
        if (n == 1) return 0

        var totalPairs = 0

        for (i in 0 until n) {
            val word1 = words[i]
            for (j in (i + 1) until n) {
                val len = word1.length
                val word2 = words[j]
                if (word2.length < len) continue
                if (word2.substring(0, len) == word1 && word2.substring(word2.length - len) == word1) totalPairs++
            }
        }

        return totalPairs
    }
}
