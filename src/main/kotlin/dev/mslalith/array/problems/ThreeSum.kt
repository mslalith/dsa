package dev.mslalith.array.problems

import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.core.TestCase
import dev.mslalith.utils.unOrderEquals

class ThreeSum : TestCaseProblem<IntArray, List<List<Int>>>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = ThreeSum().runAll()
    }

    override fun getTestCases(): Array<TestCase<IntArray, List<List<Int>>>> = arrayOf(
        TestCase(
            input = intArrayOf(-1, 0, 1, 2, -1, -4),
            output = listOf(
                listOf(-1, -1, 2),
                listOf(-1, 0, 1)
            )
        ),
        TestCase(
            input = intArrayOf(0, 1, 1),
            output = listOf()
        ),
        TestCase(
            input = intArrayOf(0, 0, 0),
            output = listOf(
                listOf(0, 0, 0)
            )
        )
    )

    override fun isTestPassed(actual: List<List<Int>>, expected: List<List<Int>>): Boolean {
        return actual.unOrderEquals(expected)
    }

    override fun solve(testCaseInput: IntArray): List<List<Int>> {
        return threeSum(testCaseInput)
    }

    private fun threeSum(nums: IntArray): List<List<Int>> {
        nums.sort()
        val result = mutableListOf<List<Int>>()

        fun findZeroSum(start: Int) {
            if (start != 0 && nums[start] == nums[start - 1]) return

            var left = start + 1
            var right = nums.lastIndex

            while (left < right) {
                val sum = nums[start] + nums[left] + nums[right]
                if (sum == 0) {
                    result.add(listOf(nums[start], nums[left], nums[right]))
                    left++
                    right--
                    while (left < right && nums[left] == nums[left - 1]) left++
                } else if (sum < 0) {
                    left++
                } else right--
            }
        }

        for (i in nums.indices) {
            if (nums[i] <= 0) {
                findZeroSum(i)
            }
        }

        return result
    }

    private fun threeSumNaive(nums: IntArray): List<List<Int>> {
        val n = nums.size
        val result = mutableListOf<List<Int>>()

        for (i in 0 until n) {
            val a = nums[i]
            for (j in (i + 1) until n) {
                if (i == j) continue
                val b = nums[j]
                for (k in (j + 1) until n) {
                    if (k == i || k == j) continue
                    val c = nums[k]
                    if (a + b + c == 0) {
                        val triple = listOf(a, b, c)
                        val match = result.firstOrNull { it.unOrderEquals(triple) }
                        if (match == null) result.add(triple)
                    }
                }
            }
        }

        return result
    }
}
