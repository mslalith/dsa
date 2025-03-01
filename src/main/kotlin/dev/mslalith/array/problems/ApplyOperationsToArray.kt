package dev.mslalith.array.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.utils.createClone

class ApplyOperationsToArray : TestCaseProblem<IntArray, IntArray>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = ApplyOperationsToArray().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<IntArray, IntArray>> = arrayOf(
        TestCase(
            input = intArrayOf(1, 2, 2, 1, 1, 0),
            output = intArrayOf(1, 4, 2, 0, 0, 0)
        ),
        TestCase(
            input = intArrayOf(0, 1),
            output = intArrayOf(1, 0)
        ),
        TestCase(
            input = intArrayOf(847, 847, 0, 0, 0, 399, 416, 416, 879, 879, 206, 206, 206, 272),
            output = intArrayOf(1694, 399, 832, 1758, 412, 206, 272, 0, 0, 0, 0, 0, 0, 0)
        )
    )

    override fun solve(testCaseInput: IntArray): IntArray {
        return applyOperations(testCaseInput.createClone())
    }

    private fun applyOperations(nums: IntArray): IntArray {
        val n = nums.size
        val result = IntArray(n)

        var idx = 0

        for (i in 0 until (n - 1)) {
            if (nums[i] == 0) continue

            result[idx++] = when {
                nums[i] != nums[i + 1] -> nums[i]
                else -> {
                    nums[i + 1] = 0
                    nums[i] * 2
                }
            }
        }

        if (nums[n - 1] != 0 && idx < n) result[idx] = nums[n - 1]

        return result
    }
}
