package dev.mslalith.string.problems

import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.core.TestCase
import kotlin.math.max

class LongestSubstringWithoutRepeatingCharacters : TestCaseProblem<String, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = LongestSubstringWithoutRepeatingCharacters().runAll()
    }

    override fun getTestCases(): Array<TestCase<String, Int>> = arrayOf(
        TestCase(input = "abcabcbb", output = 3),
        TestCase(input = "bbbbb", output = 1),
        TestCase(input = "pwwkew", output = 3)
    )

    override fun solve(testCaseInput: String): Int {
        return lengthOfLongestSubstring(testCaseInput)
    }

    private fun lengthOfLongestSubstring(s: String): Int {
        val set = hashSetOf<Char>()

        var length = 0
        var i = 0
        var j = 0

        while (i < s.length && j < s.length) {
            when {
                set.contains(s[j]) -> set.remove(s[i++])
                else -> set.add(s[j++])
            }
            length = max(length, j - i)
        }

        return length
    }
}