package dev.mslalith.array.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem

class FindFirstAndLastPositionOfElementInSortedArray : TestCaseProblem<Pair<IntArray, Int>, IntArray>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = FindFirstAndLastPositionOfElementInSortedArray().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<Pair<IntArray, Int>, IntArray>> {
        return arrayOf(
            TestCase(
                input = intArrayOf(5, 7, 7, 8, 8, 10) to 8,
                output = intArrayOf(3, 4)
            ),
            TestCase(
                input = intArrayOf(5, 7, 7, 8, 8, 10) to 6,
                output = intArrayOf(-1, -1)
            ),
            TestCase(
                input = intArrayOf() to 0,
                output = intArrayOf(-1, -1)
            ),
            TestCase(
                input = intArrayOf(1) to 1,
                output = intArrayOf(0, 0)
            )
        )
    }

    override fun solve(testCaseInput: Pair<IntArray, Int>): IntArray {
        return searchRange(testCaseInput.first, testCaseInput.second)
    }

    private fun searchRange(nums: IntArray, target: Int): IntArray {
        return intArrayOf(
            binarySearch(nums, target, true),
            binarySearch(nums, target, false)
        )
    }

    private fun binarySearch(nums: IntArray, target: Int, isSearchingLeft: Boolean): Int {
        var left = 0
        var right = nums.size - 1
        var index = -1

        while (left <= right) {
            val mid = left + (right - left) / 2
            when {
                target < nums[mid] -> right = mid - 1
                nums[mid] < target -> left = mid + 1
                else -> {
                    index = mid
                    if (isSearchingLeft) right = mid - 1 else left = mid + 1
                }
            }
        }

        return index
    }
}
