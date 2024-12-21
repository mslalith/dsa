package dev.mslalith.string.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import kotlin.math.max

class ConsecutiveCharacters : TestCaseProblem<String, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = ConsecutiveCharacters().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<String, Int>> = arrayOf(
        TestCase(
            input = "leetcode",
            output = 2
        ),
        TestCase(
            input = "abbcccddddeeeeedcba",
            output = 5
        ),
        TestCase(
            input = "cc",
            output = 2
        )
    )

    override fun solve(testCaseInput: String): Int {
        return maxPower(testCaseInput)
    }

    private fun maxPower(s: String): Int {
        val n = s.length
        var maxCount = 1
        var start = 0

        var i = 1
        while (i < n) {
            while (i < n && s[i] == s[start]) i++
            maxCount = max(maxCount, i - start)
            start = i
            i++
        }

        return maxCount
    }
}
