package src.stacks.problems

import src.core.Problem
import src.core.TestCase
import src.utils.buildArray
import java.util.Stack

class EvaluateExpression : Problem<Array<String>, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = EvaluateExpression().run()
    }

    override fun getTestCases(): Array<TestCase<Array<String>, Int>> = arrayOf(
        TestCase(input = buildArray(input = "2 1 + 3 *"), output = 9),
        TestCase(input = buildArray(input = "4 13 5 / +"), output = 6),
        TestCase(input = buildArray(input = "5 1 2 + 4 * + 3 -"), output = 14),
        TestCase(input = buildArray(input = "5"), output = 5)
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
