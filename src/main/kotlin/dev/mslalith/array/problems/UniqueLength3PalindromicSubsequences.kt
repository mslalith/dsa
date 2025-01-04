package dev.mslalith.array.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem

class UniqueLength3PalindromicSubsequences : TestCaseProblem<String, Int>() {
    
    companion object {
        @JvmStatic
        fun main(args: Array<String>) = UniqueLength3PalindromicSubsequences().runForConsole()
    }
    
    override fun getTestCases(): Array<TestCase<String, Int>> = arrayOf(
        TestCase(
            input = "aabca",
            output = 3
        ),
        TestCase(
            input = "adc",
            output = 0
        ),
        TestCase(
            input = "bbcbaba",
            output = 4
        )
    )
    
    override fun solve(testCaseInput: String): Int {
        return countPalindromicSubsequence(testCaseInput)
    }

    private fun countPalindromicSubsequence(s: String): Int {
        val map = hashMapOf<Char, Pair<Int, Int>>()
        for (i in s.indices) {
            val ch = s[i]
            if (ch !in map) map[ch] = i to i
            map[ch] = map.getValue(ch).copy(second = i)
        }

        if (map.size == s.length) return 0

        var count = 0
        val seen = hashSetOf<Char>()

        for ((ch, indexes) in map) {
            val (left, right) = indexes
            if (right - left < 2) continue

            for (i in (left + 1) until right) seen += s[i]

            count += seen.size
            seen.clear()
        }

        return count
    }
}
