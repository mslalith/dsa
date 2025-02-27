package dev.mslalith.array.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import kotlin.math.max

class LengthOfLongestFibonacciSubsequence : TestCaseProblem<IntArray, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = LengthOfLongestFibonacciSubsequence().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<IntArray, Int>> = arrayOf(
        TestCase(
            input = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8),
            output = 5
        ),
        TestCase(
            input = intArrayOf(1, 3, 7, 11, 12, 14, 18),
            output = 3
        )
    )

    override fun solve(testCaseInput: IntArray): Int {
        return lenLongestFibSubseq(testCaseInput)
    }

    private fun lenLongestFibSubseq(arr: IntArray): Int {
        val n = arr.size
        if (n < 3) return 0

        var maxLongest = 2
        val set = arr.toHashSet()

        for (i in 0 until n) {
            for (j in (i + 1) until n) {
                if (i + j >= n) continue

                var count = 2
                var num1 = arr[i]
                var num2 = arr[j]

                while ((num1 + num2) in set) {
                    val sum = num1 + num2
                    num1 = num2
                    num2 = sum
                    count++
                }

                maxLongest = max(maxLongest, count)
            }
        }

        return if (maxLongest > 2) maxLongest else 0
    }
}
