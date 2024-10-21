package dev.mslalith.dp.problems

import dev.mslalith.core.Problem
import dev.mslalith.core.TestCase
import kotlin.math.max

class LongestCommonSubsequence : Problem<Pair<String, String>, Int>() {
    
    companion object {
        @JvmStatic
        fun main(args: Array<String>) = LongestCommonSubsequence().run()
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
        )
    )
    
    override fun solve(testCaseInput: Pair<String, String>): Int {
        return longestCommonSubsequence(testCaseInput.first, testCaseInput.second)
    }

    private fun longestCommonSubsequence(text1: String, text2: String): Int {
        val n = text1.length
        val m = text2.length

        val dp = Array(n + 1) {
            IntArray(m + 1) { 0 }
        }

        for (i in 1..n) {
            for (j in 1..m) {
                if (text1[i - 1] == text2[j - 1]) {
                    dp[i][j] = 1 + dp[i - 1][j - 1]
                } else {
                    dp[i][j] = max(dp[i - 1][j], dp[i][j - 1])
                }
            }
        }

        return dp.last().last()
    }
}
