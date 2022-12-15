package src.array.problems

import src.core.Problem
import src.core.TestCase
import src.utils.buildIntArray
import java.util.Arrays

class FindFirstAndLastPositionOfElementInSortedArray :
    Problem<FindFirstAndLastPositionOfElementInSortedArrayParams, IntArray>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = FindFirstAndLastPositionOfElementInSortedArray().run()
    }

    override fun getTestCases(): Array<TestCase<FindFirstAndLastPositionOfElementInSortedArrayParams, IntArray>> {
        return arrayOf(
            TestCase(
                input = FindFirstAndLastPositionOfElementInSortedArrayParams(
                    nums = "5,7,7,8,8,10",
                    target = 8
                ),
                output = intArrayOf(3, 4)
            ),
            TestCase(
                input = FindFirstAndLastPositionOfElementInSortedArrayParams(
                    nums = "5,7,7,8,8,10",
                    target = 6
                ),
                output = intArrayOf(-1, -1)
            ),
            TestCase(
                input = FindFirstAndLastPositionOfElementInSortedArrayParams(
                    nums = "",
                    target = 0
                ),
                output = intArrayOf(-1, -1)
            ),
            TestCase(
                input = FindFirstAndLastPositionOfElementInSortedArrayParams(
                    nums = "1",
                    target = 1
                ),
                output = intArrayOf(0, 0)
            )
        )
    }

    override fun solve(testCaseInput: FindFirstAndLastPositionOfElementInSortedArrayParams): IntArray {
        return searchRange(
            nums = buildIntArray(input = testCaseInput.nums, separator = ","),
            target = testCaseInput.target
        )
    }

    private fun searchRange(nums: IntArray, target: Int): IntArray {
        if (nums.isEmpty()) return intArrayOf(-1, -1)

        val index = Arrays.binarySearch(nums, target)
        if (index < 0) return intArrayOf(-1, -1)

        var left = index
        var right = index

        while (left > 0 && nums[left - 1] == target) left--

        while (right < nums.lastIndex && nums[right + 1] == target) right++

        return intArrayOf(left, right)
    }
}

data class FindFirstAndLastPositionOfElementInSortedArrayParams(
    val nums: String,
    val target: Int
) {
    override fun toString(): String {
        return """
            
            nums: $nums
            target: $target
        """.trimIndent()
    }
}