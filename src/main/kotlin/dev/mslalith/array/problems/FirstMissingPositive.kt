package dev.mslalith.array.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem

class FirstMissingPositive : TestCaseProblem<IntArray, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = FirstMissingPositive().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<IntArray, Int>> = arrayOf(
        TestCase(
            input = intArrayOf(1, 2, 0),
            output = 3
        ),
        TestCase(
            input = intArrayOf(3, 4, -1, 1),
            output = 2
        ),
        TestCase(
            input = intArrayOf(7, 8, 9, 11, 12),
            output = 1
        )
    )

    override fun solve(testCaseInput: IntArray): Int {
        return firstMissingPositive(testCaseInput)
    }

    private fun firstMissingPositive(nums: IntArray): Int {
        val n = nums.size

        var i = 0
        while (i < n) {
            val num = nums[i]
            val correctIndex = num - 1
            // place num in its correct position
            if (num in 1..n && num != nums[correctIndex]) {
                nums[i] = nums[correctIndex]
                nums[correctIndex] = num
            } else {
                i++
            }
        }

        // find first nums which is not in its correct position
        i = 0
        while (i < n) {
            if (nums[i] != i + 1) return i + 1
            i++
        }

        // all are in correct position
        return n + 1
    }

    private fun firstMissingPositiveBetter(nums: IntArray): Int {
        val seen = hashSetOf<Int>()
        for (num in nums) seen += num

        var i = 1
        while (i in seen) i++

        return i
    }

    private fun firstMissingPositiveBrute(nums: IntArray): Int {
        val sortedNums = nums.sorted()

        var i = 1
        for (num in sortedNums) {
            if (num < i) continue
            if (num > i) return i
            i++
        }

        return i
    }
}
