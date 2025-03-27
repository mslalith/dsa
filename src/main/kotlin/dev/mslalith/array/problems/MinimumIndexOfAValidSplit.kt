package dev.mslalith.array.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem

class MinimumIndexOfAValidSplit : TestCaseProblem<List<Int>, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = MinimumIndexOfAValidSplit().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<List<Int>, Int>> = arrayOf(
        TestCase(
            input = listOf(1, 2, 2, 2),
            output = 2
        ),
        TestCase(
            input = listOf(2, 1, 3, 1, 1, 1, 7, 1, 2, 1),
            output = 4
        ),
        TestCase(
            input = listOf(3, 3, 3, 3, 7, 2, 2),
            output = -1
        ),
        TestCase(
            input = listOf(1, 1),
            output = 0
        )
    )

    override fun solve(testCaseInput: List<Int>): Int {
        return minimumIndex(testCaseInput)
    }

    private fun minimumIndex(nums: List<Int>): Int {
        val n = nums.size
        val numsFreqMap = hashMapOf<Int, Int>()
        for (num in nums) numsFreqMap[num] = numsFreqMap.getOrDefault(num, 0) + 1

        val leftFreqMap = hashMapOf<Int, Int>()

        for (i in 0 until n) {
            val num = nums[i]
            leftFreqMap[num] = leftFreqMap.getOrDefault(num, 0) + 1
            numsFreqMap[num] = numsFreqMap.getOrDefault(num, 0) - 1

            val isLeftValid = leftFreqMap.getValue(num) * 2 > (i + 1)
            val isRightValid = numsFreqMap.getValue(num) * 2 > (n - i - 1)
            if (isLeftValid && isRightValid) return i
        }

        return -1
    }
}
