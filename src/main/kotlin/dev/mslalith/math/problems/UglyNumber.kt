package dev.mslalith.math.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem

class UglyNumber : TestCaseProblem<Int, Boolean>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = UglyNumber().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<Int, Boolean>> = arrayOf(
        TestCase(
            input = 6,
            output = true
        ),
        TestCase(
            input = 1,
            output = true
        ),
        TestCase(
            input = 14,
            output = false
        )
    )

    override fun solve(testCaseInput: Int): Boolean {
        return isUgly(testCaseInput)
    }

    private fun isUgly(n: Int): Boolean {
        if (n < 1) return false
        if (n < 2) return true

        var i = n
        while (i % 2 == 0) i /= 2
        while (i % 3 == 0) i /= 3
        while (i % 5 == 0) i /= 5

        return i == 1
    }
}
