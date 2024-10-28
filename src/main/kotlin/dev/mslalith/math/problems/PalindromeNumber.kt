package dev.mslalith.math.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem

class PalindromeNumber : TestCaseProblem<Int, Boolean>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = PalindromeNumber().runAll()
    }

    override fun getTestCases(): Array<TestCase<Int, Boolean>> = arrayOf(
        TestCase(
            input = 121,
            output = true
        ),
        TestCase(
            input = -121,
            output = false
        ),
        TestCase(
            input = 10,
            output = false
        )
    )

    override fun solve(testCaseInput: Int): Boolean {
        return isPalindrome(testCaseInput)
    }

    private fun isPalindrome(x: Int): Boolean {
        if (x < 0) return false
        if (x == 0) return true

        var reverse = 0
        var num = x
        while (num != 0) {
            val digit = num % 10
            reverse = (reverse * 10) + digit
            num /= 10
        }

        return reverse == x
    }
}
