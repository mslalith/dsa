package dev.mslalith.dp.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import kotlin.math.max

class SolvingQuestionsWithBrainpower : TestCaseProblem<Array<IntArray>, Long>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = SolvingQuestionsWithBrainpower().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<Array<IntArray>, Long>> = arrayOf(
        TestCase(
            input = arrayOf(
                intArrayOf(3, 2),
                intArrayOf(4, 3),
                intArrayOf(4, 4),
                intArrayOf(2, 5)
            ),
            output = 5
        ),
        TestCase(
            input = arrayOf(
                intArrayOf(1, 1),
                intArrayOf(2, 2),
                intArrayOf(3, 3),
                intArrayOf(4, 4),
                intArrayOf(5, 5)
            ),
            output = 7
        ),
        TestCase(
            input = arrayOf(
                intArrayOf(12, 46),
                intArrayOf(78, 19),
                intArrayOf(63, 15),
                intArrayOf(79, 62),
                intArrayOf(13, 10)
            ),
            output = 79
        )
    )

    override fun solve(testCaseInput: Array<IntArray>): Long {
        return mostPoints(testCaseInput)
    }

    private fun mostPoints(questions: Array<IntArray>): Long {
        val n = questions.size
        val dp = LongArray(n)

        for (i in (n - 1) downTo 0) {
            val (points, brainPower) = questions[i]
            val notTake = if (i + 1 < n) dp[i + 1] else 0
            val take = points + if (i + brainPower + 1 < n) dp[i + brainPower + 1] else 0

            dp[i] = max(take, notTake)
        }

        return dp[0]
    }

    private fun mostPointsRecursive(questions: Array<IntArray>): Long {
        val n = questions.size
        val dp = LongArray(n) { -1 }

        fun findMostPoints(i: Int): Long {
            if (i >= n) return 0
            if (dp[i] != -1L) return dp[i]

            val (points, brainPower) = questions[i]
            val notTake = findMostPoints(i + 1)
            val take = points + findMostPoints(i + brainPower + 1)

            dp[i] = max(take, notTake)
            return dp[i]
        }

        return findMostPoints(0)
    }
}
