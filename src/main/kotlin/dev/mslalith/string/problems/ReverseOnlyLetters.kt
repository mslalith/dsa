package dev.mslalith.string.problems

import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.core.TestCase

class ReverseOnlyLetters : TestCaseProblem<String, String>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = ReverseOnlyLetters().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<String, String>> = arrayOf(
        TestCase(input = "ab-cd", output = "dc-ba"),
        TestCase(input = "a-bC-dEf-ghIj", output = "j-Ih-gfE-dCba"),
        TestCase(input = "Test1ng-Leet=code-Q!", output = "Qedo1ct-eeLg=ntse-T!")
    )

    override fun solve(testCaseInput: String): String {
        return reverseOnlyLetters(testCaseInput)
    }

    private fun reverseOnlyLetters(s: String): String {
        val n = s.length
        if (n == 0 || n == 1) return s

        val sb = StringBuilder(s)
        var left = 0
        var right = n - 1

        while (left < right) {
            while (left < n && !isAlphabet(sb[left])) left++
            while (right >= 0 && !isAlphabet(sb[right])) right--
            if (left >= right) return sb.toString()

            val temp = sb[left]
            sb[left] = sb[right]
            sb[right] = temp
            left++
            right--
        }

        return sb.toString()
    }

    private fun isAlphabet(char: Char): Boolean {
        return char in 'a'..'z' || char in 'A'..'Z'
    }
}
