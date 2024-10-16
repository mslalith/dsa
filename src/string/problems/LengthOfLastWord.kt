package src.string.problems

import src.core.Problem
import src.core.TestCase

class LengthOfLastWord : Problem<String, Int>() {
    
    companion object {
        @JvmStatic
        fun main(args: Array<String>) = LengthOfLastWord().run()
    }
    
    override fun getTestCases(): Array<TestCase<String, Int>> = arrayOf(
        TestCase(
            input = "Hello World",
            output = 5
        ),
        TestCase(
            input = "   fly me   to   the moon  ",
            output = 4
        ),
        TestCase(
            input = "luffy is still joyboy",
            output = 6
        )
    )
    
    override fun solve(testCaseInput: String): Int {
        return lengthOfLastWord(testCaseInput)
    }

    private fun lengthOfLastWord(s: String): Int {
        var count = 0
        var wordFound = false

        for (i in (s.length - 1) downTo 0) {
            if (s[i] != ' ') {
                if (wordFound) count++ else {
                    wordFound = true
                    count = 1
                }
            } else if (wordFound) break
        }

        return count
    }

    private fun lengthOfLastWordAttempt(s: String): Int {
        return s.trim().split(" ").last().length
    }
}
