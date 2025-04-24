package dev.mslalith.twopointers.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem

class CountCompleteSubarraysInArray : TestCaseProblem<IntArray, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = CountCompleteSubarraysInArray().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<IntArray, Int>> = arrayOf(
        TestCase(
            input = intArrayOf(1, 3, 1, 2, 2),
            output = 4
        ),
        TestCase(
            input = intArrayOf(5, 5, 5, 5),
            output = 10
        )
    )

    override fun solve(testCaseInput: IntArray): Int {
        return countCompleteSubarrays(testCaseInput)
    }

    private fun countCompleteSubarrays(nums: IntArray): Int {
        val distinctCount = nums.toSet().size

        val windowMapFreq = hashMapOf<Int, Int>()
        var left = 0
        var count = 0

        for (right in nums.indices) {
            val rightNum = nums[right]
            windowMapFreq[rightNum] = windowMapFreq.getOrDefault(rightNum, 0) + 1

            while (windowMapFreq.size == distinctCount) {
                count += nums.size - right

                val leftNum = nums[left]
                windowMapFreq[leftNum] = windowMapFreq.getOrDefault(leftNum, 0) - 1
                if (windowMapFreq.getValue(leftNum) == 0) windowMapFreq.remove(leftNum)

                left++
            }
        }

        return count
    }

    private fun countCompleteSubarraysNaive(nums: IntArray): Int {
        val n = nums.size
        val distinctCount = nums.toSet().size

        var count = 0

        for (i in 0 until n) {
            val set = hashSetOf<Int>()
            for (j in i until n) {
                set += nums[j]
                if (set.size >= distinctCount) count++
            }
        }

        return count
    }
}
