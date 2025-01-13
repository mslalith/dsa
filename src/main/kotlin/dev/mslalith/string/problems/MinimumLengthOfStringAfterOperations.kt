package dev.mslalith.string.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem

class MinimumLengthOfStringAfterOperations : TestCaseProblem<String, Int>() {
    
    companion object {
        @JvmStatic
        fun main(args: Array<String>) = MinimumLengthOfStringAfterOperations().runForConsole()
    }
    
    override fun getTestCases(): Array<TestCase<String, Int>> = arrayOf(
        TestCase(
            input = "abaacbcbb",
            output = 5
        ),
        TestCase(
            input = "aa",
            output = 2
        ),
        TestCase(
            input = "ucvbutgkohgbcobqeyqwppbxqoynxeuuzouyvmydfhrprdbuzwqebwuiejoxsxdhbmuaiscalnteocghnlisxxawxgcjloevrdcj",
            output = 38
        )
    )
    
    override fun solve(testCaseInput: String): Int {
        return minimumLength(testCaseInput)
    }

    private fun minimumLength(s: String): Int {
        val charFrequencyArray = IntArray(26)
        for (i in s.indices) charFrequencyArray[s[i] - 'a']++

        return charFrequencyArray.fold(0) { acc, freq ->
            acc + when {
                freq == 0 -> 0
                freq % 2 == 0 -> 2
                else -> 1
            }
        }
    }
}
