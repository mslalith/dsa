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
        var lastLast = 1
        var last = 1

        for (i in 2..n) {
            val curr = last + lastLast
            lastLast = last
            last = curr
        }

        return last
    }

    private fun climbStairsDp(n: Int): Int {
        val dp = IntArray(n + 1)
        dp[0] = 1
        dp[1] = 1

        for (i in 2..n) {
            dp[i] = dp[i - 1] + dp[i - 2]
        }

        return dp[n]
    }

    private fun climbStairsRecursive(n: Int): Int {
        val dp = IntArray(n + 1) { -1 }

        fun findClimbStairs(i: Int): Int {
            if (i == 0) return 1
            if (i == 1) return 1
            if (dp[i] != -1) return dp[i]

            dp[i] = climbStairsRecursive(i - 1) + climbStairsRecursive(i - 2)
            return dp[i]
        }

        return findClimbStairs(n)
    }
}
