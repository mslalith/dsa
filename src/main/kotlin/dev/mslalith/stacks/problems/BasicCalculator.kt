package dev.mslalith.stacks.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import java.util.Stack

class BasicCalculator : TestCaseProblem<String, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = BasicCalculator().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<String, Int>> = arrayOf(
        TestCase(
            input = "1 + 1",
            output = 2
        ),
        TestCase(
            input = " 2-1 + 2 ",
            output = 3
        ),
        TestCase(
            input = "2147483647",
            output = 2147483647
        )
    )

    override fun solve(testCaseInput: String): Int {
        return calculate(testCaseInput)
    }

    private fun calculate(s: String): Int {
        val n = s.length
        val stack = Stack<Int>()

        var answer = 0
        var sign = 1
        var currNumber: Int
        var i = 0

        while (i < n) {
            when (val ch = s[i]) {
                ' ' -> { /* do nothing */ }

                in '0'..'9' -> {
                    currNumber = ch - '0'
                    while (i + 1 < n && s[i + 1] in '0'..'9') {
                        currNumber = (currNumber * 10) + (s[i + 1] - '0')
                        i++
                    }
                    currNumber *= sign
                    answer += currNumber
                    sign = 1
                }

                '+' -> sign = 1
                '-' -> sign = -1

                '(' -> {
                    stack.push(answer)
                    stack.push(sign)

                    answer = 0
                    sign = 1
                }

                ')' -> {
                    val prevSign = stack.pop()
                    val prevAns = stack.pop()
                    answer *= prevSign
                    answer += prevAns
                }
            }
            i++
        }

        return answer
    }
}
