package dev.mslalith.array.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import kotlin.math.max
import kotlin.math.min

class NumberOfEquivalentDominoPairs : TestCaseProblem<Array<IntArray>, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = NumberOfEquivalentDominoPairs().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<Array<IntArray>, Int>> = arrayOf(
        TestCase(
            input = arrayOf(
                intArrayOf(1, 2),
                intArrayOf(2, 1),
                intArrayOf(3, 4),
                intArrayOf(5, 6)
            ),
            output = 1
        ),
        TestCase(
            input = arrayOf(
                intArrayOf(1, 2),
                intArrayOf(1, 2),
                intArrayOf(1, 1),
                intArrayOf(1, 2),
                intArrayOf(2, 2)
            ),
            output = 3
        ),
        TestCase(
            input = arrayOf(
                intArrayOf(1, 1),
                intArrayOf(2, 2),
                intArrayOf(1, 1),
                intArrayOf(1, 2),
                intArrayOf(1, 2),
                intArrayOf(1, 1)
            ),
            output = 4
        )
    )

    override fun solve(testCaseInput: Array<IntArray>): Int {
        return numEquivDominoPairs(testCaseInput)
    }

    private fun numEquivDominoPairs(dominoes: Array<IntArray>): Int {
        val map = hashMapOf<Int, Int>()

        var count = 0

        for (i in 0 until dominoes.size) {
            val domino = dominoes[i]
            val a = min(domino[0], domino[1])
            val b = max(domino[0], domino[1])
            val num = (a * 10) + b

            count += map.getOrDefault(num, 0)
            map[num] = map.getOrDefault(num, 0) + 1
        }

        return count
    }
}
