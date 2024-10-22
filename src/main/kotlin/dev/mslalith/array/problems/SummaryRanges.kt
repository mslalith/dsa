package dev.mslalith.array.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem

class SummaryRanges : TestCaseProblem<IntArray, List<String>>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = SummaryRanges().runAll()
    }

    override fun getTestCases(): Array<TestCase<IntArray, List<String>>> = arrayOf(
        TestCase(
            input = intArrayOf(0, 1, 2, 4, 5, 7),
            output = listOf("0->2", "4->5", "7")
        ),
        TestCase(
            input = intArrayOf(0, 2, 3, 4, 6, 8, 9),
            output = listOf("0", "2->4", "6", "8->9")
        )
    )

    override fun solve(testCaseInput: IntArray): List<String> {
        return summaryRanges(testCaseInput)
    }

    private fun summaryRanges(nums: IntArray): List<String> {
        val list = mutableListOf<String>()

        var i = 0
        while (i < nums.size) {
            val start = i
            var end = i
            while (end + 1 < nums.size && nums[end] + 1 == nums[end + 1]) {
                i++
                end++
            }
            if (start == end) list.add("${nums[start]}") else list.add("${nums[start]}->${nums[end]}")
            i++
        }

        return list
    }
}
