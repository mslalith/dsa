package dev.mslalith.math.problems

import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.core.TestCase

class NthTribonacciNumber : TestCaseProblem<Int, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = NthTribonacciNumber().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<Int, Int>> = arrayOf(
        TestCase(
            input = 4,
            output = 4
        ),
        TestCase(
            input = 25,
            output = 1389537
        )
    )

    override fun solve(testCaseInput: Int): Int {
        return tribonacci(testCaseInput)
    }

    private fun tribonacci(n: Int): Int {
        if (n == 0) return 0
        if (n == 1 || n == 2) return 1

        var first = 0
        var second = 1
        var third = 1

        var result = 0
        repeat(n - 2) {
            result = first + second + third
            first = second
            second = third
            third = result
        }

        return result
    }
}
