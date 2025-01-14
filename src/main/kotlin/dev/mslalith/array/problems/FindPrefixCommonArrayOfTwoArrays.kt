package dev.mslalith.array.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem

class FindPrefixCommonArrayOfTwoArrays : TestCaseProblem<Pair<IntArray, IntArray>, IntArray>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = FindPrefixCommonArrayOfTwoArrays().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<Pair<IntArray, IntArray>, IntArray>> = arrayOf(
        TestCase(
            input = intArrayOf(1, 3, 2, 4) to intArrayOf(3, 1, 2, 4),
            output = intArrayOf(0, 2, 3, 4)
        ),
        TestCase(
            input = intArrayOf(2, 3, 1) to intArrayOf(3, 1, 2),
            output = intArrayOf(0, 1, 3)
        )
    )

    override fun solve(testCaseInput: Pair<IntArray, IntArray>): IntArray {
        return findThePrefixCommonArray(testCaseInput.first, testCaseInput.second)
    }

    private fun findThePrefixCommonArray(a: IntArray, b: IntArray): IntArray {
        val n = a.size
        val frequency = IntArray(n + 1)
        val result = IntArray(n)

        var common = 0

        for (i in 0 until n) {
            frequency[a[i]]++
            frequency[b[i]]++

            when {
                a[i] == b[i] -> common++
                else -> {
                    if (frequency[a[i]] > 1) common++
                    if (frequency[b[i]] > 1) common++
                }
            }

            result[i] = common
        }

        return result
    }

    private fun findThePrefixCommonArrayBrute(a: IntArray, b: IntArray): IntArray {
        val n = a.size
        val seen1 = hashSetOf<Int>()
        val seen2 = hashSetOf<Int>()
        val result = IntArray(n)

        for (i in 0 until n) {
            seen1 += a[i]
            seen2 += b[i]

            result[i] = seen1.count { it in seen2 }
        }

        return result
    }
}
