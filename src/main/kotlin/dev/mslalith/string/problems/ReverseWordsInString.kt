package dev.mslalith.string.problems

import dev.mslalith.core.Problem
import dev.mslalith.core.TestCase

class ReverseWordsInString : Problem<String, String>() {
    
    companion object {
        @JvmStatic
        fun main(args: Array<String>) = ReverseWordsInString().run()
    }
    
    override fun getTestCases(): Array<TestCase<String, String>> = arrayOf(
        TestCase(
            input = "the sky is blue",
            output = "blue is sky the"
        ),
        TestCase(
            input = "  hello world  ",
            output = "world hello"
        ),
        TestCase(
            input = "a good   example",
            output = "example good a"
        )
    )
    
    override fun solve(testCaseInput: String): String {
        return reverseWords(testCaseInput)
    }

    private fun reverseWords(s: String): String {
        val sb = StringBuilder()

        val words = s.split(" ")
        for (w in words) {
            if (w.isEmpty()) continue
            sb.insert(0, w.trim())
            sb.insert(0, ' ')
        }
        sb.deleteAt(0)

        return sb.toString()
    }
}
