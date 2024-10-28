package dev.mslalith.array.problems

import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.core.TestCase

class FindPeakElement : TestCaseProblem<IntArray, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = FindPeakElement().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<IntArray, Int>> = arrayOf(
        TestCase(
            input = intArrayOf(1,2,3,1),
            output = 2
        ),
        TestCase(
            input = intArrayOf(3,2,1,3,5,6,4),
            output = 5
        ),
        TestCase(
            input = intArrayOf(3,2,1),
            output = 0
        )
    )

    override fun solve(testCaseInput: IntArray): Int {
        return findPeakElement(testCaseInput)
    }

    private fun findPeakElement(nums: IntArray): Int {
        val n = nums.size
        var left = 0
        var right = n - 1

        while (left <= right) {
            val mid = left + (right - left) / 2

            when {
                mid - 1 >= 0 && nums[mid - 1] > nums[mid] -> right = mid - 1
                mid + 1 < n && nums[mid] < nums[mid + 1] -> left = mid + 1
                else -> return mid
            }
        }

        return 0
    }

    private fun findPeakElementNaive(nums: IntArray): Int {
        if (nums.isEmpty()) return -1
        if (nums.size == 1) return 0
        if (nums.size == 2) return if (nums[0] > nums[1]) 0 else 1

        val n = nums.size
        var i = 1
        while (i < n - 1) {
            if (nums[i - 1] < nums[i] && nums[i] > nums[i + 1]) return i
            i++
        }
        if (nums[i - 1] < nums[i]) return i

        return 0
    }
}
