package dev.mslalith.array.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import kotlin.math.max

class SearchInsertPosition : TestCaseProblem<Pair<IntArray, Int>, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = SearchInsertPosition().runAll()
    }

    override fun getTestCases(): Array<TestCase<Pair<IntArray, Int>, Int>> = arrayOf(
        TestCase(
            input = intArrayOf(1, 3, 5, 6) to 5,
            output = 2
        ),
        TestCase(
            input = intArrayOf(1, 3, 5, 6) to 2,
            output = 1
        ),
        TestCase(
            input = intArrayOf(1, 3, 5, 6) to 7,
            output = 4
        ),
        TestCase(
            input = intArrayOf(1, 3) to 0,
            output = 0
        )
    )
        .let { arrayOf(it.last()) }

    override fun solve(testCaseInput: Pair<IntArray, Int>): Int {
        return searchInsert(testCaseInput.first, testCaseInput.second)
    }

    private fun searchInsert(nums: IntArray, target: Int): Int {
        val n = nums.size

        var left = 0
        var right = n - 1
        var mid = 0

        while (left < right) {
            mid = left + (right - left) / 2
            when {
                nums[mid] == target -> return mid
                nums[mid] > target -> right = max(0, mid - 1)
                else -> left = mid + 1
            }
        }

        return when {
            nums[right] == target -> right
            nums[right] > target -> right
            else -> right + 1
        }
    }
}
