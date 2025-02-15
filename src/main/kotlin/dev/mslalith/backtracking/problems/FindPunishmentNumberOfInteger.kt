package dev.mslalith.backtracking.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import kotlin.math.min

class FindPunishmentNumberOfInteger : TestCaseProblem<Int, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = FindPunishmentNumberOfInteger().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<Int, Int>> = arrayOf(
        TestCase(
            input = 10,
            output = 182
        ),
        TestCase(
            input = 37,
            output = 1478
        )
    )

    override fun solve(testCaseInput: Int): Int {
        return punishmentNumber(testCaseInput)
    }

    private fun punishmentNumber(n: Int): Int {

        fun matches(x: Int, target: Int): Boolean {
            if (target < 0) return false
            if (x == target) return true

            val end = min(x, 1000)
            var i = 10
            while (i <= end) {
                val rightDigits = x % i
                val remaining = x / i
                if (matches(remaining, target - rightDigits)) return true
                i *= 10
            }

            return false
        }

        return (1..n).sumOf { if (matches(it * it, it)) it * it else 0 }
    }
}
