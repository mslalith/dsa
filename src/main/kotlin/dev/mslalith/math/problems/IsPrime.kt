package dev.mslalith.math.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem

class IsPrime : TestCaseProblem<Int, Boolean>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = IsPrime().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<Int, Boolean>> = arrayOf(
        TestCase(
            input = 10,
            output = false
        ),
        TestCase(
            input = 59,
            output = true
        )
    )

    override fun solve(testCaseInput: Int): Boolean {
        return isPrime(testCaseInput)
    }

    private fun isPrime(n: Int): Boolean {
        var count = 0

        var i = 2
        while ((i * i) <= n) {
            if (n % i == 0) {
                count++
                if (n % i != i) count++
            }
            i++
        }

        return count == 0
    }

    private fun isPrimeNaive(n: Int): Boolean {
        var count = 0

        for (i in 2 until n) {
            if (n % i == 0) count++
        }

        return count == 0
    }
}
