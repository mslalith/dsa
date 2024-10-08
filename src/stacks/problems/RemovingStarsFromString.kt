package src.stacks.problems

import src.core.Problem
import src.core.TestCase
import java.util.Stack

class RemovingStarsFromString : Problem<String, String>() {
    
    companion object {
        @JvmStatic
        fun main(args: Array<String>) = RemovingStarsFromString().run()
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
