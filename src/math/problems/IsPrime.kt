package src.math.problems

import src.core.Problem
import src.core.TestCase

class IsPrime : Problem<Int, Boolean>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = IsPrime().run()
    }

    override fun getTestCases(): Array<TestCase<Int, Boolean>> = arrayOf(
        TestCase(input = 10, output = false),
        TestCase(input = 59, output = true)
    )

    override fun solve(testCaseInput: Int): Boolean {
        return isPrime(testCaseInput)
    }

    private fun isPrime(n: Int): Boolean {
        if (n == 2 || n == 3) return true
        if (n % 2 == 0 || n % 3 == 0) return false
        var i = 5
        while (i * i <= n) {
            if (n % i == 0 || n % (i + 2) == 0) return false
            i += 6
        }
        return true
    }
}
