package dev.mslalith.string.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import kotlin.math.max

class MaximumScoreAfterSplittingString : TestCaseProblem<String, Int>() {
    
    companion object {
        @JvmStatic
        fun main(args: Array<String>) = MaximumScoreAfterSplittingString().runForConsole()
    }
    
    override fun getTestCases(): Array<TestCase<String, Int>> = arrayOf(
        TestCase(
            input = "011101",
            output = 5
        ),
        TestCase(
            input = "00111",
            output = 5
        ),
        TestCase(
            input = "1111",
            output = 3
        ),
        TestCase(
            input = "00",
            output = 1
        )
    )
    
    override fun solve(testCaseInput: String): Int {
        return maxScore(testCaseInput)
    }

    private fun maxScore(s: String): Int {
        var totalZeros = 0
        var totalOnes = 0
        for (ch in s) if (ch == '0') totalZeros++ else totalOnes++

        var zeroCount = 0
        var oneCount = 0
        var maxCount = 0

        for (i in 0 until (s.length - 1)) {
            val ch = s[i]
            if (ch == '0') zeroCount++ else oneCount++
            val sum = zeroCount + (totalOnes - oneCount)
            maxCount = max(maxCount, sum)
        }

        return maxCount
    }
}
