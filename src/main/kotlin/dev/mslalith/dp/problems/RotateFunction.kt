package dev.mslalith.dp.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import kotlin.math.max

class RotateFunction : TestCaseProblem<IntArray, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = RotateFunction().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<IntArray, Int>> = arrayOf(
        TestCase(
            input = intArrayOf(4, 3, 2, 6),
            output = 26
        ),
        TestCase(
            input = intArrayOf(100),
            output = 0
        )
    )

    override fun solve(testCaseInput: IntArray): Int {
        return maxRotateFunction(testCaseInput)
    }

    private fun maxRotateFunction(nums: IntArray): Int {
        val n = nums.size
        if (n <= 1) return 0

        var res = 0
        var totalSum = 0
        for (i in 0 until n) {
            totalSum += nums[i]
            res += i * nums[i]
        }

        var maxi = res
        var prev = res

        for (i in 1 until n) {
            res = (prev + totalSum) - (n * nums[n - i])
            maxi = max(maxi, res)
            prev = res
        }

        return maxi
    }

    private fun maxRotateFunctionNaive(nums: IntArray): Int {
        val n = nums.size
        if (n <= 1) return 0

        var maxi = Int.MIN_VALUE

        for (offset in 0 until n) {
            var ans = 0
            for (i in 0 until n) {
                val ind = if (i - offset < 0) n + (i - offset) else i - offset
                ans += i * nums[ind]
            }
            maxi = max(maxi, ans)
        }

        return maxi
    }
}
