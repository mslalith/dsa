package dev.mslalith.array.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.utils.createClone

class MinimumOperationsToMakeBinaryArrayElementsEqualToOneI : TestCaseProblem<IntArray, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = MinimumOperationsToMakeBinaryArrayElementsEqualToOneI().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<IntArray, Int>> = arrayOf(
        TestCase(
            input = intArrayOf(0, 1, 1, 1, 0, 0),
            output = 3
        ),
        TestCase(
            input = intArrayOf(0, 1, 1, 1),
            output = -1
        )
    )

    override fun solve(testCaseInput: IntArray): Int {
        return minOperations(testCaseInput.createClone())
    }

    private fun minOperations(nums: IntArray): Int {
        val n = nums.size
        var count = 0

        for (i in 0 until n) {
            if (nums[i] == 0) {
                if (i + 2 >= n) return -1
                nums[i] = if (nums[i] == 0) 1 else 0
                nums[i + 1] = if (nums[i + 1] == 0) 1 else 0
                nums[i + 2] = if (nums[i + 2] == 0) 1 else 0
                count++
            }
        }

        return count
    }
}
