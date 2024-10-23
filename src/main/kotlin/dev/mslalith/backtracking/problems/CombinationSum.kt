package dev.mslalith.backtracking.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.utils.createClone
import dev.mslalith.utils.unOrderEquals

class CombinationSum : TestCaseProblem<Pair<IntArray, Int>, List<List<Int>>>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = CombinationSum().runAll()
    }

    override fun getTestCases(): Array<TestCase<Pair<IntArray, Int>, List<List<Int>>>> = arrayOf(
        TestCase(
            input = intArrayOf(2, 3, 6, 7) to 7,
            output = listOf(
                listOf(2, 2, 3),
                listOf(7)
            )
        ),
        TestCase(
            input = intArrayOf(2, 3, 5) to 8,
            output = listOf(
                listOf(2, 2, 2, 2),
                listOf(2, 3, 3),
                listOf(3, 5)
            )
        ),
        TestCase(
            input = intArrayOf(2) to 1,
            output = emptyList()
        )
    )
        .let { arrayOf(it[1]) }

    override fun isTestPassed(actual: List<List<Int>>, expected: List<List<Int>>): Boolean {
        return actual.unOrderEquals(expected)
    }

    override fun solve(testCaseInput: Pair<IntArray, Int>): List<List<Int>> {
        return combinationSum(testCaseInput.first.createClone(), testCaseInput.second)
    }

    private fun combinationSum(candidates: IntArray, target: Int): List<List<Int>> {
        candidates.sort()
        val n = candidates.size

        val result = mutableListOf<List<Int>>()
        val currentList = mutableListOf<Int>()


        fun buildCombinationSum(index: Int, target: Int) {
            if (target < 0) return

            if (target == 0) {
                result.add(currentList.toList())
                return
            }

            for (i in index until n) {
                currentList.add(candidates[i])
                buildCombinationSum(i, target - candidates[i])
                currentList.removeLast()
            }
        }

        buildCombinationSum(0, target)
        return result
    }
}
