package dev.mslalith.string.problems

import dev.mslalith.core.Problem
import dev.mslalith.core.TestCase

class FindIndexOfFirstOccurrenceInString : Problem<Pair<String, String>, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = FindIndexOfFirstOccurrenceInString().run()
    }

    override fun getTestCases(): Array<TestCase<Pair<String, String>, Int>> = arrayOf(
        TestCase(
            input = "sadbutsad" to "sad",
            output = 0
        ),
        TestCase(
            input = "leetcode" to "leeto",
            output = -1
        ),
        TestCase(
            input = "hello" to "ll",
            output = 2
        ),
        TestCase(
            input = "abc" to "c",
            output = 2
        )
    )

    override fun solve(testCaseInput: Pair<String, String>): Int {
        return strStr(testCaseInput.first, testCaseInput.second)
    }

    private fun strStr(haystack: String, needle: String): Int {
        if (haystack.length < needle.length) return -1
        if (haystack == needle) return 0

        var i = 0
        while (i <= (haystack.length - needle.length)) {
            if (haystack.substring(i, i + needle.length) == needle) return i
            i++
        }

        return -1
    }
}
