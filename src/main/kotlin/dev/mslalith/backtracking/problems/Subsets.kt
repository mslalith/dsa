package dev.mslalith.backtracking.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.utils.unOrderEquals


class Subsets : TestCaseProblem<IntArray, List<List<Int>>>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = Subsets().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<IntArray, List<List<Int>>>> = arrayOf(
        TestCase(
            input = intArrayOf(1, 2, 3),
            output = listOf(
                listOf(),
                listOf(1),
                listOf(2),
                listOf(1, 2),
                listOf(3),
                listOf(1, 3),
                listOf(2, 3),
                listOf(1, 2, 3)
            )
        ),
        TestCase(
            input = intArrayOf(0),
            output = listOf(
                listOf(),
                listOf(0)
            )
        )
    )

    override fun isTestPassed(actual: List<List<Int>>, expected: List<List<Int>>): Boolean {
        return actual.unOrderEquals(expected)
    }

    override fun solve(testCaseInput: IntArray): List<List<Int>> {
        return subsets(testCaseInput)
    }

    private fun subsets(nums: IntArray): List<List<Int>> {
        val n = nums.size
        val result = mutableListOf<List<Int>>()
        val end = 1 shl n

        for (i in 0 until end) {
            val subset = mutableListOf<Int>()
            for (j in 0 until n) {
                if ((i and (1 shl j)) != 0) subset += nums[j]
            }
            result += subset
        }

        return result
    }

    private fun subsetsNaive(nums: IntArray): List<List<Int>> {
        val n = nums.size
        val result = mutableListOf<List<Int>>()

        fun buildSubsets(start: Int, currentPath: MutableList<Int>) {
            result.add(currentPath.toList())

            for (i in start until n) {
                currentPath += nums[i]
                buildSubsets(i + 1, currentPath)
                currentPath -= nums[i]
            }
        }

        buildSubsets(0, mutableListOf())
        return result
    }
}
