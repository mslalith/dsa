package src.stacks.problems

import src.core.Problem
import src.core.TestCase
import java.util.Stack

class EvaluateReversePolishNotation : Problem<Array<String>, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = EvaluateReversePolishNotation().run()
    }

    override fun getTestCases(): Array<TestCase<Array<String>, Int>> = arrayOf(
        TestCase(
            input = arrayOf("2", "1", "+", "3", "*"),
            output = 9
        ),
        TestCase(
            input = arrayOf("4", "13", "5", "/", "+"),
            output = 6
        ),
        TestCase(
            input = arrayOf("10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"),
            output = 22
        )
    )

    override fun solve(testCaseInput: Array<String>): Int {
        return evalRPN(testCaseInput)
    }

    private fun evalRPN(tokens: Array<String>): Int {
        if (tokens.isEmpty()) return 0

        val stack = Stack<Int>()

        for (token in tokens) {
            when (token) {
                "+" -> stack.push(stack.pop() + stack.pop())
                "-" -> stack.push(-stack.pop() + stack.pop())
                "*" -> stack.push(stack.pop() * stack.pop())
                "/" -> {
                    val a = stack.pop()
                    stack.push(stack.pop() / a)
                }
                else -> stack.push(token.toInt())
            }
        }

        return stack.pop()
    }
}
