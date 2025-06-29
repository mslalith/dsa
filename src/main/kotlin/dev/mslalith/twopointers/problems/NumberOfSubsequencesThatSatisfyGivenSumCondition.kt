package dev.mslalith.twopointers.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.utils.createClone

class NumberOfSubsequencesThatSatisfyGivenSumCondition : TestCaseProblem<Pair<IntArray, Int>, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = NumberOfSubsequencesThatSatisfyGivenSumCondition().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<Pair<IntArray, Int>, Int>> = arrayOf(
        TestCase(
            input = intArrayOf(3, 5, 6, 7) to 9,
            output = 4
        ),
        TestCase(
            input = intArrayOf(3, 3, 6, 8) to 10,
            output = 6
        ),
        TestCase(
            input = intArrayOf(2, 3, 3, 4, 6, 7) to 12,
            output = 61
        )
    )

    override fun solve(testCaseInput: Pair<IntArray, Int>): Int {
        return numSubseq(testCaseInput.first.createClone(), testCaseInput.second)
    }

    private fun numSubseq(nums: IntArray, target: Int): Int {
        nums.sort()

        val mod = 1_000_000_000 + 7
        val n = nums.size

        val power = IntArray(n) { 1 }
        for (i in 1..<n) power[i] = (power[i - 1] * 2) % mod

        var left = 0
        var right = n - 1
        var count = 0

        while (left <= right) {
            if (nums[left] + nums[right] <= target) {
                count = (count + power[right - left]) % mod
                left++
            } else {
                right--
            }
        }

        return count
    }

    private fun numSubseqNaive(nums: IntArray, target: Int): Int {
        nums.sort()

        val mod = 1_000_000_000 + 7
        var count = 0

        for (i in nums.indices) {
            for (j in i..<nums.size) {
                if (nums[i] + nums[j] <= target) {
                    count = (count + 1) % mod
                }
            }
        }

        return mod
    }
}
