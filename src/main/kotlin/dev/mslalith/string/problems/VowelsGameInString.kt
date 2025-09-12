package dev.mslalith.string.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem

class VowelsGameInString : TestCaseProblem<String, Boolean>() {
    
    companion object {
        @JvmStatic
        fun main(args: Array<String>) = VowelsGameInString().runForConsole()
    }
    
    override fun getTestCases(): Array<TestCase<String, Boolean>> = arrayOf(
        TestCase(
            input = "leetcoder",
            output = true
        ),
        TestCase(
            input = "bbcd",
            output = false
        ),
        TestCase(
            input = "ifld",
            output = true
        )
    )
    
    override fun solve(testCaseInput: String): Boolean {
        return doesAliceWin(testCaseInput)
    }

    private fun doesAliceWin(s: String): Boolean {
        val vowels = hashSetOf('a', 'e', 'i', 'o', 'u')
        return s.any { it in vowels }
    }
}
