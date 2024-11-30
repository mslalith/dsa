package dev.mslalith.array.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import kotlin.math.max

class Candy : TestCaseProblem<IntArray, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = Candy().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<IntArray, Int>> = arrayOf(
        TestCase(
            input = intArrayOf(1, 0, 2),
            output = 5
        ),
        TestCase(
            input = intArrayOf(1, 2, 2),
            output = 4
        ),
        TestCase(
            input = intArrayOf(1, 3, 2, 2, 1),
            output = 7
        ),
        TestCase(
            input = intArrayOf(1, 3, 4, 5, 2),
            output = 11
        )
    )

    override fun solve(testCaseInput: IntArray): Int {
        return candy(testCaseInput)
    }

    private fun candy(ratings: IntArray): Int {
        val n = ratings.size
        val candies = IntArray(n) { 1 }

        for (i in 1 until n) {
            if (ratings[i - 1] < ratings[i]) candies[i] = candies[i - 1] + 1
        }

        for (i in (n - 2) downTo 0) {
            if (ratings[i] > ratings[i + 1]) candies[i] = max(candies[i], candies[i + 1] + 1)
        }

        return candies.sum()
    }
}
