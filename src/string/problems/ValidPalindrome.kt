package src.string.problems

import src.core.Problem
import src.core.TestCase

class ValidPalindrome : Problem<String, Boolean>() {
    
    companion object {
        @JvmStatic
        fun main(args: Array<String>) = ValidPalindrome().run()
    }
    
    override fun getTestCases(): Array<TestCase<String, Boolean>> = arrayOf(
        TestCase(input = "A man, a plan, a canal: Panama", output = true),
        TestCase(input = "race a car", output = false),
        TestCase(input = " ", output = true)
    )
    
    override fun solve(testCaseInput: String): Boolean {
        return isPalindrome(testCaseInput)
    }

    private fun isPalindrome(s: String): Boolean {
        val n = s.length
        if (n == 0 || n == 1) return true

        val sb = StringBuilder(s)
        var left = 0
        var right = n - 1

        while (left < right) {
            while (left < n && !isAlphaNumeric(sb[left])) left++
            while (right >= 0 && !isAlphaNumeric(sb[right])) right--
            if (left >= right) return true
            if (sb[left].lowercaseChar() != sb[right].lowercaseChar()) return false
            left++
            right--
        }

        return true
    }

    private fun isAlphaNumeric(char: Char): Boolean {
        return char in 'A'..'Z' || char in 'a'..'z' || char in '0'..'9'
    }
}