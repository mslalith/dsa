package dev.mslalith.math.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.math.problems.CountNumberOfArraysWithKMatchingAdjacentElements.CountNumberOfArraysWithKMatchingAdjacentElementsParams

class CountNumberOfArraysWithKMatchingAdjacentElements : TestCaseProblem<CountNumberOfArraysWithKMatchingAdjacentElementsParams, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = CountNumberOfArraysWithKMatchingAdjacentElements().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<CountNumberOfArraysWithKMatchingAdjacentElementsParams, Int>> = arrayOf(
        TestCase(
            input = CountNumberOfArraysWithKMatchingAdjacentElementsParams(
                n = 3,
                m = 2,
                k = 1
            ),
            output = 4
        ),
        TestCase(
            input = CountNumberOfArraysWithKMatchingAdjacentElementsParams(
                n = 4,
                m = 2,
                k = 2
            ),
            output = 6
        ),
        TestCase(
            input = CountNumberOfArraysWithKMatchingAdjacentElementsParams(
                n = 5,
                m = 2,
                k = 0
            ),
            output = 2
        )
    )

    override fun solve(testCaseInput: CountNumberOfArraysWithKMatchingAdjacentElementsParams): Int {
        return countGoodArrays(testCaseInput.n, testCaseInput.m, testCaseInput.k)
    }

    private fun countGoodArrays(n: Int, m: Int, k: Int): Int {
        val mod = 1_000_000_000 + 7

        fun power(a: Long, b: Long): Long {
            if (b == 0L) return 1

            val half = power(a, b / 2)
            var result = (half * half) % mod
            if (b % 2 == 1L) result = (result * a) % mod

            return result
        }

        fun modInverse(a: Long) = power(a, mod - 2L)

        val factorial = LongArray(n) { 1 }
        for (i in 2..<n) factorial[i] = (factorial[i - 1] * i) % mod

        fun nCr(n: Int, r: Int): Long {
            if (r < 0 || r > n) return 0

            val numerator = factorial[n]
            val denominator = (factorial[r] * factorial[n - r]) % mod
            val inverseDenominator = modInverse(denominator)
            val result = (numerator * inverseDenominator) % mod

            return result
        }


        val numberOfWaysToPickIndices = nCr(n - 1, n - 1 - k)
        val numberOfWaysToFillIndices = power(m - 1L, n - 1L - k)
        val ans = ((numberOfWaysToPickIndices * numberOfWaysToFillIndices) % mod * m) % mod

        return ans.toInt()
    }

    data class CountNumberOfArraysWithKMatchingAdjacentElementsParams(
        val n: Int,
        val m: Int,
        val k: Int
    ) {
        override fun toString(): String {
            return """
                
                n: $n
                m: $m
                k: $k
            """.trimIndent()
        }
    }
}
