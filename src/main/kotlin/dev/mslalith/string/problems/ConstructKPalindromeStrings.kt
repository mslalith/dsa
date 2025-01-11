package dev.mslalith.string.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem


class ConstructKPalindromeStrings : TestCaseProblem<Pair<String, Int>, Boolean>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = ConstructKPalindromeStrings().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<Pair<String, Int>, Boolean>> = arrayOf(
        TestCase(
            input = "annabelle" to 2,
            output = true
        ),
        TestCase(
            input = "leetcode" to 3,
            output = false
        ),
        TestCase(
            input = "true" to 4,
            output = true
        ),
        TestCase(
            input = "tree" to 4,
            output = true
        ),
        TestCase(
            input = "messi" to 3,
            output = true
        ),
        TestCase(
            input = "aaa" to 2,
            output = true
        )
    )

    override fun solve(testCaseInput: Pair<String, Int>): Boolean {
        return canConstruct(testCaseInput.first, testCaseInput.second)
    }

    private fun canConstruct(s: String, k: Int): Boolean {
        val n = s.length
        if (n < k) return false
        if (n == k) return true

        var mask = 0
        for (ch in s) {
            val position = ch - 'a'
            mask = mask xor (1 shl position)
        }

        var oddCount = 0
        for (i in 0 until 32) {
            if (mask and 1 == 1) oddCount++
            mask = mask shr 1
        }

        return oddCount <= k
    }

    private fun canConstructBrute(s: String, k: Int): Boolean {
        val n = s.length
        if (n < k) return false
        if (n == k) return true

        val charFrequencyArray = IntArray(26)
        for (i in s.indices) charFrequencyArray[s[i] - 'a']++

        if (charFrequencyArray.size == k) return true

        val oddCount = charFrequencyArray.count { it % 2 == 1 }
        return oddCount <= k
    }
}
