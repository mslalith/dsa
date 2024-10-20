package src.stacks.problems

import src.core.Problem
import src.core.TestCase
import java.util.Stack

class ValidParentheses : Problem<String, Boolean>() {
    
    companion object {
        @JvmStatic
        fun main(args: Array<String>) = ValidParentheses().run()
    }
    
    override fun getTestCases(): Array<TestCase<String, Boolean>> = arrayOf(
        TestCase(
            input = "()",
            output = true
        ),
        TestCase(
            input = "()[]{}",
            output = true
        ),
        TestCase(
            input = "([])",
            output = true
        ),
        TestCase(
            input = "[",
            output = false
        )
    )
    
    override fun solve(testCaseInput: String): Boolean {
        return isValid(testCaseInput)
    }

    private fun isValid(s: String): Boolean {
        if (s.isEmpty()) return true

        val stack = Stack<Char>()

        fun Char.isOpening(): Boolean = this == '(' || this == '[' || this == '{'
        fun Char.matches(other: Char): Boolean = when (this) {
            ')' -> other == '('
            ']' -> other == '['
            '}' -> other == '{'
            else -> false
        }

        for (ch in s) {
            if (ch.isOpening()) stack.push(ch) else {
                if (stack.isEmpty()) return false
                if (!ch.matches(stack.peek())) return false
                stack.pop()
            }
        }

        return stack.isEmpty()
    }
}
