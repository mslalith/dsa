package src.array.problems

import src.core.Problem
import src.core.TestCase

class RemoveDuplicatesFromSortedArray : Problem<IntArray, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = RemoveDuplicatesFromSortedArray().run()
    }

    override fun getTestCases(): Array<TestCase<IntArray, Int>> = arrayOf(
        TestCase(
            input = intArrayOf(1, 1, 2),
            output = 2
        ),
        TestCase(
            input = intArrayOf(0, 0, 1, 1, 1, 2, 2, 3, 3, 4),
            output = 5
        )
    )

    override fun solve(testCaseInput: IntArray): Int {
        return removeDuplicates(testCaseInput)
    }

    private fun removeDuplicates(nums: IntArray): Int {
        var insert = 0
        var i = 1

        while (i < nums.size) {
            if (nums[i] != nums[insert]) nums[++insert] = nums[i]
            i++
        }

        return insert + 1
    }
}
