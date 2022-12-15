package src.string.problems

import src.core.Problem
import src.core.TestCase
import java.lang.StringBuilder

class LongestCommonPrefix : Problem<Array<String>, String>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = LongestCommonPrefix().run()
    }

    override fun getTestCases(): Array<TestCase<Array<String>, String>> = arrayOf(
        TestCase(
            input = arrayOf("flower", "flow", "flight"),
            output = "fl"
        ),
        TestCase(
            input = arrayOf("dog", "racecar", "car"),
            output = ""
        )
    )

    override fun solve(testCaseInput: Array<String>): String {
        return longestCommonPrefix(testCaseInput)
    }

    private fun longestCommonPrefix(list: Array<String>): String {
        val sb = StringBuilder()
        if (list.isEmpty()) return sb.toString()

        val minWordLength = list.minBy { it.length }
        minWordLength.forEachIndexed { i,  ch ->
            if (list.all { it[i] == ch }) sb.append(ch)
            else return sb.toString()
        }

        return sb.toString()
    }
}