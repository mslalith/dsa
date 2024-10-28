package dev.mslalith.math.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.utils.precision

class PowOfXToN : TestCaseProblem<Pair<Double, Int>, Double>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = PowOfXToN().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<Pair<Double, Int>, Double>> = arrayOf(
        TestCase(
            input = 2.0 to 10,
            output = 1024.0
        ),
        TestCase(
            input = 2.10 to 3,
            output = 9.261
        ),
        TestCase(
            input = 2.0 to -2,
            output = 0.25
        ),
        TestCase(
            input = 1.0 to 2147483647,
            output = 1.0
        ),
        TestCase(
            input = 2.0 to -2147483648,
            output = 0.0
        )
    )

    override fun isTestPassed(actual: Double, expected: Double): Boolean {
        return actual.precision(6) == expected.precision(6)
    }

    override fun solve(testCaseInput: Pair<Double, Int>): Double {
        return myPow(testCaseInput.first, testCaseInput.second)
    }

    private fun myPow(x: Double, n: Int): Double {
        if (n == 0) return 1.0
        if (x == 1.0) return 1.0

        fun power(a: Double, b: Long): Double {
            if (b == 0L) return 1.0
            if (b == 1L) return a

            return if (b and 1 == 0L) {
                val res = power(a, b / 2)
                res * res
            } else {
                val res = power(a, (b - 1) / 2)
                res * res * a
            }
        }

        return if (n < 0) 1 / power(x, -n.toLong()) else power(x, n.toLong())
    }

    private fun myPowNaive(x: Double, n: Int): Double {
        if (n == 0) return 1.0
        if (x == 1.0) return 1.0

        val times = if (n < 0) n * -1L else n.toLong()
        var power = 1.0
        for (i in 0 until times) power *= x

        return if (n < 0) 1 / power else power
    }
}
