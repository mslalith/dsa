package dev.mslalith.dp.problems

import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.core.TestCase
import kotlin.math.max

class ClimbingStairs : TestCaseProblem<Int, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = ClimbingStairs().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<Int, Int>> = arrayOf(
        TestCase(
            input = 2,
            output = 2
        ),
        TestCase(
            input = 3,
            output = 3
        ),
        TestCase(
            input = 4,
            output = 5
        ),
        TestCase(
            input = 5,
            output = 8
        ),
        TestCase(
            input = 6,
            output = 13
        ),
    )

    override fun solve(testCaseInput: Int): Int {
        return climbStairs(testCaseInput)
    }

    private fun climbStairs(n: Int): Int {
        if (n <= 2) return n

        var lastLast = 1
        var last = 2

        var count = 0

        for (i in 2 until n) {
            val curr = last + lastLast
            count = max(curr, count)
            lastLast = last
            last = curr
        }

        return last
    }
}
