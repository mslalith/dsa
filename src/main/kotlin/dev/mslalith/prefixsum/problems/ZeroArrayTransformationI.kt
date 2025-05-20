package dev.mslalith.prefixsum.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem

class ZeroArrayTransformationI : TestCaseProblem<Pair<IntArray, Array<IntArray>>, Boolean>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = ZeroArrayTransformationI().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<Pair<IntArray, Array<IntArray>>, Boolean>> = arrayOf(
        TestCase(
            input = intArrayOf(1, 0, 1) to arrayOf(
                intArrayOf(0, 2)
            ),
            output = true
        ),
        TestCase(
            input = intArrayOf(4, 3, 2, 1) to arrayOf(
                intArrayOf(1, 3),
                intArrayOf(0, 2)
            ),
            output = false
        )
    )

    override fun solve(testCaseInput: Pair<IntArray, Array<IntArray>>): Boolean {
        return isZeroArray(testCaseInput.first, testCaseInput.second)
    }

    private fun isZeroArray(nums: IntArray, queries: Array<IntArray>): Boolean {
        val n = nums.size
        val diff = IntArray(n + 1)

        for (i in queries.indices) {
            val (l, r) = queries[i]
            diff[l] += 1
            diff[r + 1] -= 1
        }

        var sum = 0

        for (i in 0..<n) {
            sum += diff[i]
            if (sum < nums[i]) return false
        }

        return true
    }
}
