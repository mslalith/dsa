package dev.mslalith.dp.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem

class DecodeWays : TestCaseProblem<String, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = DecodeWays().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<String, Int>> = arrayOf(
        TestCase(
            input = "12",
            output = 2
        ),
        TestCase(
            input = "226",
            output = 3
        ),
        TestCase(
            input = "06",
            output = 0
        ),
        TestCase(
            input = "601",
            output = 0
        ),
        TestCase(
            input = "10",
            output = 1
        ),
        TestCase(
            input = "2101",
            output = 1
        )
    )

    override fun solve(testCaseInput: String): Int {
        return numDecodings(testCaseInput)
    }

    private fun numDecodings(s: String): Int {
        if (s.isEmpty()) return 0
        if (s[0] == '0') return 0

        val n = s.length
        var later = 1
        var next = if (s[n - 1] != '0') 1 else 0
        var curr: Int

        for (i in n - 2 downTo 0) {
            curr = if (s[i] == '0') 0 else {
                val takeOne = next
                val takeTwo = when {
                    s.substring(i, i + 2).toInt() <= 26 -> later
                    else -> 0
                }

                takeOne + takeTwo
            }
            later = next
            next = curr
        }

        return next
    }

    private fun numDecodingsDp(s: String): Int {
        if (s.isEmpty()) return 0
        if (s[0] == '0') return 0

        val n = s.length
        val dp = IntArray(n + 1)
        dp[n] = 1
        dp[n - 1] = if (s[n - 1] != '0') 1 else 0

        for (i in n - 2 downTo 0) {
            dp[i] = if (s[i] == '0') 0 else {
                val takeOne = dp[i + 1]
                val takeTwo = when {
                    s.substring(i, i + 2).toInt() <= 26 -> dp[i + 2]
                    else -> 0
                }

                takeOne + takeTwo
            }
        }

        return dp[0]
    }

    private fun numDecodingsRecursive(s: String): Int {
        if (s.isEmpty()) return 0
        if (s[0] == '0') return 0

        val n = s.length
        val dp = IntArray(n + 1) { -1 }

        fun findNumDecodings(i: Int): Int {
            if (i >= n || (i == n - 1 && s[i] != '0')) return 1
            if (s[i] == '0') return 0
            if (dp[i] != -1) return dp[i]

            val takeOne = findNumDecodings(i + 1)

            val takeTwo = when {
                s.substring(i, i + 2).toInt() <= 26 -> findNumDecodings(i + 2)
                else -> 0
            }

            dp[i] = takeOne + takeTwo
            return dp[i]
        }
        return findNumDecodings(0)
    }
}
