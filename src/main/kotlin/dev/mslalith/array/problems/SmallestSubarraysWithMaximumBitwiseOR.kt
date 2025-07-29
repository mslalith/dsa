package dev.mslalith.array.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem

class SmallestSubarraysWithMaximumBitwiseOR : TestCaseProblem<IntArray, IntArray>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = SmallestSubarraysWithMaximumBitwiseOR().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<IntArray, IntArray>> = arrayOf(
        TestCase(
            input = intArrayOf(1, 0, 2, 1, 3),
            output = intArrayOf(3, 3, 2, 2, 1)
        ),
        TestCase(
            input = intArrayOf(1, 2),
            output = intArrayOf(2, 1)
        )
    )

    override fun solve(testCaseInput: IntArray): IntArray {
        return smallestSubarrays(testCaseInput)
    }

    private fun smallestSubarrays(nums: IntArray): IntArray {
        val lastSeen = IntArray(30)
        val res = IntArray(nums.size) { 1 }

        for (i in nums.indices.reversed()) {
            for (bit in 0..29) {
                if ((nums[i] and (1 shl bit)) > 0) lastSeen[bit] = i
                res[i] = maxOf(res[i], lastSeen[bit] - i + 1)
            }
        }

        return res
    }
}
