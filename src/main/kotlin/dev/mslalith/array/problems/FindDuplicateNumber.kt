package dev.mslalith.array.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem

class FindDuplicateNumber : TestCaseProblem<IntArray, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = FindDuplicateNumber().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<IntArray, Int>> = arrayOf(
        TestCase(
            input = intArrayOf(1, 3, 4, 2, 2),
            output = 2
        ),
        TestCase(
            input = intArrayOf(3, 1, 3, 4, 2),
            output = 3
        ),
        TestCase(
            input = intArrayOf(3, 3, 3, 3, 3),
            output = 3
        )
    )

    override fun solve(testCaseInput: IntArray): Int {
        return findDuplicate(testCaseInput)
    }

    private fun findDuplicate(nums: IntArray): Int {
        var slow = nums[0]
        var fast = nums[0]

        do {
            slow = nums[slow]
            fast = nums[nums[fast]]
        } while (slow != fast)

        slow = nums[0]
        while (slow != fast) {
            slow = nums[slow]
            fast = nums[fast]
        }

        return slow
    }

    private fun findDuplicateNaive(nums: IntArray): Int {
        val seen = hashSetOf<Int>()

        for (num in nums) {
            if (num in seen) return num
            seen += num
        }

        return -1
    }
}
