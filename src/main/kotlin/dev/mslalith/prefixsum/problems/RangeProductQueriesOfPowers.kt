package dev.mslalith.prefixsum.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem

class RangeProductQueriesOfPowers : TestCaseProblem<Pair<Int, Array<IntArray>>, IntArray>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = RangeProductQueriesOfPowers().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<Pair<Int, Array<IntArray>>, IntArray>> = arrayOf(
        TestCase(
            input = 15 to arrayOf(
                intArrayOf(0, 1),
                intArrayOf(2, 2),
                intArrayOf(0, 3)
            ),
            output = intArrayOf(2, 4, 64)
        ),
        TestCase(
            input = 2 to arrayOf(
                intArrayOf(0, 0)
            ),
            output = intArrayOf(2)
        )
    )

    override fun solve(testCaseInput: Pair<Int, Array<IntArray>>): IntArray {
        return productQueries(testCaseInput.first, testCaseInput.second)
    }

    private fun productQueries(n: Int, queries: Array<IntArray>): IntArray {
        val mod = 1_000_000_000 + 7

        var power = 1
        while (power <= n) power = power shl 1
        power = power shr 1

        var k = n
        val powers = mutableListOf<Int>()
        while (k > 0) {
            if (power <= k) {
                powers += power
                k -= power
            }
            power = power shr 1
        }

        k = powers.size
        val prefix = Array(k) { IntArray(k) }
        for (i in 0..<k) {
            prefix[i][i] = powers[k - 1 - i]
            for (j in i + 1..<k) prefix[i][j] = ((1L * prefix[i][j - 1] * powers[k - 1 - j]) % mod).toInt()
        }

        return IntArray(queries.size) {
            prefix[queries[it][0]][queries[it][1]]
        }
    }
}