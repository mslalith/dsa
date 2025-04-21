package dev.mslalith.array.problems

import dev.mslalith.array.problems.CountTheHiddenSequences.CountTheHiddenSequencesParams
import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.utils.stringFromArray

class CountTheHiddenSequences : TestCaseProblem<CountTheHiddenSequencesParams, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = CountTheHiddenSequences().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<CountTheHiddenSequencesParams, Int>> = arrayOf(
        TestCase(
            input = CountTheHiddenSequencesParams(
                differences = intArrayOf(1, -3, 4),
                lower = 1,
                upper = 6
            ),
            output = 2
        ),
        TestCase(
            input = CountTheHiddenSequencesParams(
                differences = intArrayOf(3, -4, 5, 1, -2),
                lower = -4,
                upper = 5
            ),
            output = 4
        ),
        TestCase(
            input = CountTheHiddenSequencesParams(
                differences = intArrayOf(4, -7, 2),
                lower = 3,
                upper = 6
            ),
            output = 0
        )
    )

    override fun solve(testCaseInput: CountTheHiddenSequencesParams): Int {
        return numberOfArrays(testCaseInput.differences, testCaseInput.lower, testCaseInput.upper)
    }

    private fun numberOfArrays(differences: IntArray, lower: Int, upper: Int): Int {
        var sum = 0L
        var maxi = 0L
        var mini = 0L

        for (diff in differences) {
            sum += diff
            maxi = maxOf(maxi, sum)
            mini = minOf(mini, sum)
        }

        val ans = (upper - lower) - (maxi - mini) + 1
        return maxOf(0, ans.toInt())
    }

    private fun numberOfArraysNaive(differences: IntArray, lower: Int, upper: Int): Int {
        return (lower..upper).count {
            var sum = it
            for (diff in differences) {
                sum += diff
                if (sum !in lower..upper) return@count false
            }
            true
        }
    }

    data class CountTheHiddenSequencesParams(
        val differences: IntArray,
        val lower: Int,
        val upper: Int
    ) {
        override fun toString(): String {
            return """
                
                differences: ${stringFromArray(differences)}
                lower: $lower
                upper: $upper
            """.trimIndent()
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as CountTheHiddenSequencesParams

            if (lower != other.lower) return false
            if (upper != other.upper) return false
            if (!differences.contentEquals(other.differences)) return false

            return true
        }

        override fun hashCode(): Int {
            var result = lower
            result = 31 * result + upper
            result = 31 * result + differences.contentHashCode()
            return result
        }
    }
}
