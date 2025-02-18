package dev.mslalith.stacks.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import java.util.Stack

class ConstructSmallestNumberFromDIString : TestCaseProblem<String, String>() {
    
    companion object {
        @JvmStatic
        fun main(args: Array<String>) = ConstructSmallestNumberFromDIString().runForConsole()
    }
    
    override fun getTestCases(): Array<TestCase<String, String>> = arrayOf(
        TestCase(
            input = "IIIDIDDD",
            output = "123549876"
        ),
        TestCase(
            input = "DDD",
            output = "4321"
        )
    )
    
    override fun solve(testCaseInput: String): String {
        return smallestNumber(testCaseInput)
    }

    private fun smallestNumber(pattern: String): String {
        val sb = StringBuilder()
        val stack = Stack<Char>()

        var digit = '1'
        stack.push(digit++)

        for (ch in pattern) {
            if (ch == 'I') while (stack.isNotEmpty()) sb.append(stack.pop())
            stack.push(digit++)
        }

        while (stack.isNotEmpty()) sb.append(stack.pop())

        return sb.toString()
    }
}
