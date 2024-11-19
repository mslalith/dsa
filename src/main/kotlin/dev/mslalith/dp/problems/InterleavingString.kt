package dev.mslalith.dp.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem

class InterleavingString : TestCaseProblem<Pair<Pair<String, String>, String>, Boolean>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = InterleavingString().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<Pair<Pair<String, String>, String>, Boolean>> = arrayOf(
        TestCase(
            input = ("aabcc" to "dbbca") to "aadbbcbcac",
            output = true
        ),
        TestCase(
            input = ("aabcc" to "dbbca") to "aadbbbaccc",
            output = false
        ),
        TestCase(
            input = ("" to "") to "",
            output = true
        ),
        TestCase(
            input = ("aabc" to "abad") to "aabadabc",
            output = true
        ),
        TestCase(
            input = ("bbbcc" to "bbaccbbbabcacc") to "bbbbacbcccbcbabbacc",
            output = false
        )
    )

    override fun solve(testCaseInput: Pair<Pair<String, String>, String>): Boolean {
        return isInterleave(testCaseInput.first.first, testCaseInput.first.second, testCaseInput.second)
    }

    private fun isInterleave(s1: String, s2: String, s3: String): Boolean {
        val a = s1.length
        val b = s2.length
        val c = s3.length
        if (a == 0 && b == 0 && c == 0) return true
        if (a + b != c) return false

        val dp = Array(a + 1) { BooleanArray(b + 1) }
        dp[0][0] = true

        for (i in 1..a) dp[i][0] = s1[i - 1] == s3[i - 1]
        for (j in 1..b) dp[0][j] = s2[j - 1] == s3[j - 1]

        for (i in 1..a) {
            for (j in 1..b) {
                // matching with s1, check if previous chars can interleave
                val s1Match = s1[i - 1] == s3[i + j - 1] && dp[i - 1][j]

                // matching with s2, check if previous chars can interleave
                val s2Match = s2[j - 1] == s3[i + j - 1] && dp[i][j - 1]

                dp[i][j] = s1Match || s2Match
            }
        }

        return dp[a][b]
    }

    private fun isInterleaveRecursive(s1: String, s2: String, s3: String): Boolean {
        val a = s1.length
        val b = s2.length
        val c = s3.length
        if (a == 0 && b == 0 && c == 0) return true
        if (a + b != c) return false

        val dp = Array(a + 1) { Array(b + 1) { -1 } }

        fun findIsInterleave(i: Int, j: Int, k: Int): Boolean {
            if (k == c) return true
            if (dp[i][j] != -1) return dp[i][j] == 1

            var result = false
            if (i < a && s1[i] == s3[k]) {
                result = result || findIsInterleave(i + 1, j, k + 1)
            }
            if (j < b && s2[j] == s3[k]) {
                result = result || findIsInterleave(i, j + 1, k + 1)
            }

            dp[i][j] = if (result) 1 else 0
            return result
        }

        return findIsInterleave(0, 0, 0)
    }
}
