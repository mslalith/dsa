package dev.mslalith.array.problems

import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.core.TestCase

class FindDifferenceOfTwoArrays : TestCaseProblem<Pair<IntArray, IntArray>, List<List<Int>>>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = FindDifferenceOfTwoArrays().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<Pair<IntArray, IntArray>, List<List<Int>>>> = arrayOf(
        TestCase(
            input = intArrayOf(1, 2, 3) to intArrayOf(2, 4, 6),
            output = listOf(listOf(1, 3), listOf(4, 6))
        ),
        TestCase(
            input = intArrayOf(1, 2, 3, 3) to intArrayOf(1, 1, 2, 2),
            output = listOf(listOf(3), listOf())
        )
    )

    override fun solve(testCaseInput: Pair<IntArray, IntArray>): List<List<Int>> {
        return findDifference(testCaseInput.first, testCaseInput.second)
    }

    private fun findDifference(nums1: IntArray, nums2: IntArray): List<List<Int>> {
        val uniqueNums1 = hashSetOf<Int>()
        nums1.forEach { uniqueNums1.add(it) }
        nums2.forEach { uniqueNums1.remove(it) }

        val uniqueNums2 = hashSetOf<Int>()
        nums2.forEach { uniqueNums2.add(it) }
        nums1.forEach { uniqueNums2.remove(it) }

        return listOf(
            uniqueNums1.toList(),
            uniqueNums2.toList()
        )
    }
}
