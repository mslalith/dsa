package dev.mslalith.array.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem

class TupleWithSameProduct : TestCaseProblem<IntArray, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = TupleWithSameProduct().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<IntArray, Int>> = arrayOf(
        TestCase(
            input = intArrayOf(2, 3, 4, 6),
            output = 8
        ),
        TestCase(
            input = intArrayOf(1, 2, 4, 5, 10),
            output = 16
        )
    )

    override fun solve(testCaseInput: IntArray): Int {
        return tupleSameProduct(testCaseInput)
    }

    private fun tupleSameProduct(nums: IntArray): Int {
        val n = nums.size
        if (n < 3) return 0

        val map = hashMapOf<Int, Int>()

        for (i in 0 until n) {
            for (j in (i + 1) until n) {
                val prod = nums[i] * nums[j]
                map[prod] = map.getOrDefault(prod, 0) + 1
            }
        }

        return map.values.sumOf {
            when {
                it > 1 -> it * (it - 1) / 2
                else -> 0
            }
        } * 8
    }
}
