package dev.mslalith.string.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem

class ClearDigits : TestCaseProblem<String, String>() {
    
    companion object {
        @JvmStatic
        fun main(args: Array<String>) = ClearDigits().runForConsole()
    }
    
    override fun getTestCases(): Array<TestCase<String, String>> = arrayOf(
        TestCase(
            input = "abc",
            output = "abc"
        ),
        TestCase(
            input = "cb34",
            output = ""
        )
    )
    
    override fun solve(testCaseInput: String): String {
        return clearDigits(testCaseInput)
    }

    private fun clearDigits(s: String): String {
        val n = s.length
        if (n < 2) return s

        val sb = StringBuilder()

        for (i in s.indices) {
            val ch = s[i]
            when {
                ch.isDigit() -> sb.deleteAt(sb.lastIndex)
                else -> sb.append(ch)
            }
        }

        return sb.toString()
    }
}
