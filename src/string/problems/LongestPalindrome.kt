package src.string.problems

import src.core.Problem
import src.core.TestCase

class LongestPalindrome : Problem<String, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = LongestPalindrome().run()
    }

    override fun getTestCases(): Array<TestCase<String, Int>> = arrayOf(
        TestCase(input = "abccccdd", output = 7),
        TestCase(input = "a", output = 1)
    )

    override fun solve(testCaseInput: String): Int {
        return longestPalindrome(testCaseInput)
    }

    private fun longestPalindrome(s: String): Int {
        val map = hashMapOf<Char, Int>()
        var count = 0
        s.forEach { ch ->
            if (map.containsKey(ch)) {
                if (map[ch] == 1) {
                    count += 2
                    map.remove(ch)
                }
            } else map[ch] = 1
        }
        if (map.isNotEmpty()) count += 1
        return count
    }
}