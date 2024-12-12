package dev.mslalith.math.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem

class FactorialTrailingZeroes : TestCaseProblem<Int, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = FactorialTrailingZeroes().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<Int, Int>> = arrayOf(
        TestCase(
            input = 3,
            output = 0
        ),
        TestCase(
            input = 5,
            output = 1
        ),
        TestCase(
            input = 20,
            output = 4
        ),
        TestCase(
            input = 100,
            output = 24
        )
    )

    override fun solve(testCaseInput: Int): Int {
        return trailingZeroes(testCaseInput)
    }

    private fun trailingZeroes(n: Int): Int {
        if (n < 0) return -1

        var count = 0
        var i = 5

        while (i <= n) {
            count += n / i
            i *= 5
        }

        return count
    }
}
