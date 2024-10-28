package dev.mslalith.backtracking.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem

class GenerateParentheses : TestCaseProblem<Int, List<String>>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = GenerateParentheses().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<Int, List<String>>> = arrayOf(
        TestCase(
            input = 3,
            output = listOf("((()))", "(()())", "(())()", "()(())", "()()()")
        ),
        TestCase(
            input = 1,
            output = listOf("()")
        ),
        TestCase(
            input = 2,
            output = listOf("(())", "()()")
        )
    )

    override fun solve(testCaseInput: Int): List<String> {
        return generateParenthesis(testCaseInput)
    }

    private fun generateParenthesis(n: Int): List<String> {
        val result = mutableListOf<String>()
        val sb = StringBuilder()

        fun buildParenthesis(openCount: Int, closeCount: Int) {
            if (sb.length == n * 2) {
                result.add(sb.toString())
                return
            }

            if (openCount < n) {
                sb.append('(')
                buildParenthesis(openCount + 1, closeCount)
                sb.deleteAt(sb.lastIndex)
            }
            if (closeCount < openCount && closeCount < n) {
                sb.append(')')
                buildParenthesis(openCount, closeCount + 1)
                sb.deleteAt(sb.lastIndex)
            }
        }

        buildParenthesis(0, 0)

        return result
    }
}
