package dev.mslalith.array.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem


class CountNumberOfBadPairs : TestCaseProblem<IntArray, Long>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = CountNumberOfBadPairs().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<IntArray, Long>> = arrayOf(
        TestCase(
            input = intArrayOf(4, 1, 3, 3),
            output = 5
        ),
        TestCase(
            input = intArrayOf(1, 2, 3, 4, 5),
            output = 0
        )
    )

    override fun solve(testCaseInput: IntArray): Long {
        return countBadPairs(testCaseInput)
    }

    private fun countBadPairs(nums: IntArray): Long {
        val n = nums.size
        if (n < 2) return 0

        val indexToCountMap = hashMapOf<Int, Int>()
        var goodPairs = 0L

        for (i in 0 until n) {
            val key = nums[i] - i
            goodPairs += indexToCountMap.getOrDefault(key, 0)
            indexToCountMap[key] = indexToCountMap.getOrDefault(key, 0) + 1
        }

        val totalPairs = n.toLong() * (n - 1) / 2
        return totalPairs - goodPairs
    }

    private fun countBadPairsNaive(nums: IntArray): Long {
        val n = nums.size
        if (n < 2) return 0

        var badPairs = 0L

        for (j in (n - 1) downTo 1) {
            for (i in (j - 1) downTo 0) {
                if (j - i != nums[j] - nums[i]) badPairs++
            }
        }

        return badPairs
    }
}
