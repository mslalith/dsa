package dev.mslalith.math.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.math.algorithms.generatePrimesTill

class ClosestPrimeNumbersInRange : TestCaseProblem<Pair<Int, Int>, IntArray>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = ClosestPrimeNumbersInRange().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<Pair<Int, Int>, IntArray>> = arrayOf(
        TestCase(
            input = 10 to 19,
            output = intArrayOf(11, 13)
        ),
        TestCase(
            input = 4 to 6,
            output = intArrayOf(-1, -1)
        ),
        TestCase(
            input = 19 to 31,
            output = intArrayOf(29, 31)
        ),
        TestCase(
            input = 1087 to 4441,
            output = intArrayOf(1091, 1093)
        )
    )

    override fun solve(testCaseInput: Pair<Int, Int>): IntArray {
        return closestPrimes(testCaseInput.first, testCaseInput.second)
    }

    private val primes = generatePrimesTill(1_000_000)

    private fun closestPrimes(left: Int, right: Int): IntArray {
        val result = intArrayOf(-1, -1)
        if (right < 3) return result
        if (right - left < 2) return result
        if (primes.size < 2) return result

        var lastPrime = -1
        for (num in left..right) {
            if (!primes[num]) continue

            if (lastPrime == -1) {
                lastPrime = num
                continue
            }

            if (result[0] == -1 || (num - lastPrime) < (result[1] - result[0])) {
                result[0] = lastPrime
                result[1] = num
            }

            lastPrime = num
        }

        return result
    }
}
