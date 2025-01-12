package dev.mslalith.stacks.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import java.util.Stack

class CheckIfParenthesesStringCanBeValid : TestCaseProblem<Pair<String, String>, Boolean>() {
    
    companion object {
        @JvmStatic
        fun main(args: Array<String>) = CheckIfParenthesesStringCanBeValid().runForConsole()
    }
    
    override fun getTestCases(): Array<TestCase<Pair<String, String>, Boolean>> = arrayOf(
        TestCase(
            input = "))()))" to "010100",
            output = true
        ),
        TestCase(
            input = "()()" to "0000",
            output = true
        ),
        TestCase(
            input = ")" to "0",
            output = false
        ),
        TestCase(
            input = "((((()" to "101000",
            output = true
        )
    )
    
    override fun solve(testCaseInput: Pair<String, String>): Boolean {
        return canBeValid(testCaseInput.first, testCaseInput.second)
    }

    private fun canBeValid(s: String, locked: String): Boolean {
        val n = s.length
        if (n % 2 == 1) return false

        var balance = 0

        for (i in 0 until n) {
            when {
                s[i] == '(' || locked[i] == '0' -> balance++
                balance > 0 -> balance--
                else -> return false
            }
        }

        balance = 0

        for (i in (n - 1) downTo 0) {
            when {
                s[i] == ')' || locked[i] == '0' -> balance++
                balance > 0 -> balance--
                else -> return false
            }
        }

        return true
    }

    private fun canBeValidUsingStacks(s: String, locked: String): Boolean {
        val n = s.length
        if (n % 2 == 1) return false

        val openBrackets = Stack<Int>()
        val unlocked = Stack<Int>()

        for (i in 0 until n) {
            val ch = s[i]
            when {
                locked[i] == '0' -> unlocked.push(i)
                ch == '(' -> openBrackets.push(i)
                else -> when {
                    // encountered ), we need (
                    openBrackets.isNotEmpty() -> openBrackets.pop()
                    // if unlocked is having
                    // - (, not a problem, that is what we need
                    // - ), this is supposed to be changed
                    unlocked.isNotEmpty() -> unlocked.pop()
                    // no more chars on the left side
                    else -> return false
                }
            }
        }

        while (openBrackets.isNotEmpty() && unlocked.isNotEmpty() && openBrackets.peek() < unlocked.peek()) {
            openBrackets.pop()
            unlocked.pop()
        }

        return openBrackets.isEmpty()
    }
}
