package dev.mslalith.dp.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem


class WordBreak : TestCaseProblem<Pair<String, List<String>>, Boolean>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = WordBreak().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<Pair<String, List<String>>, Boolean>> = arrayOf(
        TestCase(
            input = "leetcode" to listOf("leet", "code"),
            output = true
        ),
        TestCase(
            input = "applepenapple" to listOf("apple", "pen"),
            output = true
        ),
        TestCase(
            input = "catsandog" to listOf("cats", "dog", "sand", "and", "cat"),
            output = false
        ),
        TestCase(
            input = "cars" to listOf("car", "ca", "rs"),
            output = true
        ),
        TestCase(
            input = "a" to listOf("a"),
            output = true
        ),
        TestCase(
            input = "a" to listOf("aa"),
            output = false
        ),
        TestCase(
            input = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab" to listOf(
                "a",
                "aa",
                "aaa",
                "aaaa",
                "aaaaa",
                "aaaaaa",
                "aaaaaaa",
                "aaaaaaaa",
                "aaaaaaaaa",
                "aaaaaaaaaa"
            ),
            output = false
        ),
        TestCase(
            input = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabaabaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" to listOf(
                "aa",
                "aaa",
                "aaaa",
                "aaaaa",
                "aaaaaa",
                "aaaaaaa",
                "aaaaaaaa",
                "aaaaaaaaa",
                "aaaaaaaaaa",
                "ba"
            ),
            output = false
        ),
        TestCase(
            input = "aaaaaaa" to listOf("aaaa", "aaa"),
            output = true
        )
    )

    override fun solve(testCaseInput: Pair<String, List<String>>): Boolean {
        return wordBreak(testCaseInput.first, testCaseInput.second)
    }

    private fun wordBreak(s: String, wordDict: List<String>): Boolean {
        val n = s.length
        val dp = BooleanArray(n + 1)
        dp[0] = true

        for (i in 1..n) {
            for (word in wordDict) {
                val start = i - word.length
                if (start >= 0 && dp[start] && s.substring(start, i) == word) {
                    dp[i] = true
                }
            }
        }

        return dp.last()
    }
}
