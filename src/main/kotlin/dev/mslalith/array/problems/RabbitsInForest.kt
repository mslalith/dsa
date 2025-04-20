package dev.mslalith.array.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import kotlin.math.ceil

class RabbitsInForest : TestCaseProblem<IntArray, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = RabbitsInForest().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<IntArray, Int>> = arrayOf(
        TestCase(
            input = intArrayOf(1, 1, 2),
            output = 5
        ),
        TestCase(
            input = intArrayOf(10, 10, 10),
            output = 11
        )
    )

    override fun solve(testCaseInput: IntArray): Int {
        return numRabbits(testCaseInput)
    }

    private fun numRabbits(answers: IntArray): Int {
        val freq = hashMapOf<Int, Int>()
        for (answer in answers) freq[answer] = freq.getOrDefault(answer, 0) + 1

        return freq.keys.sumOf { key ->
            ceil(freq.getValue(key) / (key + 1.0)).toInt() * (key + 1)
        }
    }
}
