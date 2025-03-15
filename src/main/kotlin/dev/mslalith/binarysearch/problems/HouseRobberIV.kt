package dev.mslalith.binarysearch.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import kotlin.math.min

class HouseRobberIV : TestCaseProblem<Pair<IntArray, Int>, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = HouseRobberIV().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<Pair<IntArray, Int>, Int>> = arrayOf(
        TestCase(
            input = intArrayOf(2, 3, 5, 9) to 2,
            output = 5
        ),
        TestCase(
            input = intArrayOf(2, 7, 9, 3, 1) to 2,
            output = 2
        )
    )

    override fun solve(testCaseInput: Pair<IntArray, Int>): Int {
        return minCapability(testCaseInput.first, testCaseInput.second)
    }

    private fun minCapability(nums: IntArray, k: Int): Int {
        val n = nums.size

        fun canRob(capability: Int): Boolean {
            var i = 0
            var count = 0

            while (i < n) {
                if (nums[i] <= capability) {
                    count++
                    i++
                }
                i++
            }

            return count >= k
        }

        var left = nums.min()
        var right = nums.max()

        while (left < right) {
            val mid = left + (right - left) / 2
            if (canRob(mid)) right = mid
            else left = mid + 1
        }

        return left
    }

    private fun minCapabilityNaive(nums: IntArray, k: Int): Int {
        val n = nums.size

        var minRob = Int.MAX_VALUE
        var robbed = 0

        for (i in 0 until n) {
            var maxRob = 0
            robbed++

            for (j in (i + 2) until n step 2) {
                robbed++
                maxRob = maxOf(maxRob, nums[i], nums[j])

                if (robbed == k) {
                    robbed = 0
                    minRob = min(minRob, maxRob)
                }
            }

            if (robbed == k) {
                robbed = 0
                minRob = min(minRob, maxRob)
            }
        }

        return minRob
    }
}
