package dev.mslalith.string.problems

import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.core.TestCase
import kotlin.math.min

class LongestCommonPrefix : TestCaseProblem<Array<String>, String>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = LongestCommonPrefix().runForConsole()
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

    private fun longestCommonPrefix(strs: Array<String>): String {
        val sb = StringBuilder()
        if (strs.isEmpty()) return sb.toString()
        if (strs.size == 1) return strs.first()

        strs.sort()
        val first = strs.first()
        val last = strs.last()
        val n = min(first.length, last.length)

        for (i in 0 until n) {
            if (first[i] != last[i]) break
            sb.append(first[i])
        }

        return sb.toString()
    }

    private fun longestCommonPrefixAttempt(strs: Array<String>): String {
        val sb = StringBuilder()
        if (strs.isEmpty()) return sb.toString()

        val minWordLength = strs.minBy { it.length }
        minWordLength.forEachIndexed { i,  ch ->
            if (strs.all { it[i] == ch }) sb.append(ch)
            else return sb.toString()
        }

        return sb.toString()
    }
}
