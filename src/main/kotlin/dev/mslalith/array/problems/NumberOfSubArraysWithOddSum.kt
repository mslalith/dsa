package dev.mslalith.array.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem

class NumberOfSubArraysWithOddSum : TestCaseProblem<IntArray, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = NumberOfSubArraysWithOddSum().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<IntArray, Int>> = arrayOf(
        TestCase(
            input = intArrayOf(1, 3, 5),
            output = 4
        ),
        TestCase(
            input = intArrayOf(2, 4, 6),
            output = 0
        ),
        TestCase(
            input = intArrayOf(1, 2, 3, 4, 5, 6, 7),
            output = 16
        )
    )

    override fun solve(testCaseInput: IntArray): Int {
        return numOfSubarrays(testCaseInput)
    }

    private fun numOfSubarrays(arr: IntArray): Int {
        val mod = 1_000_000_000 + 7

        var oddCount = 0
        var evenCount = 1 // as 0 is even
        var sum = 0
        var result = 0

        for (num in arr) {
            sum += num
            if (sum and 1 == 0) {
                result = (result + oddCount) % mod
                evenCount++
            } else {
                result = (result + evenCount) % mod
                oddCount++
            }
        }

        return result
    }
}
