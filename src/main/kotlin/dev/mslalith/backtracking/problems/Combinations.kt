package dev.mslalith.backtracking.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem

class Combinations : TestCaseProblem<Pair<Int, Int>, List<List<Int>>>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = Combinations().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<Pair<Int, Int>, List<List<Int>>>> = arrayOf(
        TestCase(
            input = 4 to 2,
            output = listOf(
                listOf(1, 2),
                listOf(1, 3),
                listOf(1, 4),
                listOf(2, 3),
                listOf(2, 4),
                listOf(3, 4)
            )
        ),
        TestCase(
            input = 1 to 1,
            output = listOf(
                listOf(1)
            )
        ),
        TestCase(
            input = 3 to 3,
            output = listOf(
                listOf(1, 2, 3)
            )
        ),
        TestCase(
            input = 5 to 3,
            output = listOf(
                listOf(1, 2, 3),
                listOf(1, 2, 4),
                listOf(1, 2, 5),
                listOf(1, 3, 4),
                listOf(1, 3, 5),
                listOf(1, 4, 5),
                listOf(2, 3, 4),
                listOf(2, 3, 5),
                listOf(2, 4, 5),
                listOf(3, 4, 5)
            )
        )
    )
        .let { arrayOf(it.last()) }

    override fun solve(testCaseInput: Pair<Int, Int>): List<List<Int>> {
        return combine(testCaseInput.first, testCaseInput.second)
    }

    private fun combine(n: Int, k: Int): List<List<Int>> {
        if (n == k) return listOf(List(n) { it + 1 })

        val result = mutableListOf<List<Int>>()
        val currentList = mutableListOf<Int>()

        fun buildCombinations(start: Int) {
            if (start > n) return

            currentList.add(start)

            if (currentList.size == k) {
                result.add(currentList.toList())
                currentList.removeLast()
                return
            }

            for (i in (start + 1)..n) {
                buildCombinations(i)
            }
            if (currentList.isNotEmpty()) currentList.removeLast()
        }

        for (i in 1..(n - k + 1)) {
            currentList.clear()
            buildCombinations(i)
        }

        return result
    }
}
