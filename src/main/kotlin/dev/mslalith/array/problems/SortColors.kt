package dev.mslalith.array.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.utils.createClone

class SortColors : TestCaseProblem<IntArray, IntArray>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = SortColors().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<IntArray, IntArray>> = arrayOf(
        TestCase(
            input = intArrayOf(2, 0, 2, 1, 1, 0),
            output = intArrayOf(0, 0, 1, 1, 2, 2)
        ),
        TestCase(
            input = intArrayOf(2, 0, 1),
            output = intArrayOf(0, 1, 2)
        )
    )

    override fun solve(testCaseInput: IntArray): IntArray {
        val clone = testCaseInput.createClone()
        sortColors(clone)
        return clone
    }

    private fun sortColors(nums: IntArray) {
        var low = 0
        var high = nums.size - 1
        var i = 0
        while (i <= high) {
            when (nums[i]) {
                0 -> swap(nums, i++, low++)
                2 -> swap(nums, i, high--)
                else -> i++
            }
        }
    }

    private fun swap(nums: IntArray, from: Int, to: Int) {
        val temp = nums[from]
        nums[from] = nums[to]
        nums[to] = temp
    }
}
