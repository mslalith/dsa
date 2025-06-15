package dev.mslalith.math.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem

class MaximumDifferenceByRemappingADigit : TestCaseProblem<Int, Int>() {
    
    companion object {
        @JvmStatic
        fun main(args: Array<String>) = MaximumDifferenceByRemappingADigit().runForConsole()
    }
    
    override fun getTestCases(): Array<TestCase<Int, Int>> = arrayOf(
        TestCase(
            input = 11891,
            output = 99009
        ),
        TestCase(
            input = 90,
            output = 99
        ),
        TestCase(
            input = 99999,
            output = 99999
        )
    )
    
    override fun solve(testCaseInput: Int): Int {
        return minMaxDifference(testCaseInput)
    }

    private fun minMaxDifference(num: Int): Int {
        val numStr = num.toString()

        var replaceDigit = numStr.firstOrNull { it != '9' } ?: ' '
        val maxiStr = buildString {
            for (ch in numStr) append(if (ch == replaceDigit) '9' else ch)
        }

        replaceDigit = numStr.firstOrNull { it != '0' } ?: ' '
        val miniStr = buildString {
            for (ch in numStr) append(if (ch == replaceDigit) '0' else ch)
        }

        return maxiStr.toInt() - miniStr.toInt()
    }
}
