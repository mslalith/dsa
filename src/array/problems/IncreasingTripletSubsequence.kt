package src.array.problems

import src.core.Problem
import src.core.TestCase

class IncreasingTripletSubsequence : Problem<IntArray, Boolean>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = IncreasingTripletSubsequence().run()
    }

    override fun getTestCases(): Array<TestCase<IntArray, Boolean>> = arrayOf(
        TestCase(
            input = intArrayOf(1, 2, 3, 4, 5),
            output = true
        ),
        TestCase(
            input = intArrayOf(5, 4, 3, 2, 1),
            output = false
        ),
        TestCase(
            input = intArrayOf(2, 1, 5, 0, 4, 6),
            output = true
        ),
        TestCase(
            input = intArrayOf(1, 5, 0, 4, 1, 3),
            output = true
        ),
        TestCase(
            input = intArrayOf(1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1),
            output = false
        )
    )

    override fun solve(testCaseInput: IntArray): Boolean {
        return increasingTriplet(testCaseInput)
    }

    private fun increasingTriplet(nums: IntArray): Boolean {
        val n = nums.size
        if (n < 3) return false

        var a = Int.MAX_VALUE
        var b = Int.MAX_VALUE

        for (num in nums) {
            when {
                num <= a -> a = num
                num <= b -> b = num
                else -> return true
            }
        }

        return false
    }

    private fun increasingTripletNaive(nums: IntArray): Boolean {
        val n = nums.size

        fun findSmallestValueIndexAfter(index: Int): Int? {
            var smallestIndex: Int? = null
            for (i in (index + 1) until n) {
                if (nums[index] < nums[i]) {
                    if (smallestIndex == null || nums[i] < nums[smallestIndex]) smallestIndex = i
                }
            }
            return smallestIndex
        }

        for (i in 0 until n) {
            val smallestIndex = findSmallestValueIndexAfter(i) ?: continue
            if (findSmallestValueIndexAfter(smallestIndex) != null) return true
        }

        return false
    }
}
