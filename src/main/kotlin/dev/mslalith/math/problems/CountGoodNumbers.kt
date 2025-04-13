package dev.mslalith.math.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem

class CountGoodNumbers : TestCaseProblem<Long, Int>() {
    
    companion object {
        @JvmStatic
        fun main(args: Array<String>) = CountGoodNumbers().runForConsole()
    }
    
    override fun getTestCases(): Array<TestCase<Long, Int>> = arrayOf(
        TestCase(
            input = 1,
            output = 5
        ),
        TestCase(
            input = 4,
            output = 400
        ),
        TestCase(
            input = 50,
            output = 564908303
        ),
        TestCase(
            input = 806166225460393,
            output = 643535977
        )
    )
    
    override fun solve(testCaseInput: Long): Int {
        return countGoodNumbers(testCaseInput)
    }

    private fun countGoodNumbers(n: Long): Int {
        val mod = 1_000_000_000 + 7
        val evenPosCount = (n + 1) / 2
        val oddPosCount = n / 2

        fun fastPower(base: Long, power: Long): Long {
            var result = 1L
            var b = base % mod
            var p = power

            while (p > 0) {
                if (p % 2 == 1L) {
                    result = (result * b) % mod
                }
                b = (b * b) % mod
                p /= 2
            }

            return result
        }

        val evenWays = fastPower(5, evenPosCount)
        val oddWays = fastPower(4, oddPosCount)

        return ((evenWays * oddWays) % mod).toInt()
    }
}
