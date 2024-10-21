package dev.mslalith.array.problems

import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.core.TestCase


class CombinationSumIII : TestCaseProblem<Pair<Int, Int>, List<List<Int>>>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = CombinationSumIII().runAll()
    }

    override fun getTestCases(): Array<TestCase<Pair<Int, Int>, List<List<Int>>>> = arrayOf(
        TestCase(
            input = 3 to 7,
            output = listOf(
                listOf(1, 2, 4)
            )
        ),
        TestCase(
            input = 3 to 9,
            output = listOf(
                listOf(1, 2, 6),
                listOf(1, 3, 5),
                listOf(2, 3, 4)
            )
        ),
        TestCase(
            input = 4 to 1,
            output = emptyList()
        ),
        TestCase(
            input = 9 to 45,
            output = listOf(
                listOf(1, 2, 3, 4, 5, 6, 7, 8, 9)
            )
        )
    )

    override fun solve(testCaseInput: Pair<Int, Int>): List<List<Int>> {
        return combinationSum3(testCaseInput.first, testCaseInput.second)
    }

    private fun combinationSum3(k: Int, n: Int): List<List<Int>> {
        val result = arrayListOf<List<Int>>()
        combinationSum3Recursive(1, k, n, result, arrayListOf())
        return result
    }

    private fun combinationSum3Recursive(start: Int, size: Int, target: Int, result: ArrayList<List<Int>>, current: ArrayList<Int>) {
        if (current.size == size) {
            if (target == 0) result.add(ArrayList(current))
            return
        }

        for (i in start..9) {
            if (i > target) break
            current.add(i)
            combinationSum3Recursive(i + 1, size, target - i, result, current)
            current.removeLast()
        }
    }

    private fun combinationSum3Naive(k: Int, n: Int): List<List<Int>> {
        val answer = mutableListOf<List<Int>>()
        val array = IntArray(k) { 1 }
        while (array.sum() < 9 * k) {
            if (isValid(array, n) && array.sum() == n && !alreadyExists(array, answer)) answer.add(array.toList())

            var i = array.lastIndex
            while (i >= 0) {
                if (array[i] < 9) {
                    array[i]++
                    break
                } else {
                    array[i] = 1
                    i--
                }
            }
        }
        if (array.sum() == n) answer.add(array.toList())
        return answer
    }

    private fun isValid(array: IntArray, sum: Int): Boolean {
        if (array.toSet().size != array.size) return false
        return array.all { it < sum }
    }

    private fun alreadyExists(array: IntArray, answer: List<List<Int>>): Boolean {
        return answer.map { it.sorted() }.contains(array.sorted())
    }
}
