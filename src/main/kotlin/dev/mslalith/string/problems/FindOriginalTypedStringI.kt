package dev.mslalith.string.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem

class FindOriginalTypedStringI : TestCaseProblem<String, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = FindOriginalTypedStringI().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<String, Int>> = arrayOf(
        TestCase(
            input = "abbcccc",
            output = 5
        ),
        TestCase(
            input = "abcd",
            output = 1
        ),
        TestCase(
            input = "aaaa",
            output = 4
        )
    )

    override fun solve(testCaseInput: String): Int {
        return possibleStringCount(testCaseInput)
    }

    private fun possibleStringCount(word: String): Int {
        val n = word.length
        var count = n

        for (i in 1..<n) if (word[i] != word[i - 1]) count--

        return count
    }
}
