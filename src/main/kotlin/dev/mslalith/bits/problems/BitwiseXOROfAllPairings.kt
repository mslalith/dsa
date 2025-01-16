package dev.mslalith.bits.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem

class BitwiseXOROfAllPairings : TestCaseProblem<Pair<IntArray, IntArray>, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = BitwiseXOROfAllPairings().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<Pair<IntArray, IntArray>, Int>> = arrayOf(
        TestCase(
            input = intArrayOf(2, 1, 3) to intArrayOf(10, 2, 5, 0),
            output = 13
        ),
        TestCase(
            input = intArrayOf(1, 2) to intArrayOf(3, 4),
            output = 0
        )
    )

    override fun solve(testCaseInput: Pair<IntArray, IntArray>): Int {
        return xorAllNums(testCaseInput.first, testCaseInput.second)
    }

    private fun xorAllNums(nums1: IntArray, nums2: IntArray): Int {
        var xorNum = 0

        if (nums1.size % 2 != 0) {
            for (num in nums2) xorNum = xorNum xor num
        }

        if (nums2.size % 2 != 0) {
            for (num in nums1) xorNum = xorNum xor num
        }

        return xorNum
    }

    private fun xorAllNumsBrute(nums1: IntArray, nums2: IntArray): Int {
        var xorNum = 0

        for (num1 in nums1) {
            for (num2 in nums2) {
                xorNum = xorNum xor (num1 xor num2)
            }
        }

        return xorNum
    }
}
