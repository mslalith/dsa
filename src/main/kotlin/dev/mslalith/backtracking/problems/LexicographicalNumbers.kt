package dev.mslalith.backtracking.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem

class LexicographicalNumbers : TestCaseProblem<Int, List<Int>>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = LexicographicalNumbers().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<Int, List<Int>>> = arrayOf(
        TestCase(
            input = 13,
            output = listOf(1, 10, 11, 12, 13, 2, 3, 4, 5, 6, 7, 8, 9)
        ),
        TestCase(
            input = 2,
            output = listOf(1, 2)
        )
    )

    override fun solve(testCaseInput: Int): List<Int> {
        return lexicalOrder(testCaseInput)
    }

    private fun lexicalOrder(n: Int): List<Int> {
        val nums = mutableListOf<Int>()
        var curr = 1

        repeat(n) {
            nums += curr

            if (curr * 10 <= n) curr *= 10 else {
                while (curr % 10 == 9 || curr >= n) curr /= 10
                curr++
            }
        }

        return nums
    }
}
