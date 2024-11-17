package dev.mslalith.dp.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem


class LongestPalindromicSubstring : TestCaseProblem<String, String>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = LongestPalindromicSubstring().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<String, String>> = arrayOf(
        TestCase(
            input = "babad",
            output = "bab"
        ),
        TestCase(
            input = "cbbd",
            output = "bb"
        ),
        TestCase(
            input = "aacabdkacaa",
            output = "aca"
        )
    )

    override fun solve(testCaseInput: String): String {
        return longestPalindrome(testCaseInput)
    }

    private fun longestPalindrome(s: String): String {
        val n = s.length
        if (n <= 1) return s

        fun expandFromCenter(i: Int, j: Int): String {
            var l = i
            var r = j

            while (l >= 0 && r < n && s[l] == s[r]) {
                l--
                r++
            }

            return s.substring(l + 1, r)
        }

        var maxStr = s.substring(0, 1)

        for (i in 0 until n - 1) {
            val odd = expandFromCenter(i, i)
            val even = expandFromCenter(i, i + 1)

            if (odd.length > maxStr.length) maxStr = odd
            if (even.length > maxStr.length) maxStr = even
        }

        return maxStr
    }
}
