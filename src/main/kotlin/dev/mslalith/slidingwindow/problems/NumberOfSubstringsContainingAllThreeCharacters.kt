package dev.mslalith.slidingwindow.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem

class NumberOfSubstringsContainingAllThreeCharacters : TestCaseProblem<String, Int>() {
    
    companion object {
        @JvmStatic
        fun main(args: Array<String>) = NumberOfSubstringsContainingAllThreeCharacters().runForConsole()
    }
    
    override fun getTestCases(): Array<TestCase<String, Int>> = arrayOf(
        TestCase(
            input = "abcabc",
            output = 10
        ),
        TestCase(
            input = "aaacb",
            output = 3
        ),
        TestCase(
            input = "abc",
            output = 1
        )
    )
    
    override fun solve(testCaseInput: String): Int {
        return numberOfSubstrings(testCaseInput)
    }

    private fun numberOfSubstrings(s: String): Int {
        val lastSeen = IntArray(3) { -1 }
        var count = 0

        for (i in s.indices) {
            lastSeen[s[i] - 'a'] = i
            count += lastSeen.min() + 1
        }

        return count
    }
}
