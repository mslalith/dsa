package dev.mslalith.dp.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import kotlin.math.max

class MaximumProductSubarray : TestCaseProblem<IntArray, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = MaximumProductSubarray().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<IntArray, Int>> = arrayOf(
        TestCase(
            input = intArrayOf(2, 3, -2, 4),
            output = 6
        ),
        TestCase(
            input = intArrayOf(-2, 0, -1),
            output = 0
        ),
        TestCase(
            input = intArrayOf(3, -1, 4),
            output = 4
        )
    )

    override fun solve(testCaseInput: IntArray): Int {
        return maxProduct(testCaseInput)
    }

    private fun maxProduct(nums: IntArray): Int {
        val n = nums.size
        var prod = 1
        var maxProd = Int.MIN_VALUE

        for (i in 0 until n) {
            prod *= nums[i]
            maxProd = max(maxProd, prod)
            if (prod == 0) prod = 1
        }

        prod = 1
        for (i in (n - 1) downTo 0) {
            prod *= nums[i]
            maxProd = max(maxProd, prod)
            if (prod == 0) prod = 1
        }

        return maxProd
    }
}
