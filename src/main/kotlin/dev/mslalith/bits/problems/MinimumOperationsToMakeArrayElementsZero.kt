package dev.mslalith.bits.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem

class MinimumOperationsToMakeArrayElementsZero : TestCaseProblem<Array<IntArray>, Long>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = MinimumOperationsToMakeArrayElementsZero().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<Array<IntArray>, Long>> = arrayOf(
        TestCase(
            input = arrayOf(
                intArrayOf(1, 2),
                intArrayOf(2, 4)
            ),
            output = 3
        ),
        TestCase(
            input = arrayOf(
                intArrayOf(2, 6)
            ),
            output = 4
        )
    )

    override fun solve(testCaseInput: Array<IntArray>): Long {
        return minOperations(testCaseInput)
    }

    private fun minOperations(queries: Array<IntArray>): Long {
        var ops = 0L

        for ((l, r) in queries) {
            var s = 0L
            var maxi = 0

            for (k in 1..31) {
                val low = 1L shl (k - 1)
                val high = (1L shl k) - 1
                if (low > r) break

                val a = maxOf(l.toLong(), low)
                val b = minOf(r.toLong(), high)
                if (a > b) continue

                val count = b - a + 1
                val stepsForK = (k + 1) / 2
                s += count * stepsForK
                maxi = maxOf(maxi, stepsForK)
            }

            ops += maxOf(maxi.toLong(), (s + 1) / 2)
        }

        return ops
    }
}
