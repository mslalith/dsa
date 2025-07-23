package dev.mslalith.string.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.string.problems.MaximumScoreFromRemovingSubstrings.MaximumScoreFromRemovingSubstringsParams

class MaximumScoreFromRemovingSubstrings : TestCaseProblem<MaximumScoreFromRemovingSubstringsParams, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = MaximumScoreFromRemovingSubstrings().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<MaximumScoreFromRemovingSubstringsParams, Int>> = arrayOf(
        TestCase(
            input = MaximumScoreFromRemovingSubstringsParams(
                s = "cdbcbbaaabab",
                x = 4,
                y = 5
            ),
            output = 19
        ),
        TestCase(
            input = MaximumScoreFromRemovingSubstringsParams(
                s = "aabbaaxybbaabb",
                x = 5,
                y = 4
            ),
            output = 20
        )
    )

    override fun solve(testCaseInput: MaximumScoreFromRemovingSubstringsParams): Int {
        return maximumGain(testCaseInput.s, testCaseInput.x, testCaseInput.y)
    }

    private fun maximumGain(s: String, x: Int, y: Int): Int {
        val (maxVal, maxChar) = if (x > y) x to 'a' else y to 'b'
        val (minVal, minChar) = if (x > y) y to 'b' else x to 'a'

        var minCharCount = 0
        var maxCharCount = 0
        var points = 0

        for (i in 0..<s.length) {
            when {
                s[i] == maxChar -> maxCharCount++
                s[i] == minChar -> when {
                    maxCharCount > 0 -> {
                        points += maxVal
                        maxCharCount--
                    }
                    else -> minCharCount++
                }
                else -> {
                    points += minOf(maxCharCount, minCharCount) * minVal
                    maxCharCount = 0
                    minCharCount = 0
                }
            }
        }

        points += minOf(maxCharCount, minCharCount) * minVal

        return points
    }

    data class MaximumScoreFromRemovingSubstringsParams(
        val s: String,
        val x: Int,
        val y: Int
    ) {
        override fun toString(): String {
            return """
                
                s: $s
                x: $x
                y: $y
            """.trimIndent()
        }
    }
}
