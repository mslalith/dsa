package dev.mslalith.string.problems

import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.core.TestCase

class ValidPalindrome : TestCaseProblem<String, Boolean>() {
    
    companion object {
        @JvmStatic
        fun main(args: Array<String>) = ValidPalindrome().runAll()
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

        var left = 0
        var right = n - 1

        while (left < right) {
            while (left < n && !isAlphaNumeric(s[left])) left++
            while (right >= 0 && !isAlphaNumeric(s[right])) right--
            if (left >= right) return true
            if (s[left].lowercaseChar() != s[right].lowercaseChar()) return false
            left++
            right--
        }

        return true
    }

    private fun isAlphaNumeric(char: Char): Boolean {
        return char in 'A'..'Z' || char in 'a'..'z' || char in '0'..'9'
    }
}