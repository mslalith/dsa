package dev.mslalith.stacks.problems

import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.core.TestCase
import dev.mslalith.utils.buildArray
import java.util.*

class EvaluateExpression : TestCaseProblem<Array<String>, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = EvaluateExpression().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<Array<String>, Int>> = arrayOf(
        TestCase(input = buildArray(input = "2 1 + 3 *", separator = " "), output = 9),
        TestCase(input = buildArray(input = "4 13 5 / +", separator = " "), output = 6),
        TestCase(input = buildArray(input = "5 1 2 + 4 * + 3 -", separator = " "), output = 14),
        TestCase(input = buildArray(input = "5", separator = " "), output = 5)
    )

    override fun solve(testCaseInput: Array<String>): Int {
        return eval(testCaseInput)
    }

    private fun eval(inputArray: Array<String>): Int {
        val stack = Stack<String>()
        var result = inputArray[0].toInt()
        for (input in inputArray) {
            if (isOperator(input)) {
                result = handleOperator(input, stack.pop(), stack.pop())
                stack.push(result.toString())
            } else {
                stack.push(input)
            }
        }
        return result
    }

    private fun handleOperator(operator: String, right: String, left: String): Int {
        val leftInt = left.toInt()
        val rightInt = right.toInt()
        return when (operator) {
            "+" -> leftInt + rightInt
            "-" -> leftInt - rightInt
            "*" -> leftInt * rightInt
            "/" -> leftInt / rightInt
            else -> throw UnsupportedOperationException("$operator not supported")
        }
    }

    private fun isOperator(character: String): Boolean {
        if (character == "+") return true
        if (character == "-") return true
        return if (character == "*") true else character == "/"
    }
}
