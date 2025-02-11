package dev.mslalith.string.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem

class RemoveAllOccurrencesOfSubstring : TestCaseProblem<Pair<String, String>, String>() {
    
    companion object {
        @JvmStatic
        fun main(args: Array<String>) = RemoveAllOccurrencesOfSubstring().runForConsole()
    }
    
    override fun getTestCases(): Array<TestCase<Pair<String, String>, String>> = arrayOf(
        TestCase(
            input = "daabcbaabcbc" to "abc",
            output = "dab"
        ),
        TestCase(
            input = "axxxxyyyyb" to "xy",
            output = "ab"
        )
    )
    
    override fun solve(testCaseInput: Pair<String, String>): String {
        return removeOccurrences(testCaseInput.first, testCaseInput.second)
    }

    private fun removeOccurrences(s: String, part: String): String {
        val sb = StringBuilder(s)

        var index = sb.indexOf(part)
        while (index > -1) {
            sb.delete(index, index + part.length)
            index = sb.indexOf(part)
        }

        return sb.toString()
    }
}
