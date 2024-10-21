package dev.mslalith.stacks.problems

import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.core.TestCase
import java.util.*

class ValidParentheses : TestCaseProblem<String, Boolean>() {
    
    companion object {
        @JvmStatic
        fun main(args: Array<String>) = ValidParentheses().runAll()
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
