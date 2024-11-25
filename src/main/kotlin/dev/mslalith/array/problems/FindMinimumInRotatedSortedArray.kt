package dev.mslalith.array.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import kotlin.math.min

class FindMinimumInRotatedSortedArray : TestCaseProblem<IntArray, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = FindMinimumInRotatedSortedArray().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<IntArray, Int>> = arrayOf(
        TestCase(
            input = intArrayOf(3, 4, 5, 1, 2),
            output = 1
        ),
        TestCase(
            input = intArrayOf(4, 5, 6, 7, 0, 1, 2),
            output = 0
        ),
        TestCase(
            input = intArrayOf(11, 13, 15, 17),
            output = 11
        ),
        TestCase(
            input = intArrayOf(2, 1),
            output = 1
        )
    )

    override fun solve(testCaseInput: IntArray): Int {
        return findMin(testCaseInput)
    }

    private fun findMin(nums: IntArray): Int {
        var left = 0
        var right = nums.size - 1
        var minimum = Int.MAX_VALUE

        while (left <= right) {
            val mid = left + (right - left) / 2
            when {
                left == right -> {
                    minimum = min(minimum, nums[left])
                    break
                }
                nums[left] <= nums[mid] -> {
                    minimum = min(minimum, nums[left])
                    left = mid + 1
                }
                nums[mid] <= nums[right] -> {
                    minimum = min(minimum, nums[mid])
                    right = mid - 1
                }
            }
        }

        return minimum
    }
}
