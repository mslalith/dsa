package dev.mslalith.stacks.problems

import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.core.TestCase
import java.util.*

class RemovingStarsFromString : TestCaseProblem<String, String>() {
    
    companion object {
        @JvmStatic
        fun main(args: Array<String>) = RemovingStarsFromString().runForConsole()
    }
    
    override fun getTestCases(): Array<TestCase<String, String>> = arrayOf(
        TestCase(
            input = "leet**cod*e",
            output = "lecoe"
        ),
        TestCase(
            input = "erase*****",
            output = ""
        )
    )
    
    override fun solve(testCaseInput: String): String {
        return removeStars(testCaseInput)
    }

    private fun removeStars(s: String): String {
        val stack = Stack<Char>()

        for (ch in s) {
            if (ch == '*') stack.pop()
            else stack.push(ch)
        }

        return buildString {
            while(stack.isNotEmpty()) insert(0, stack.pop())
        }
    }
}
