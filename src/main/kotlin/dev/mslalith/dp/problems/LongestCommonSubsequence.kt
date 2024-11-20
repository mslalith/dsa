package dev.mslalith.dp.problems

import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.core.TestCase
import kotlin.math.max

class LongestCommonSubsequence : TestCaseProblem<Pair<String, String>, Int>() {
    
    companion object {
        @JvmStatic
        fun main(args: Array<String>) = LongestCommonSubsequence().runForConsole()
    }
    
    override fun getTestCases(): Array<TestCase<Pair<String, String>, Int>> = arrayOf(
        TestCase(
            input = "abcde" to "ace",
            output = 3
        ),
        TestCase(
            input = "abc" to "abc",
            output = 3
        ),
        TestCase(
            input = "abc" to "def",
            output = 0
        ),
        TestCase(
            input = "bl" to "yby",
            output = 1
        ),
        TestCase(
            input = "ezupkr" to "ubmrapg",
            output = 2
        ),
        TestCase(
            input = "abcba" to "abcbcba",
            output = 5
        )
    )
    
    override fun solve(testCaseInput: Pair<String, String>): Int {
        return longestCommonSubsequence(testCaseInput.first, testCaseInput.second)
    }

    private fun longestCommonSubsequence(text1: String, text2: String): Int {
        if (text1 == text2) return text1.length

        val m = text1.length
        val n = text2.length
        val curr = IntArray(n + 1)
        var next = IntArray(n + 1)

        for (i in (m - 1) downTo 0) {
            for (j in (n - 1) downTo 0) {
                curr[j] = if (text1[i] == text2[j]) {
                    1 + next[j + 1]
                } else {
                    max(next[j], curr[j + 1])
                }
            }
            next = curr.clone()
        }

        return next[0]
    }

    private fun longestCommonSubsequenceDp(text1: String, text2: String): Int {
        if (text1 == text2) return text1.length

        val m = text1.length
        val n = text2.length
        val dp = Array(m + 1) { IntArray(n + 1) }

        for (i in (m - 1) downTo 0) {
            for (j in (n - 1) downTo 0) {
                dp[i][j] = if (text1[i] == text2[j]) {
                    1 + dp[i + 1][j + 1]
                } else {
                    max(dp[i + 1][j], dp[i][j + 1])
                }
            }
        }

        return dp[0][0]
    }

    private fun longestCommonSubsequenceRecursive(text1: String, text2: String): Int {
        if (text1 == text2) return text1.length

        val m = text1.length
        val n = text2.length
        val dp = Array(m) { IntArray(n) { -1 } }

        fun findLongestCommonSubsequence(i: Int, j: Int): Int {
            if (i == m || j == n) return 0
            if (dp[i][j] != -1) return dp[i][j]

            dp[i][j] = if (text1[i] == text2[j]) {
                1 + findLongestCommonSubsequence(i + 1, j + 1)
            } else {
                max(findLongestCommonSubsequence(i + 1, j), findLongestCommonSubsequence(i, j + 1))
            }

            return dp[i][j]
        }

        return findLongestCommonSubsequence(0, 0)
    }
}
