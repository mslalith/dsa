package dev.mslalith.stacks.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import java.util.Stack
import kotlin.math.max

class LongestValidParentheses : TestCaseProblem<String, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = LongestValidParentheses().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<String, Int>> = arrayOf(
        TestCase(
            input = "(()",
            output = 2
        ),
        TestCase(
            input = ")()())",
            output = 4
        ),
        TestCase(
            input = "",
            output = 0
        ),
        TestCase(
            input = "()(()",
            output = 2
        ),
        TestCase(
            input = "()()",
            output = 4
        )
    )

    override fun solve(testCaseInput: String): Int {
        return longestValidParentheses(testCaseInput)
    }

    private fun longestValidParentheses(s: String): Int {
        val n = s.length
        if (n == 0) return 0

        val stack = Stack<Int>()
        stack.push(-1)

        var maxCount = 0

        for (i in s.indices) {
            val ch = s[i]
            when (ch) {
                '(' -> stack.push(i)
                else -> {
                    stack.pop()
                    when {
                        stack.isEmpty() -> stack.push(i)
                        else -> maxCount = max(maxCount, i - stack.peek())
                    }
                }
            }
        }

        return maxCount
    }

    private fun longestValidParenthesesBruteForce(s: String): Int {
        val n = s.length
        if (n == 0) return 0

        var maxCount = 0

        fun isValid(str: String): Boolean {
            var openCount = 0

            for (ch in str) {
                when {
                    ch == '(' -> openCount++
                    openCount == 0 -> return false
                    else -> openCount--
                }
            }

            return openCount == 0
        }

        for (i in 0 until n) {
            for (j in (i + 1) until n) {
                val str = s.substring(i, j + 1)
                if (isValid(str)) maxCount = max(maxCount, str.length)
            }
        }

        return maxCount
    }
}
