package dev.mslalith.array.problems

import dev.mslalith.core.Problem
import dev.mslalith.core.TestCase

class FindHighestAltitude : Problem<IntArray, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = FindHighestAltitude().run()
    }

    override fun getTestCases(): Array<TestCase<IntArray, Int>> = arrayOf(
        TestCase(
            input = intArrayOf(-5, 1, 5, 0, -7),
            output = 1
        ),
        TestCase(
            input = intArrayOf(-4, -3, -2, -1, 4, 3, 2),
            output = 0
        ),
        TestCase(
            input = intArrayOf(44, 32, -9, 52, 23, -50, 50, 33, -84, 47, -14, 84, 36, -62, 37, 81, -36, -85, -39, 67, -63, 64, -47, 95, 91, -40, 65, 67, 92, -28, 97, 100, 81),
            output = 781
        )
    )

    override fun solve(testCaseInput: IntArray): Int {
        return largestAltitude(testCaseInput)
    }

    private fun largestAltitude(gain: IntArray): Int {
        val n = gain.size
        val prefixSum = IntArray(n + 1) { 0 }
        var prefix = 0

        for (i in gain.indices) {
            prefixSum[i] = prefix
            prefix += gain[i]
        }
        prefixSum[n] = prefix

        return prefixSum.max()
    }
}
