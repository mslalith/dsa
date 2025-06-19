package dev.mslalith.array.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.utils.createClone

class PartitionArraySuchThatMaximumDifferenceIsK : TestCaseProblem<Pair<IntArray, Int>, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = PartitionArraySuchThatMaximumDifferenceIsK().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<Pair<IntArray, Int>, Int>> = arrayOf(
        TestCase(
            input = intArrayOf(3, 6, 1, 2, 5) to 2,
            output = 2
        ),
        TestCase(
            input = intArrayOf(1, 2, 3) to 1,
            output = 2
        ),
        TestCase(
            input = intArrayOf(2, 2, 4, 5) to 0,
            output = 3
        )
    )

    override fun solve(testCaseInput: Pair<IntArray, Int>): Int {
        return partitionArray(testCaseInput.first.createClone(), testCaseInput.second)
    }

    private fun partitionArray(nums: IntArray, k: Int): Int {
        nums.sort()

        var mini = nums[0]
        var count = 1

        for (i in 1..<nums.size) {
            if (nums[i] - mini > k) {
                count++
                mini = nums[i]
            }
        }

        return count
    }
}
