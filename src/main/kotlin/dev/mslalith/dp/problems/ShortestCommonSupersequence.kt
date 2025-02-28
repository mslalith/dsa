package dev.mslalith.dp.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import kotlin.math.max

class ShortestCommonSupersequence : TestCaseProblem<Pair<String, String>, String>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = ShortestCommonSupersequence().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<Pair<String, String>, String>> = arrayOf(
        TestCase(
            input = "abac" to "cab",
            output = "cabac"
        ),
        TestCase(
            input = "aaaaaaaa" to "aaaaaaaa",
            output = "aaaaaaaa"
        ),
        TestCase(
            input = "bbbaaaba" to "bbababbb",
            output = "bbbaaababbb"
        )
    )

    override fun solve(testCaseInput: Pair<String, String>): String {
        return shortestCommonSupersequence(testCaseInput.first, testCaseInput.second)
    }

    private fun shortestCommonSupersequence(str1: String, str2: String): String {
        if (str1 == str2) return str1

        val m = str1.length
        val n = str2.length
        val lcs = Array(m + 1) { IntArray(n + 1) }

        for (i in 1..m) {
            for (j in 1..n) {
                lcs[i][j] = when {
                    str1[i - 1] == str2[j - 1] -> 1 + lcs[i - 1][j - 1]
                    else -> max(lcs[i - 1][j], lcs[i][j - 1])
                }
            }
        }

        val sb = StringBuilder()
        var i = m
        var j = n

        while (i > 0 && j > 0) {
            when {
                str1[i - 1] == str2[j - 1] -> {
                    sb.insert(0, str1[i - 1])
                    i--
                    j--
                }
                lcs[i - 1][j] > lcs[i][j - 1] -> {
                    sb.insert(0, str1[i - 1])
                    i--
                }
                else -> {
                    sb.insert(0, str2[j - 1])
                    j--
                }
            }
        }

        while (i > 0) sb.insert(0, str1[--i])
        while (j > 0) sb.insert(0, str2[--j])

        return sb.toString()
    }
}
