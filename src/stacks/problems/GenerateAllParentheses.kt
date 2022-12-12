package src.stacks.problems

import src.core.Problem
import src.core.TestCase
import java.util.Stack

class GenerateAllParentheses : Problem<String, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = GenerateAllParentheses().run()
    }

    override fun getTestCases(): Array<TestCase<String, Int>> = arrayOf(
        TestCase(input = "()[]{}", output = 1),
        TestCase(input = "([[{}]()])", output = 1),
        TestCase(input = "){}", output = 0),
        TestCase(input = "{(}", output = 0)
    )

    override fun solve(testCaseInput: String): Int {
        return isValid(testCaseInput)
    }

    private fun isValid(input: String): Int {
        val stack = Stack<Char>()
        for (ch in input.toCharArray()) {
            if (stack.empty() && isClosing(ch)) return 0
            if (isOpening(ch)) {
                stack.push(ch)
            }
            if (isClosing(ch)) {
                if (isMatching(stack.peek(), ch)) stack.pop() else return 0
            }
        }
        return if (stack.empty()) 1 else 0
    }

    private fun isOpening(ch: Char): Boolean = ch == '(' || ch == '[' || ch == '{'

    private fun isClosing(ch: Char): Boolean = ch == ')' || ch == ']' || ch == '}'

    private fun isMatching(open: Char, close: Char): Boolean {
        if (open == '(' && close == ')') return true
        return if (open == '[' && close == ']') true else open == '{' && close == '}'
    }
}
