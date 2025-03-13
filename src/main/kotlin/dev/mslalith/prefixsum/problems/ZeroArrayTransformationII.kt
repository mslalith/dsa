package dev.mslalith.prefixsum.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.utils.createClone

class ZeroArrayTransformationII : TestCaseProblem<Pair<IntArray, Array<IntArray>>, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = ZeroArrayTransformationII().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<Pair<IntArray, Array<IntArray>>, Int>> = arrayOf(
        TestCase(
            input = intArrayOf(2, 0, 2) to arrayOf(
                intArrayOf(0, 2, 1),
                intArrayOf(0, 2, 1),
                intArrayOf(1, 1, 3)
            ),
            output = 2
        ),
        TestCase(
            input = intArrayOf(4, 3, 2, 1) to arrayOf(
                intArrayOf(1, 3, 2),
                intArrayOf(0, 2, 1)
            ),
            output = -1
        )
    )

    override fun solve(testCaseInput: Pair<IntArray, Array<IntArray>>): Int {
        return minZeroArray(testCaseInput.first.createClone(), testCaseInput.second)
    }

    private fun minZeroArray(nums: IntArray, queries: Array<IntArray>): Int {
        val n = nums.size

        fun canMakeZero(k: Int): Boolean {
            val diff = IntArray(n + 1)

            for (i in 0 until k) {
                val (l, r, value) = queries[i]
                diff[l] += value
                diff[r + 1] -= value
            }

            var sum = 0

            for (i in 0 until n) {
                sum += diff[i]
                if (sum < nums[i]) return false
            }

            return true
        }

        var left = 0
        var right = queries.size
        if (!canMakeZero(right)) return -1

        while (left < right) {
            val mid = left + (right - left) / 2
            if (canMakeZero(mid)) right = mid // looks for smaller
            else left = mid + 1
        }

        return left
    }

    private fun minZeroArrayNaive(nums: IntArray, queries: Array<IntArray>): Int {
        val n = nums.size

        var zeroCount = nums.count { it == 0 }
        if (zeroCount == n) return 0

        var count = 0

        for ((l, r, value) in queries) {
            for (i in l..r) {
                if (nums[i] == 0) continue
                val sub = nums[i] - value
                nums[i] = if (sub < 0) 0 else sub
                if (nums[i] == 0) zeroCount++
            }
            count++
            if (zeroCount == n) break
        }

        val hasNonZeros = nums.any { it > 0 }
        if (hasNonZeros) return -1

        return count
    }
}
