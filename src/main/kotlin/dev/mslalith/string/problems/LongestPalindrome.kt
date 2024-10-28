package dev.mslalith.string.problems

import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.core.TestCase

class LongestPalindrome : TestCaseProblem<String, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = LongestPalindrome().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<String, Int>> = arrayOf(
        TestCase(input = "abccccdd", output = 7),
        TestCase(input = "a", output = 1)
    )

    override fun solve(testCaseInput: String): Int {
        return longestPalindrome(testCaseInput)
    }

    private fun longestPalindrome(s: String): Int {
        val set = hashSetOf<Char>()
        var count = 0
        s.forEach { ch ->
            if (!set.add(ch)) {
                count += 2
                set.remove(ch)
            }
        }
        if (set.isNotEmpty()) count += 1
        return count
    }
}