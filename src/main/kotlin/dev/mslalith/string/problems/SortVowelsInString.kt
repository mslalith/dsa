package dev.mslalith.string.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import java.util.PriorityQueue

class SortVowelsInString : TestCaseProblem<String, String>() {
    
    companion object {
        @JvmStatic
        fun main(args: Array<String>) = SortVowelsInString().runForConsole()
    }
    
    override fun getTestCases(): Array<TestCase<String, String>> = arrayOf(
        TestCase(
            input = "lEetcOde",
            output = "lEOtcede"
        ),
        TestCase(
            input = "lYmpH",
            output = "lYmpH"
        )
    )
    
    override fun solve(testCaseInput: String): String {
        return sortVowels(testCaseInput)
    }

    private fun sortVowels(s: String): String {

        fun Char.isVowel(): Boolean = when (lowercaseChar()) {
            'a', 'e', 'i', 'o', 'u' -> true
            else -> false
        }

        val pq = PriorityQueue<Char> { a, b -> a.code - b.code }
        for (ch in s) if (ch.isVowel()) pq.add(ch)

        return buildString {
            for (ch in s) {
                if (ch.isVowel()) {
                    if (pq.isNotEmpty()) append(pq.poll())
                } else {
                    append(ch)
                }
            }
        }
    }
}
