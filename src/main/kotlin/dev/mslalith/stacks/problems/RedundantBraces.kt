package dev.mslalith.stacks.problems

import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.core.TestCase
import java.util.*

class RedundantBraces : TestCaseProblem<String, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = RedundantBraces().runAll()
    }

    override fun getTestCases(): Array<TestCase<String, Int>> = arrayOf(
        TestCase(input = "((a+b))", output = 1),
        TestCase(input = "(a+(a+b))", output = 0),
        TestCase(input = "((a*b)+(c+d))", output = 0),
        TestCase(input = "(a+((b*c)+c))", output = 0)
    )

    override fun solve(testCaseInput: String): Int {
        return braces(testCaseInput)
    }

    private fun braces(input: String): Int {
        val stack = Stack<Char>()
        for (ch in input.toCharArray()) {
            if (isOpening(ch) || isOperator(ch)) {
                stack.push(ch)
            } else if (isClosing(ch)) {
                if (isOpening(stack.peek())) return 1
                while (!isOpening(stack.peek())) {
                    stack.pop()
                }
                stack.pop()
            }
        }
        return 0
    }

    private fun isOpening(ch: Char): Boolean = ch == '('

    private fun isClosing(ch: Char): Boolean = ch == ')'

    private fun isOperator(ch: Char): Boolean = ch == '+' || ch == '-' || ch == '*' || ch == '/'
}
