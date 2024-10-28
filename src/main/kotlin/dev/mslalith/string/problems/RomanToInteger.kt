package dev.mslalith.string.problems

import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.core.TestCase

class RomanToInteger : TestCaseProblem<String, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = RomanToInteger().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<String, Int>> = arrayOf(
        TestCase(input = "III", output = 3),
        TestCase(input = "LVIII", output = 58),
        TestCase(input = "MCMXCIV", output = 1994)
    )

    override fun solve(testCaseInput: String): Int {
        return romanToInt(testCaseInput)
    }

    private fun romanToInt(s: String): Int {
        val mapping = hashMapOf(
            'I' to 1,
            'V' to 5,
            'X' to 10,
            'L' to 50,
            'C' to 100,
            'D' to 500,
            'M' to 1000
        )
        var sum = 0
        s.zipWithNext { curr, next ->
            val value = mapping.getOrDefault(curr, 0)
            if (value < mapping.getOrDefault(next, 0)) {
                sum -= value
            } else {
                sum += value
            }
        }
        sum += mapping.getOrDefault(s.last(), 0)
        return sum
    }
}