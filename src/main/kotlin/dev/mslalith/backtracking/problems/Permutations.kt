package dev.mslalith.backtracking.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem

class Permutations : TestCaseProblem<IntArray, List<List<Int>>>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = Permutations().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<IntArray, List<List<Int>>>> = arrayOf(
        TestCase(
            input = intArrayOf(1, 2, 3),
            output = listOf(
                listOf(1, 2, 3),
                listOf(1, 3, 2),
                listOf(2, 1, 3),
                listOf(2, 3, 1),
                listOf(3, 1, 2),
                listOf(3, 2, 1)
            )
        ),
        TestCase(
            input = intArrayOf(0, 1),
            output = listOf(
                listOf(0, 1),
                listOf(1, 0)
            )
        ),
        TestCase(
            input = intArrayOf(1),
            output = listOf(
                listOf(1)
            )
        )
    )

    override fun solve(testCaseInput: IntArray): List<List<Int>> {
        return permute(testCaseInput)
    }

    private fun permute(nums: IntArray): List<List<Int>> {
        val n = nums.size
        if (n == 1) return listOf(listOf(nums[0]))

        val result = mutableListOf<List<Int>>()
        val currentList = mutableListOf<Int>()

        fun buildPermutations() {
            if (currentList.size == n) {
                result.add(currentList.toList())
                return
            }

            for (i in nums.indices) {
                if (currentList.contains(nums[i])) continue
                currentList.add(nums[i])
                buildPermutations()
                currentList.removeLast()
            }
        }

        for (i in nums.indices) {
            currentList.clear()
            currentList.add(nums[i])
            buildPermutations()
        }

        return result
    }
}
