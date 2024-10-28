package dev.mslalith.array.problems

import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.core.TestCase

class RemoveDuplicatesFromSortedArrayII : TestCaseProblem<IntArray, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = RemoveDuplicatesFromSortedArrayII().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<IntArray, Int>> = arrayOf(
        TestCase(
            input = intArrayOf(1, 1, 1, 2, 2, 3),
            output = 5
        ),
        TestCase(
            input = intArrayOf(0, 0, 1, 1, 1, 1, 2, 3, 3),
            output = 7
        )
    )

    override fun solve(testCaseInput: IntArray): Int {
        return removeDuplicates(testCaseInput)
    }

    private fun removeDuplicates(nums: IntArray): Int {
        var insert = 0
        var i = 1
        var seenCount = 0

        while (i < nums.size) {
            if (nums[i] != nums[insert]) {
                nums[++insert] = nums[i]
                seenCount = 0
            } else if (nums[i] == nums[insert] && seenCount < 1) {
                nums[++insert] = nums[i]
                seenCount++
            }
            i++
        }

        return insert + 1
    }
}
