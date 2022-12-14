package src.string.problems

import src.core.Problem
import src.core.TestCase

class LongestSubstringWithoutRepeatingCharacters : Problem<String, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = LongestSubstringWithoutRepeatingCharacters().run()
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
        var length = 0
        var i = 0
        var j = 0
        val set = hashSetOf<Char>()
        while (i < s.length && j < s.length) {
            if (set.contains(s[j])) {
                set.remove(s[i++])
            } else {
                set.add(s[j++])
            }
            length = Math.max(length, j - i)
        }
        return length
    }
}