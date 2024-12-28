package dev.mslalith.twopointers.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.utils.createClone

class NextPermutation : TestCaseProblem<IntArray, IntArray>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = NextPermutation().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<IntArray, IntArray>> = arrayOf(
        TestCase(
            input = intArrayOf(1, 2, 3),
            output = intArrayOf(1, 3, 2)
        ),
        TestCase(
            input = intArrayOf(3, 2, 1),
            output = intArrayOf(1, 2, 3)
        ),
        TestCase(
            input = intArrayOf(1, 1, 5),
            output = intArrayOf(1, 5, 1)
        ),
        TestCase(
            input = intArrayOf(1, 3, 2),
            output = intArrayOf(2, 1, 3)
        )
    )

    override fun solve(testCaseInput: IntArray): IntArray {
        val clone = testCaseInput.createClone()
        nextPermutation(clone)
        return clone
    }

    private fun nextPermutation(nums: IntArray) {
        val n = nums.size

        fun swap(i: Int, j: Int) {
            val temp = nums[i]
            nums[i] = nums[j]
            nums[j] = temp
        }

        fun reverse(start: Int) {
            var i = start
            var j = n - 1
            while (i < j) {
                swap(i, j)
                i++
                j--
            }
        }

        var breakPoint = -1
        for (i in (n - 2) downTo 0) {
            if (nums[i] < nums[i + 1]) {
                breakPoint = i
                break
            }
        }

        if (breakPoint == -1) {
            // nums is in decreasing order
            reverse(0)
        } else {
            var largest = -1

            for (i in (n - 1) downTo 0) {
                if (nums[i] > nums[breakPoint]) {
                    largest = i
                    break
                }
            }

            swap(breakPoint, largest)

            // now the right half is in decreasing order
            // reverse to make it increasing
            reverse(breakPoint + 1)
        }
    }
}
