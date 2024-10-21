package dev.mslalith.string.problems

import dev.mslalith.core.Problem
import dev.mslalith.core.TestCase

class ReverseVowelsOfAString : Problem<String, String>() {
    
    companion object {
        @JvmStatic
        fun main(args: Array<String>) = ReverseVowelsOfAString().run()
    }
    
    override fun getTestCases(): Array<TestCase<String, String>> = arrayOf(
        TestCase(input = "hello", output = "holle"),
        TestCase(input = "leetcode", output = "leotcede"),
        TestCase(input = ".,", output = ".,")
    )
    
    override fun solve(testCaseInput: String): String {
        return reverseVowels(testCaseInput)
    }

    private fun reverseVowels(s: String): String {
        val n = s.length
        if (n == 0 || n == 1) return s

        val sb = StringBuilder(s)
        var left = 0
        var right = n - 1

        while (left < right) {
            while (left < n && !isVowel(sb[left])) left++
            while (right >= 0 && !isVowel(sb[right])) right--
            if (left >= right) break

            val temp = s[left]
            sb[left] = sb[right]
            sb[right] = temp
            left++
            right--
        }

        return sb.toString()
    }

    private fun isVowel(char: Char): Boolean {
        return when (char.lowercaseChar()) {
            'a', 'e', 'i', 'o', 'u' -> true
            else -> false
        }
    }
}