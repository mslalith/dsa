package src.array.problems

import src.core.Problem
import src.core.TestCase
import kotlin.math.max

class LongestConsecutiveSequence : Problem<IntArray, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = LongestConsecutiveSequence().run()
    }

    override fun getTestCases(): Array<TestCase<IntArray, Int>> = arrayOf(
        TestCase(
            input = intArrayOf(100, 4, 200, 1, 3, 2),
            output = 4
        ),
        TestCase(
            input = intArrayOf(0, 3, 7, 2, 5, 8, 4, 6, 0, 1),
            output = 9
        ),
        TestCase(
            input = intArrayOf(1, 2, 0, 1, 2),
            output = 3
        ),
        TestCase(
            input = intArrayOf(0, -1),
            output = 2
        )
    )

    override fun solve(testCaseInput: IntArray): Int {
        return longestConsecutive(testCaseInput)
    }

    private fun longestConsecutive(nums: IntArray): Int {
        val n = nums.size
        if (n == 0 || n == 1) return n

        nums.sort()

        var currCount = 1
        var longestCount = 0

        for (i in 1 until n) {
            if (nums[i] != nums[i - 1]) {
                if (nums[i] == nums[i - 1] + 1) {
                    currCount++
                } else {
                    longestCount = max(currCount, longestCount)
                    currCount = 1
                }
            }
        }

        return max(currCount, longestCount)
    }
}