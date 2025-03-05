package dev.mslalith.math.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem

class CountTotalNumberOfColoredCells : TestCaseProblem<Int, Long>() {
    
    companion object {
        @JvmStatic
        fun main(args: Array<String>) = CountTotalNumberOfColoredCells().runForConsole()
    }
    
    override fun getTestCases(): Array<TestCase<Int, Long>> = arrayOf(
        TestCase(
            input = 1,
            output = 1
        ),
        TestCase(
            input = 2,
            output = 5
        ),
        TestCase(
            input = 3,
            output = 13
        ),
        TestCase(
            input = 4,
            output = 25
        ),
        TestCase(
            input = 5,
            output = 41
        )
    )
    
    override fun solve(testCaseInput: Int): Long {
        return coloredCells(testCaseInput)
    }

    private fun coloredCells(n: Int): Long {
        if (n == 1) return 1

        var last = 1L
        var i = 1
        while (i <= n) {
            last += (i - 1) * 4
            i++
        }

        return last
    }
}
