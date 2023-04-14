package src.string.problems

import src.core.Problem
import src.core.TestCase

class RemoveOutermostParentheses : Problem<String, String>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = RemoveOutermostParentheses().run()
    }

    override fun getTestCases(): Array<TestCase<String, String>> = arrayOf(
        TestCase(
            input = "(()())(())",
            output = "()()()"
        ),
        TestCase(
            input = "(()())(())(()(()))",
            output = "()()()()(())"
        ),
        TestCase(
            input = "()()",
            output = ""
        )
    )

    override fun solve(testCaseInput: String): String {
        return removeOuterParentheses(testCaseInput)
    }

    private fun removeOuterParentheses(s: String): String = buildString {
        var openCount = 0
        for (ch in s) {
            if (ch == '(') {
                openCount++
                if (openCount > 1) append(ch)
            }
            if (ch == ')') {
                openCount--
                if (openCount > 0) append(ch)
            }
        }
    }
}
