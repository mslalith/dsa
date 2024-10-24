package dev.mslalith.array.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem

class SearchInRotatedSortedArray : TestCaseProblem<Pair<IntArray, Int>, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = SearchInRotatedSortedArray().runAll()
    }

    override fun getTestCases(): Array<TestCase<Pair<IntArray, Int>, Int>> = arrayOf(
        TestCase(
            input = intArrayOf(4, 5, 6, 7, 0, 1, 2) to 0,
            output = 4
        ),
        TestCase(
            input = intArrayOf(4, 5, 6, 7, 0, 1, 2) to 3,
            output = -1
        ),
        TestCase(
            input = intArrayOf(-1) to 0,
            output = -1
        ),
        TestCase(
            input = intArrayOf(1) to 1,
            output = 0
        ),
        TestCase(
            input = intArrayOf(3, 1) to 1,
            output = 1
        ),
        TestCase(
            input = intArrayOf(4, 5, 6, 7, 8, 1, 2, 3) to 8,
            output = 4
        )
    )

    override fun solve(testCaseInput: Pair<IntArray, Int>): Int {
        return search(testCaseInput.first, testCaseInput.second)
    }

    private fun search(nums: IntArray, target: Int): Int {
        var left = 0
        var right = nums.size - 1

        while (left <= right) {
            val mid = left + (right - left) / 2
            when {
                nums[mid] == target -> return mid
                nums[left] <= nums[mid] -> when {
                    nums[left] <= target && target < nums[mid] -> right = mid - 1
                    else -> left = mid + 1
                }
                else -> when {
                    nums[mid] < target && target <= nums[right] -> left = mid + 1
                    else -> right = mid - 1
                }
            }
        }

        return -1
    }
}
