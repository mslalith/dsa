package dev.mslalith.math.problems

import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.core.TestCase

class SumOfDigits : TestCaseProblem<Int, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = SumOfDigits().runAll()
    }

    override fun getTestCases(): Array<TestCase<Int, Int>> = arrayOf(
        TestCase(123, 6),
        TestCase(89723478, 48)
    )

    override fun solve(testCaseInput: Int): Int {
        return reverseDigits(testCaseInput)
    }

    private fun reverseDigits(n: Int): Int {
        var x = n
        var sum = 0
        while (x > 0) {
            sum += x % 10
            x /= 10
        }
        return sum
    }
}
