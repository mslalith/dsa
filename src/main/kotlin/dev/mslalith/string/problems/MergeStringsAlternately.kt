package dev.mslalith.string.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem

class MergeStringsAlternately : TestCaseProblem<Pair<String, String>, String>() {
    
    companion object {
        @JvmStatic
        fun main(args: Array<String>) = MergeStringsAlternately().runForConsole()
    }
    
    override fun getTestCases(): Array<TestCase<Pair<String, String>, String>> = arrayOf(
        TestCase(
            input = "abc" to "pqr",
            output = "apbqcr"
        ),
        TestCase(
            input = "ab" to "pqrs",
            output = "apbqrs"
        ),
        TestCase(
            input = "abcd" to "pq",
            output = "apbqcd"
        )
    )
    
    override fun solve(testCaseInput: Pair<String, String>): String {
        return mergeAlternately(testCaseInput.first, testCaseInput.second)
    }

    private fun mergeAlternately(word1: String, word2: String): String {
        return buildString {
            var i = 0
            var j = 0
            while (i < word1.length && j < word2.length) {
                append(word1[i++])
                append(word2[j++])
            }
            while (i < word1.length) {
                append(word1[i++])
            }
            while (j < word2.length) {
                append(word2[j++])
            }
        }
    }
}
