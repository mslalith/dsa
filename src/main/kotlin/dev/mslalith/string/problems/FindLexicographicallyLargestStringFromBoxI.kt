package dev.mslalith.string.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem

class FindLexicographicallyLargestStringFromBoxI : TestCaseProblem<Pair<String, Int>, String>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = FindLexicographicallyLargestStringFromBoxI().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<Pair<String, Int>, String>> = arrayOf(
        TestCase(
            input = "dbca" to 2,
            output = "dbc"
        ),
        TestCase(
            input = "gggg" to 4,
            output = "g"
        )
    )

    override fun solve(testCaseInput: Pair<String, Int>): String {
        return answerString(testCaseInput.first, testCaseInput.second)
    }

    private fun answerString(word: String, numFriends: Int): String {
        if (numFriends == 1) return word

        val n = word.length
        val length = n - numFriends + 1
        var res = ""

        for (i in 0..<n) {
            val subWord = when {
                i + length <= n -> word.substring(i, i + length)
                else -> word.substring(i)
            }
            if (subWord > res) res = subWord
        }

        return res
    }
}