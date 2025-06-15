package dev.mslalith.math.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem

class MaxDifferenceYouCanGetFromChangingInteger : TestCaseProblem<Int, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = MaxDifferenceYouCanGetFromChangingInteger().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<Int, Int>> = arrayOf(
        TestCase(
            input = 555,
            output = 888
        ),
        TestCase(
            input = 9,
            output = 8
        )
    )

    override fun solve(testCaseInput: Int): Int {
        return maxDiff(testCaseInput)
    }

    private fun maxDiff(num: Int): Int {
        val numStr = num.toString()

        var replaceDigit = numStr.firstOrNull { it != '9' } ?: ' '
        val maxiStr = buildString {
            for (ch in numStr) append(if (ch == replaceDigit) '9' else ch)
        }

        replaceDigit = numStr[0]
        var replace = '1'
        val miniStr = StringBuilder(numStr)

        if (replaceDigit == '1') {
            for (i in 1..<numStr.length) {
                if (numStr[i] != '0' && numStr[i] != '1') {
                    replaceDigit = numStr[i]
                    replace = '0'
                    break
                }
            }
        }

        for (i in numStr.indices) {
            if (numStr[i] == replaceDigit) miniStr[i] = replace
        }

        return maxiStr.toInt() - miniStr.toString().toInt()
    }
}
