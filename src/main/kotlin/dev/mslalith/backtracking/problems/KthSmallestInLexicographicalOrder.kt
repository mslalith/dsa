package dev.mslalith.backtracking.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem

class KthSmallestInLexicographicalOrder : TestCaseProblem<Pair<Int, Int>, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = KthSmallestInLexicographicalOrder().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<Pair<Int, Int>, Int>> = arrayOf(
        TestCase(
            input = 13 to 2,
            output = 10
        ),
        TestCase(
            input = 1 to 1,
            output = 1
        ),
        TestCase(
            input = 2 to 2,
            output = 2
        )
    )

    override fun solve(testCaseInput: Pair<Int, Int>): Int {
        return findKthNumber(testCaseInput.first, testCaseInput.second)
    }

    private fun findKthNumber(n: Int, k: Int): Int {
        var curr = 1L
        var remaining = k - 1L // because we start from 1

        while (remaining > 0) {
            var steps = 0L
            var first = curr
            var last = curr

            while (first <= n) {
                steps += minOf(n + 1L, last + 1L) - first
                first *= 10
                last = last * 10 + 9
            }

            if (steps <= remaining) {
                curr++ // move to the next sibling
                remaining -= steps
            } else {
                curr *= 10 // move to first child
                remaining--
            }
        }

        return curr.toInt()
    }
}
